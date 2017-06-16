package com.hnyp.ahp.core.facades.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.facades.ProjectFacade;
import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.User;
import com.hnyp.ahp.core.services.ProjectService;
import com.hnyp.ahp.core.services.UserService;

@Transactional
public class DefaultProjectsFacade implements ProjectFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Resource
    private Converter<Project, ProjectData> detailsProjectDataConverter;
    
    @Override
    public List<ProjectData> getUserProjects() {
        User currentUser = userService.getCurrentUser();
        List<Project> userProjects = currentUser.getProjects();
        if (CollectionUtils.isNotEmpty(userProjects)) {
            return userProjects.stream().map(detailsProjectDataConverter::convert)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void createProject(ProjectData projectForm) {
        Project project = new Project();
        project.setName(projectForm.getName());
        project.setDescription(projectForm.getDescription());
        project.setOwner(userService.getCurrentUser());
        projectService.save(project);
    }

    @Override
    public boolean doesProjectExists(String name) {
        return getProjectForCurrentUser(name) != null;
    }
    
    @Override
    public boolean doesProjectExists(long id) {
        return projectService.getProject(id) != null;
    }
    
    private Project getProjectForCurrentUser(String name) {
        User currentUser = userService.getCurrentUser();
        return projectService.getProjectByUserAndName(currentUser, name);
    }

    @Override
    public ProjectData getProjectDetails(long id) {
        Project project = projectService.getProject(id);
        return detailsProjectDataConverter.convert(project);
    }
    
}
