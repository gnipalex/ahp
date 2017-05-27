package com.hnyp.ahp.web.facades.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.web.data.ProjectDecisionData;
import com.hnyp.ahp.web.facades.ProjectDecisionFacade;
import com.hnyp.ahp.web.forms.ProjectDecisionForm;
import com.hnyp.ahp.web.services.ProjectDecisionService;
import com.hnyp.ahp.web.v2.models.ProjectDecision;

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
    public void createProjectDecision(ProjectDecisionForm form) {
        // TODO Auto-generated method stub
        
    }
    
    

    
    
    
}
