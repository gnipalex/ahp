package com.hnyp.ahp.core.facades;

import java.util.List;

import com.hnyp.ahp.core.data.CriteriaData;

public interface CriteriaFacade {

    List<CriteriaData> getCriteriasForDecision(long projectDecisionId);
    
    void create(long projectDecisionId, CriteriaData criteriaData); 
    
    void update(CriteriaData criteriaData); 
    
    void remove(long criteriaId);
    
}
