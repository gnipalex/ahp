package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.User;


public interface ProjectService {

    Project getProject(long id);
    
    Project getProjectByUserAndName(User user, String name);
    
    List<Project> getUserProjects(User user);
    
    void save(Project project);
    
}
