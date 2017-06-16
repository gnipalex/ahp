package com.hnyp.ahp.web.controllers.decision;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hnyp.ahp.core.data.AlternativeComparisonTableData;
import com.hnyp.ahp.core.data.ComparisonPairData;
import com.hnyp.ahp.core.facades.AlternativeComparisonFacade;
import com.hnyp.ahp.web.forms.UpdateComparisonTableForm;
import com.hnyp.ahp.web.response.AjaxResponseWraper;

@RestController
@RequestMapping("/project/{projectId}/decision/{decisionId}")
public class CompareAlternativesRestController {

    @Resource
    private AlternativeComparisonFacade alternativeComparisonFacade;
    
    @RequestMapping("/loadAlternativeComparisons")
    public AjaxResponseWraper<List<AlternativeComparisonTableData>> loadAlternativeComparisons(@PathVariable long decisionId) {
        return AjaxResponseWraper.wrap(alternativeComparisonFacade.getAlternativeComparisonsForDecision(decisionId));
    }
    
    @RequestMapping(value = "/alternativeComparison/{comparisonId}", method = RequestMethod.POST)
    public AjaxResponseWraper<AlternativeComparisonTableData> updateAlternativeComparison(@PathVariable long decisionId, @PathVariable long comparisonId, 
                @RequestBody UpdateComparisonTableForm form) {
        
     // this logic should not be here
        if (!alternativeComparisonFacade.doesComparisonExistForProjectDecision(decisionId, comparisonId)) {
            AjaxResponseWraper<AlternativeComparisonTableData> response = new AjaxResponseWraper<>();
            response.setError(true);
            response.setErrorMessage(String.format("comparison table %s doesn't exist for decision %s", comparisonId, decisionId));
            return response;
        }
        
        if (CollectionUtils.isEmpty(form.getComparisons())) {
            AjaxResponseWraper<AlternativeComparisonTableData> response = new AjaxResponseWraper<>();
            response.setError(true);
            response.setErrorMessage("comparisons are empty");
            return response;
        }
        
        alternativeComparisonFacade.update(comparisonId, getComparisonPairs(form));
        
        AlternativeComparisonTableData comparisonTableData = alternativeComparisonFacade.getForDecisionAndId(decisionId, comparisonId);
        
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
