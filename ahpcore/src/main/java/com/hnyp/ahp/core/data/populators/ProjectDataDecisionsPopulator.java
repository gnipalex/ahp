package com.hnyp.ahp.core.data.populators;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.ProjectDecision;

public class ProjectDataDecisionsPopulator implements Populator<Project, ProjectData> {

    @Resource
    private Converter<ProjectDecision, ProjectDecisionData> basicProjectDataDecisionsConverter;
    
    @Override
    public void populate(Project source, ProjectData target) {
        List<ProjectDecisionData> convertedDecisionsDataList = source.getDecisions().stream()
                .map(basicProjectDataDecisionsConverter::convert).collect(Collectors.toList());
        target.setProjectDecisions(convertedDecisionsDataList);
    }

}
