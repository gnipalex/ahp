package com.hnyp.ahp.web.data.populators;

import com.hnyp.ahp.web.data.ProjectData;
import com.hnyp.ahp.web.v2.models.Project;

public class ProjectDataBasicPopulator implements Populator<Project, ProjectData> {

    @Override
    public void populate(Project source, ProjectData target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
    }

}
