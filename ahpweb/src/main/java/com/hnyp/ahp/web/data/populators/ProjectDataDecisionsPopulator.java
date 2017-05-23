package com.hnyp.ahp.web.data.populators;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.web.data.ProjectData;
import com.hnyp.ahp.web.data.ProjectDecisionData;
import com.hnyp.ahp.web.v2.models.Project;
import com.hnyp.ahp.web.v2.models.ProjectDecision;

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
