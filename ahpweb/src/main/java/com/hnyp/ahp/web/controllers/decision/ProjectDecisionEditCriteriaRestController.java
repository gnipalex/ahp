package com.hnyp.ahp.web.controllers.decision;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hnyp.ahp.core.data.CriteriaData;
import com.hnyp.ahp.core.facades.CriteriaFacade;
import com.hnyp.ahp.web.forms.CriteriaForm;
import com.hnyp.ahp.web.response.AjaxResponseWraper;

@RestController
@RequestMapping("/project/{projectId}/decision/{id}")
public class ProjectDecisionEditCriteriaRestController {

    @Resource
    private CriteriaFacade criteriaFacade;
    
    @RequestMapping(value="/criteria", method = RequestMethod.POST)
    public AjaxResponseWraper<List<CriteriaData>> createCriteria(@PathVariable("id") long projectDecisionId, @Valid CriteriaForm criteriaForm, BindingResult bindingResult) {
        AjaxResponseWraper<List<CriteriaData>> response = new AjaxResponseWraper<>();
        if (criteriaFacade.doesCriteriaExistForDecisionAndName(projectDecisionId, criteriaForm.getName())) {
            bindingResult.rejectValue("name", "Decision already contains alternative with provided name");
        }
        if (bindingResult.hasErrors()) {
            putErrorsToResponse(bindingResult, response);
        } else {
            CriteriaData criteriaData = new CriteriaData();
            criteriaData.setDescription(criteriaForm.getDescription());
            criteriaData.setName(criteriaForm.getName());
            
            criteriaFacade.create(projectDecisionId, criteriaData);
            
            List<CriteriaData> criterias = criteriaFacade.getCriteriasForDecision(projectDecisionId);
            response.setData(criterias);
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
    
    @RequestMapping(value="/criteria/{criteriaId}", method = RequestMethod.POST)
    public AjaxResponseWraper<List<CriteriaData>> updateCriteria(@PathVariable("id") long projectDecisionId, @PathVariable long criteriaId, @Valid CriteriaForm criteriaForm, BindingResult bindingResult) {
        AjaxResponseWraper<List<CriteriaData>> response = new AjaxResponseWraper<>();
        if (doesOtherAlternativeWithNameAndProjectDecisionExist(projectDecisionId, criteriaId, criteriaForm.getName())) {
            // is not displayed
            bindingResult.rejectValue("name", "Decision already contains criteria with provided name");
        }
        if (bindingResult.hasErrors()) {
            putErrorsToResponse(bindingResult, response);
        } else {
            CriteriaData criteriaData = new CriteriaData();
            criteriaData.setDescription(criteriaForm.getDescription());
            criteriaData.setName(criteriaForm.getName());
            criteriaData.setId(criteriaForm.getId());
            
            criteriaFacade.update(criteriaData);
            
            List<CriteriaData> criterias = criteriaFacade.getCriteriasForDecision(projectDecisionId);
            response.setData(criterias);
        }
        return response;
    }
    
    private boolean doesOtherAlternativeWithNameAndProjectDecisionExist(long projectDecisionId, long criteriaId, String criteriaName) {
        CriteriaData criteriaData = criteriaFacade.getCriteriaForDecisionAndName(projectDecisionId, criteriaName);
        return criteriaData != null && criteriaData.getId() != criteriaId;
    }
    
    @RequestMapping(value = "/criteria/{criteriaId}/delete", method=RequestMethod.POST)
    public AjaxResponseWraper<Boolean> removeCriteria(@PathVariable long criteriaId) {
        criteriaFacade.remove(criteriaId);
        AjaxResponseWraper<Boolean> response = new AjaxResponseWraper<>();
        response.setData(true);
        return response;
    }
    
}
