package com.hnyp.ahp.web.controllers;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.facades.ProjectsFacade;
import com.hnyp.ahp.core.services.UserService;

public class AbstractController {

    @Resource
    private ProjectsFacade projectsFacade;
    @Resource
    private UserService userService;
    
    @ModelAttribute("currentUserProjects")
    public List<ProjectData> getUserProjects() {
        if (userService.getCurrentUser() != null) {
            return projectsFacade.getUserProjects();
        }
        return Collections.emptyList();
    }
    
}
