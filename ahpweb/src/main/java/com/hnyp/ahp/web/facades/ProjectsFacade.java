package com.hnyp.ahp.web.facades;

import java.util.List;

import com.hnyp.ahp.web.data.ProjectData;
import com.hnyp.ahp.web.forms.ProjectForm;

public interface ProjectsFacade {

    List<ProjectData> getUserProjects();
    
    boolean doesProjectExists(String name);
    
    void createProject(ProjectForm projectForm);
    
    ProjectData getProjectDetails(String name);
    
    ProjectData getProjectDetails(long id);
    
}
