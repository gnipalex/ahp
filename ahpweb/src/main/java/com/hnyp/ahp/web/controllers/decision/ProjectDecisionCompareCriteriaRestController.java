package com.hnyp.ahp.web.controllers.decision;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hnyp.ahp.core.data.ComparisonPairData;
import com.hnyp.ahp.core.data.CriteriaComparisonTableData;
import com.hnyp.ahp.core.exception.CreateBlankCriteriaComparisonException;
import com.hnyp.ahp.core.facades.CriteriaComparisonFacade;
import com.hnyp.ahp.web.forms.CreateCriteriaComparisonTableForm;
import com.hnyp.ahp.web.forms.UpdateComparisonTableForm;
import com.hnyp.ahp.web.response.AjaxResponseWraper;

@RestController
@RequestMapping("/project/{projectId}/decision/{decisionId}/criteriaComparison")
public class ProjectDecisionCompareCriteriaRestController {

    @Resource
    private CriteriaComparisonFacade criteriaComparisonFacade;
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public AjaxResponseWraper<CriteriaComparisonTableData> createBlankCriteriaComparisonTable(@PathVariable long projectId, @PathVariable long decisionId,  
            @Valid CreateCriteriaComparisonTableForm form, BindingResult bindingResult) {
        
        AjaxResponseWraper<CriteriaComparisonTableData> response = new AjaxResponseWraper<>();
        
        if (criteriaComparisonFacade.doesComparisonExistForDecisionAndName(decisionId, form.getName())) {
            bindingResult.rejectValue("name", String.format("Criteria comparison table with name %s already exists", form.getName()));
        }
        
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fe -> {
                response.addErrorMessage(fe.getField(), fe.getDefaultMessage());
            });
            response.setError(true);
        } else {
            CriteriaComparisonTableData data = new CriteriaComparisonTableData();
            data.setDescription(form.getDescription());
            data.setName(form.getName());
            
            criteriaComparisonFacade.create(decisionId, data);
            
            response.setData(criteriaComparisonFacade.getForDecisionAndName(decisionId, form.getName()));
        }
        
        return response;
    }
    
    @ExceptionHandler(CreateBlankCriteriaComparisonException.class) 
    public AjaxResponseWraper<Void> handleCreateBlankCriteriaComparisonException(CreateBlankCriteriaComparisonException e) {
        AjaxResponseWraper<Void> response = new AjaxResponseWraper<>();
        response.setError(true);
        response.setErrorMessage("Creation of blank criteria comparison table failed: " + e.getMessage());
        return response;
    }
    
    @RequestMapping("")
    public AjaxResponseWraper<List<CriteriaComparisonTableData>> loadCriteriaComparisons(@PathVariable long decisionId) {
        return AjaxResponseWraper.wrap(criteriaComparisonFacade.getForDecision(decisionId));
    }
    
    @RequestMapping(value = "/{comparisonId}", method=RequestMethod.POST)
    public AjaxResponseWraper<CriteriaComparisonTableData> updateCriteriaComparison(@PathVariable long decisionId, @PathVariable long comparisonId, @RequestBody UpdateComparisonTableForm form) {
        
        // this logic should not be here
        if (!criteriaComparisonFacade.doesComparisonExistForDecision(decisionId, comparisonId)) {
            AjaxResponseWraper<CriteriaComparisonTableData> response = new AjaxResponseWraper<>();
            response.setError(true);
            response.setErrorMessage(String.format("comparison table %s doesn't exist for decision %s", comparisonId, decisionId));
            return response;
        }
        
        if (CollectionUtils.isEmpty(form.getComparisons())) {
            AjaxResponseWraper<CriteriaComparisonTableData> response = new AjaxResponseWraper<>();
            response.setError(true);
            response.setErrorMessage("comparisons are empty");
            return response;
        }
        
        criteriaComparisonFacade.update(comparisonId, getComparisonPairs(form));
        
        CriteriaComparisonTableData comparisonTableData = criteriaComparisonFacade.getForDecisionAndId(decisionId, comparisonId);
        
        return AjaxResponseWraper.wrap(comparisonTableData);
    }
     
    private List<ComparisonPairData> getComparisonPairs(UpdateComparisonTableForm form) {
        return form.getComparisons().stream().map(comparison -> {
            ComparisonPairData data = new ComparisonPairData();
            data.setId(comparison.getId());
            data.setValue(comparison.getValue());
            return data;
        }).collect(Collectors.toList());
    }
    
    
    
}
