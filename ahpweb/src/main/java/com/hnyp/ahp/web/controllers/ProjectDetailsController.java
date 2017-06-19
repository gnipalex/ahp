package com.hnyp.ahp.web.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.facades.ProjectFacade;
import com.hnyp.ahp.web.breadcrumb.Breadcrumb;

@Controller
@RequestMapping("/project")
public class ProjectDetailsController extends AbstractController {

    @Autowired
    private ProjectFacade projectsFacade;
    
    @RequestMapping("/{id}/details")
    public String details(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        // TODO move to filter
        if (!projectsFacade.doesProjectExists(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", String.format("Project with id '%s' not found", id));
            return "redirect:" + "/";
        }
        ProjectData projectData = projectsFacade.getProjectDetails(id);
        
        model.addAttribute("project", projectData);
        
        model.addAttribute("breadcrumbs", Arrays.asList(
                new Breadcrumb().setTitle("Home Page").setUrl("/"),
                new Breadcrumb().setTitle("Projects").setUrl("/projects"),
                new Breadcrumb().setTitle(projectData.getName())
        ));
        
        return "project/projectDetails";
    }
    
}
