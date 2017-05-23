package com.hnyp.ahp.web.data.populators;

import com.hnyp.ahp.web.data.ProjectDecisionData;
import com.hnyp.ahp.web.v2.models.ProjectDecision;

public class ProjectDecisionBasicPopulator implements Populator<ProjectDecision, ProjectDecisionData> {

    @Override
    public void populate(ProjectDecision source, ProjectDecisionData target) {
        target.setId(source.getId());
        target.setGoal(source.getGoal());
        target.setDescription(source.getDescription());
    }

}
