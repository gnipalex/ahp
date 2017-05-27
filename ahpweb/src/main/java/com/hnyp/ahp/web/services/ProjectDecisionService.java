package com.hnyp.ahp.web.services;

import com.hnyp.ahp.web.v2.models.ProjectDecision;

public interface ProjectDecisionService {

    ProjectDecision getProjectDecision(long id);
    
    void save(ProjectDecision projectDecision);
    
}
