package com.hnyp.ahp.core.facades;

import java.util.List;

import com.hnyp.ahp.core.data.ProjectData;

public interface ProjectsFacade {

    List<ProjectData> getUserProjects();
    
    boolean doesProjectExists(String name);
    
    void createProject(ProjectData projectForm);
    
    ProjectData getProjectDetails(String name);
    
    ProjectData getProjectDetails(long id);
    
}
