package com.hnyp.ahp.core.facades;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.data.ProjectDecisionEditStatusData;

public interface ProjectDecisionFacade {

    ProjectDecisionData getProjectDecision(long id);
    
    ProjectDecisionData getProjectDecision(long projectId, long decisionId);
    
    ProjectDecisionEditStatusData getProjectDecisionEditStatusData(long decisionId);
    
    void createProjectDecision(long projectId, ProjectDecisionData data);
    
    void finishEditProjectDecision(long projectDecisionId);
    
    void startProjectDecision(long projectDecisionId);
    
    void finishProjectDecision(long projectDecisionId);
    
    void update(ProjectDecisionData projectDecisionData);
    
    void remove(long projectDecisionId);
    
//    boolean canReed
    
}
