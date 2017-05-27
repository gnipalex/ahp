package com.hnyp.ahp.core.facades;

import com.hnyp.ahp.core.data.ProjectDecisionData;

public interface ProjectDecisionFacade {

    ProjectDecisionData getProjectDecision(long id);
    
    void createProjectDecision(long projectId, ProjectDecisionData data);
    
}
