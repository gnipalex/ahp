package com.hnyp.ahp.web.facades;

import com.hnyp.ahp.web.data.ProjectDecisionData;
import com.hnyp.ahp.web.forms.ProjectDecisionForm;
import com.hnyp.ahp.web.models.ProjectDecision;

public interface ProjectDecisionFacade {

    ProjectDecisionData getProjectDecision(long id);
    
    void createProjectDecision(ProjectDecisionForm form);
    
}
