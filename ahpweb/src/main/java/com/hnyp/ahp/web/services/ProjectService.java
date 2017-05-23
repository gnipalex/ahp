package com.hnyp.ahp.web.services;

import java.util.List;

import com.hnyp.ahp.web.v2.models.Project;
import com.hnyp.ahp.web.v2.models.User;


public interface ProjectService {

    Project getProject(long id);
    
    Project getProjectByUserAndName(User user, String name);
    
    List<Project> getUserProjects(User user);
    
    void save(Project project);
    
}
