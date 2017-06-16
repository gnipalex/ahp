package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.models.AlternativeComparisonTable;
import com.hnyp.ahp.core.models.ProjectDecision;

public interface AlternativeComparisonService {

    /**
     *  creates blank comparison tables for each criteria
     * @param projectDecision
     */ 
    void createForProjectDecision(ProjectDecision projectDecision);
    
    List<AlternativeComparisonTable> getForProjectDecision(ProjectDecision projectDecision);
    
    AlternativeComparisonTable getForProjectDecisionAndId(ProjectDecision projectDecision, long id);
    
}
