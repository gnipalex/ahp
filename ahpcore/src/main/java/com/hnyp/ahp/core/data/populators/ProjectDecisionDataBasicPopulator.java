package com.hnyp.ahp.core.data.populators;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.models.ProjectDecision;

public class ProjectDecisionDataBasicPopulator implements Populator<ProjectDecision, ProjectDecisionData> {

    @Override
    public void populate(ProjectDecision source, ProjectDecisionData target) {
        target.setId(source.getId());
        target.setGoal(source.getGoal());
        target.setDescription(source.getDescription());
        target.setStatus(source.getStatus());
    }

}
