package com.hnyp.ahp.core.facades.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.ProjectDecisionService;

@Transactional
public class DefaultProjectDecisionFacade implements ProjectDecisionFacade {

    @Autowired
    private ProjectDecisionService projectDecisionService;
    
    @Resource
    private Converter<ProjectDecision, ProjectDecisionData> projectDecisionDataDetailConverter;
    
    @Override
    public ProjectDecisionData getProjectDecision(long id) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(id);
        return projectDecisionDataDetailConverter.convert(projectDecision);
    }

    @Override
    public void createProjectDecision(long projectId, ProjectDecisionData data) {
        // TODO Auto-generated method stub
        
    }
    
    

    
    
    
}
