package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.ProjectDecision;

public interface CriteriaService {
    
    List<Criteria> getForProjectDecision(ProjectDecision projectDecision);
    
}
