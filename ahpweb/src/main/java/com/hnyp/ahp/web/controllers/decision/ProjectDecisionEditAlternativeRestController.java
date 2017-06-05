package com.hnyp.ahp.web.controllers.decision;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hnyp.ahp.core.data.AlternativeData;
import com.hnyp.ahp.core.exception.AlternativeAlreadyExistException;
import com.hnyp.ahp.core.facades.AlternativeFacade;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.web.forms.AlternativeForm;
import com.hnyp.ahp.web.response.AjaxResponseWraper;

@RestController
@RequestMapping("/project/{projectId}/decision/{id}")
public class ProjectDecisionEditAlternativeRestController {

    @Resource
    private ProjectDecisionFacade projectDecisionFacade;
    @Resource
    private AlternativeFacade alternativeFacade;
    
    @RequestMapping(value="/alternative", method = RequestMethod.POST)
    public AjaxResponseWraper<List<AlternativeData>> createAlternative(@PathVariable("id") long projectDecisionId, @Valid AlternativeForm alternativeForm, BindingResult bindingResult) {
        AjaxResponseWraper<List<AlternativeData>> response = new AjaxResponseWraper<>();
        if (alternativeFacade.doesAlternativeExistForProjectDecision(projectDecisionId, alternativeForm.getName())) {
            bindingResult.rejectValue("name", "Decision already contains alternative with provided name");
        }
        if (bindingResult.hasErrors()) {
            putErrorsToResponse(bindingResult, response);
        } else {
            AlternativeData alternativeData = new AlternativeData();
            alternativeData.setDescription(alternativeForm.getDescription());
            alternativeData.setName(alternativeForm.getName());
            
            alternativeFacade.createAlternative(projectDecisionId, alternativeData);
            
            List<AlternativeData> alternatives = alternativeFacade.getAlternativesForDecision(projectDecisionId);
            response.setData(alternatives);
        }
        return response;
    }
    
    private void putErrorsToResponse(BindingResult bindingResult, AjaxResponseWraper<?> ajaxResponse) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fe -> {
                ajaxResponse.addErrorMessage(fe.getField(), fe.getDefaultMessage());
            });
            ajaxResponse.setError(true);
        }
    }
    
    @ExceptionHandler(AlternativeAlreadyExistException.class)
    public AjaxResponseWraper<Void> handleAlternativeUpdateException(AlternativeAlreadyExistException e) {
        AjaxResponseWraper<Void> response = new AjaxResponseWraper<>();
        response.setError(true);
        response.setErrorMessage(e.getMessage());
        return response;
    }
    
    @RequestMapping(value="/alternative/{alternativeId}", method = RequestMethod.POST)
    public AjaxResponseWraper<List<AlternativeData>> editAlternative(@PathVariable long alternativeId, @PathVariable("id") long projectDecisionId, @Valid AlternativeForm alternativeForm, BindingResult bindingResult) {
        AjaxResponseWraper<List<AlternativeData>> ajaxResponse = new AjaxResponseWraper<>();
        if (doesOtherAlternativeWithNameAndProjectDecisionExist(projectDecisionId, alternativeId, alternativeForm.getName())) {
            bindingResult.rejectValue("name", "Decision already contains alternative with provided name");
        }
        if (bindingResult.hasErrors()) {
            putErrorsToResponse(bindingResult, ajaxResponse);
        } else {
            AlternativeData alternativeData = new AlternativeData();
            alternativeData.setName(alternativeForm.getName());
            alternativeData.setDescription(alternativeForm.getDescription());
            alternativeData.setId(alternativeId);
            
            alternativeFacade.update(alternativeData);
            
            List<AlternativeData> alternatives = alternativeFacade.getAlternativesForDecision(projectDecisionId);
            ajaxResponse.setData(alternatives);
        }
        return ajaxResponse;
    }
    
    private boolean doesOtherAlternativeWithNameAndProjectDecisionExist(long projectDecisionId, long alternativeId, String alternativeName) {
        AlternativeData alternative = alternativeFacade.getAlternativeForDecisionAndName(projectDecisionId, alternativeName);
        return alternative != null && alternative.getId() != alternativeId;
    }
    
    @RequestMapping(value = "/alternative/{alternativeId}/delete", method=RequestMethod.POST)
    public AjaxResponseWraper<Boolean> removeAlternative(@PathVariable long alternativeId) {
        alternativeFacade.remove(alternativeId);
        AjaxResponseWraper<Boolean> response = new AjaxResponseWraper<>();
        response.setData(true);
        return response;
    }
    
}
