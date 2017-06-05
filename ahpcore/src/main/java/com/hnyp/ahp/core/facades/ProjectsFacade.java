package com.hnyp.ahp.core.facades;

import java.util.List;

import com.hnyp.ahp.core.data.ProjectData;

public interface ProjectsFacade {

    List<ProjectData> getUserProjects();
    
    boolean doesProjectExists(String name);
    
    boolean doesProjectExists(long id);
    
    void createProject(ProjectData projectForm);
    
    ProjectData getProjectDetails(long id);
    
}
