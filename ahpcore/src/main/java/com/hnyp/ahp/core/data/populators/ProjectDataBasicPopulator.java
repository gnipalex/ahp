package com.hnyp.ahp.core.data.populators;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.models.Project;

public class ProjectDataBasicPopulator implements Populator<Project, ProjectData> {

    @Override
    public void populate(Project source, ProjectData target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
    }

}
