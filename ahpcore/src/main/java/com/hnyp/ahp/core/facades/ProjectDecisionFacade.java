package com.hnyp.ahp.core.facades;

import com.hnyp.ahp.core.data.ProjectDecisionData;

public interface ProjectDecisionFacade {

    ProjectDecisionData getProjectDecision(long id);
    
    ProjectDecisionData getProjectDecision(long projectId, long decisionId);
    
    void createProjectDecision(long projectId, ProjectDecisionData data);
    
    void update(ProjectDecisionData projectDecisionData);
    
    void remove(long projectDecisionId);
    
}
