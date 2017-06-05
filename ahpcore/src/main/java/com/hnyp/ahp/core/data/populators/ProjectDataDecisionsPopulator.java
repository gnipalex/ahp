package com.hnyp.ahp.core.data.populators;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.util.Converters;

public class ProjectDataDecisionsPopulator implements Populator<Project, ProjectData> {

    @Resource
    private Converter<ProjectDecision, ProjectDecisionData> basicProjectDecisionDataConverter;
    
    @Override
    public void populate(Project source, ProjectData target) {
        target.setDecisions(Converters.convertAll(source.getDecisions(), basicProjectDecisionDataConverter));
    }

}
