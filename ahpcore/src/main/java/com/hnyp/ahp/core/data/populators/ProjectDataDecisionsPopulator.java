package com.hnyp.ahp.core.data.populators;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.ProjectDecisionService;

public class ProjectDataDecisionsPopulator implements Populator<Project, ProjectData> {

//    @Resource
//    private ProjectDecisionService projectDecisionService;
    @Resource
    private Converter<ProjectDecision, ProjectDecisionData> basicProjectDecisionDataConverter;
    
    @Override
    public void populate(Project source, ProjectData target) {
        List<ProjectDecisionData> convertedDecisionsDataList = source.getDecisions().stream()
                .map(basicProjectDecisionDataConverter::convert).collect(Collectors.toList());
        target.setDecisions(convertedDecisionsDataList);
    }

}
