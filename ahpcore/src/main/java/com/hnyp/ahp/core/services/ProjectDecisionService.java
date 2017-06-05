package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.ProjectDecision;

public interface ProjectDecisionService {

    ProjectDecision getProjectDecision(long id);
    
    void save(ProjectDecision projectDecision);
    
    void remove(long id);
    
    List<ProjectDecision> getActiveDecisions(Project project);
    
    List<ProjectDecision> getDecisions(Project project, int page, int count);
    
}
