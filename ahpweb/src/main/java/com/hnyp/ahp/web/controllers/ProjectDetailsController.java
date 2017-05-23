package com.hnyp.ahp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnyp.ahp.web.facades.ProjectsFacade;

@Controller
@RequestMapping("/project")
public class ProjectDetailsController {

    @Autowired
    private ProjectsFacade projectsFacade;
    
    @RequestMapping("/details/{id}")
    public String details(@PathVariable long id, Model model) {
        model.addAttribute("project", projectsFacade.getProjectDetails(id));
        return "projectDetails";
    }
    
}
