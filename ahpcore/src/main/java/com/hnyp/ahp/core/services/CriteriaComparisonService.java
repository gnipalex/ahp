package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.exception.CreateBlankCriteriaComparisonException;
import com.hnyp.ahp.core.models.CriteriaComparisonTable;
import com.hnyp.ahp.core.models.ProjectDecision;

public interface CriteriaComparisonService {

    /**
     * Creates blank criteria comparison. Fills with equal comparison values - 1
     * Created comparison table is not calculated
     * @param comparisonTable
     * @throws CreateBlankCriteriaComparisonException in case of errors
     */
    void create(CriteriaComparisonTable comparisonTable);
    
    CriteriaComparisonTable getForDecisionAndName(ProjectDecision projectDecision, String name);
    
    CriteriaComparisonTable getForDecisionAndId(ProjectDecision projectDecision, long id);
    
    List<CriteriaComparisonTable> getForDecision(ProjectDecision projectDecision);

}
