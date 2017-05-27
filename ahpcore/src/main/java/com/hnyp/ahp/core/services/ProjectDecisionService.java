package com.hnyp.ahp.core.services;

import com.hnyp.ahp.core.models.ProjectDecision;

public interface ProjectDecisionService {

    ProjectDecision getProjectDecision(long id);
    
    void save(ProjectDecision projectDecision);
    
}
