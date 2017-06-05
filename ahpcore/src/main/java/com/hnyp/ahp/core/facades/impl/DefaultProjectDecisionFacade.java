package com.hnyp.ahp.core.facades.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.models.ProjectDecisionStatus;
import com.hnyp.ahp.core.services.ProjectDecisionService;
import com.hnyp.ahp.core.services.ProjectService;

@Transactional
public class DefaultProjectDecisionFacade implements ProjectDecisionFacade {

    @Autowired
    private ProjectDecisionService projectDecisionService;
    @Autowired
    private ProjectService projectService;
    
    @Resource
    private Converter<ProjectDecision, ProjectDecisionData> projectDecisionDataDetailConverter;
    
    @Override
    public ProjectDecisionData getProjectDecision(long id) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(id);
        return projectDecisionDataDetailConverter.convert(projectDecision);
    }

    @Override
    public void createProjectDecision(long projectId, ProjectDecisionData data) {
        Project project = projectService.getProject(projectId);
        if (project == null) {
            throw new IllegalArgumentException(String.format("project with id '%s' doesn't exist", projectId));
        }
        ProjectDecision projectDecision = createProjectDecision(data, project);
        projectDecisionService.save(projectDecision);
    }

    private ProjectDecision createProjectDecision(ProjectDecisionData data, Project project) {
        ProjectDecision projectDecision = new ProjectDecision();
        projectDecision.setProject(project);
        projectDecision.setGoal(data.getGoal());
        projectDecision.setDescription(data.getDescription());
        projectDecision.setStatus(ProjectDecisionStatus.CREATED);
        Date creationDate = new Date();
        projectDecision.setCreatedTS(creationDate);
        projectDecision.setModifiedTS(creationDate);
        return projectDecision;
    }

    @Override
    public ProjectDecisionData getProjectDecision(long projectId, long decisionId) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(decisionId);
        if (projectDecision != null && projectDecision.getProject().getId() == projectId) {
            return projectDecisionDataDetailConverter.convert(projectDecision);
        }
        return null;
    }

    @Override
    public void update(ProjectDecisionData projectDecisionData) {
        ProjectDecision projectDecision = projectDecisionService.getProjectDecision(projectDecisionData.getId());
        projectDecision.setGoal(projectDecisionData.getGoal());
        projectDecision.setDescription(projectDecisionData.getDescription());
        projectDecision.setModifiedTS(new Date());
        projectDecisionService.save(projectDecision);
    }

    @Override
    public void remove(long projectDecisionId) {
        projectDecisionService.remove(projectDecisionId);
    }
    
}
