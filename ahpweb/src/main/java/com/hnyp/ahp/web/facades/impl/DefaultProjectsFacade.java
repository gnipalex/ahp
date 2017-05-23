package com.hnyp.ahp.web.facades.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.web.data.ProjectData;
import com.hnyp.ahp.web.facades.ProjectsFacade;
import com.hnyp.ahp.web.forms.ProjectForm;
import com.hnyp.ahp.web.services.ProjectService;
import com.hnyp.ahp.web.services.UserService;
import com.hnyp.ahp.web.v2.models.Project;
import com.hnyp.ahp.web.v2.models.User;

@Transactional
public class DefaultProjectsFacade implements ProjectsFacade {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ProjectService projectService;
    
    @Resource
    private Converter<Project, ProjectData> basicProjectDataConverter;
    @Resource
    private Converter<Project, ProjectData> detailsProjectDataConverter;
    
    @Override
    public List<ProjectData> getUserProjects() {
        User currentUser = userService.getCurrentUser();
        List<Project> userProjects = currentUser.getProjects();
        if (CollectionUtils.isNotEmpty(userProjects)) {
            return userProjects.stream().map(basicProjectDataConverter::convert)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
    
//    private ProjectData convertBasicInformation(Project project) {
//        ProjectData projectData = new ProjectData();
//        projectData.setId(project.getId());
//        projectData.setName(project.getName());
//        projectData.setDescription(project.getDescription());
//        return projectData;
//    }

    @Override
    public void createProject(ProjectForm projectForm) {
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
    
    private Project getProjectForCurrentUser(String name) {
        User currentUser = userService.getCurrentUser();
        return projectService.getProjectByUserAndName(currentUser, name);
    }

    @Override
    public ProjectData getProjectDetails(String name) {
        Project project = getProjectForCurrentUser(name);
        return detailsProjectDataConverter.convert(project);
    }
    
//    private ProjectData getProjectDetails(Project project) {
//        ProjectData projectData = convertBasicInformation(project);
//        List<ProjectDecisionData> projectDecisionDataList = new ArrayList<>();
//        
//        for (ProjectDecision projectDecision  : project.getDecisions()) {
//            ProjectDecisionData data = new ProjectDecisionData();
//            convertBasicInformation(projectDecision, data);
//            projectDecisionDataList.add(data);
//        }
//        
//        projectData.setProjectDecisions(projectDecisionDataList);
//
//        return projectData;
//    }
    
//    private void convertBasicInformation(ProjectDecision projectDecision, ProjectDecisionData projectDecisionData) {
//        projectDecisionData.setDescription(projectDecision.getDescription());
//        projectDecisionData.setGoal(projectDecision.getGoal());
//        projectDecisionData.setId(projectDecision.getId());
//    }

    @Override
    public ProjectData getProjectDetails(long id) {
        Project project = projectService.getProject(id);
        return detailsProjectDataConverter.convert(project);
    }
    
//    public static class Converters<S, T> {
//        
//        private List<Function<S, T>> converters;
//        
//        public static <S, T> Converters withConverters(List<Function<S, T>> converters) {
//            Converters instance = new Converters<>();
//            instance.converters = converters;
//            return instance;
//        }
//        
//        public static <>
//        
//        public List<T> convert
//    }
    
    

}
