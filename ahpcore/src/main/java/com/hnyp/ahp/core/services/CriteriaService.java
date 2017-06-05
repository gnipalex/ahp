package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.ProjectDecision;

public interface CriteriaService {
    
    List<Criteria> getForProjectDecision(ProjectDecision projectDecision);
    
    void create(Criteria criteria);
    
    void updateCriteria(Criteria criteria);
    
    Criteria getForProjectDecisionAndName(ProjectDecision projectDecision, String name);
    
}
