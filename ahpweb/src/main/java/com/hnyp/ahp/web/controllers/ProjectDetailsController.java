package com.hnyp.ahp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.facades.ProjectsFacade;

@Controller
@RequestMapping("/project")
public class ProjectDetailsController extends AbstractController {

    @Autowired
    private ProjectsFacade projectsFacade;
    
    @RequestMapping("/{id}/details")
    public String details(@PathVariable long id, Model model, RedirectAttributes redirectAttributes) {
        // TODO move to filter
        if (!projectsFacade.doesProjectExists(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", String.format("Project with id '%s' not found", id));
            return "redirect:" + "/";
        }
        model.addAttribute("project", projectsFacade.getProjectDetails(id));
        return "projectDetails";
    }
    
}
