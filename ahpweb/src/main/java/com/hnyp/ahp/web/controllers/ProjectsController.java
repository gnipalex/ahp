package com.hnyp.ahp.web.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.facades.ProjectFacade;
import com.hnyp.ahp.web.breadcrumb.Breadcrumb;
import com.hnyp.ahp.web.forms.ProjectForm;

@Controller
@RequestMapping("/projects")
public class ProjectsController extends AbstractController {

    private static final String PROJECT_FORM = "projectForm";
    
    @Autowired
    private ProjectFacade projectsFacade;
    
    
    @ModelAttribute("breadcrumbs")
    public List<Breadcrumb> getBreadcrums() {
        return Arrays.asList(
             new Breadcrumb().setTitle("Home Page").setUrl("/"),
             new Breadcrumb().setTitle("Projects").setUrl("/projects")
        );
    }
    
    @RequestMapping
    public String projects(Model model) {
        model.addAttribute("projects", projectsFacade.getUserProjects());
        return "projects";
    }
    
    @ModelAttribute(PROJECT_FORM)
    public ProjectForm getBlankProjectForm() {
        return new ProjectForm();
    }
    
    @RequestMapping("/create")
    public String createProject() {
        return "createProject";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String doCreateProject(@Valid ProjectForm projectForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (projectsFacade.doesProjectExists(projectForm.getName())) {
            bindingResult.rejectValue("name", String.format("Project with name %s already exists, please specify other name", 
                            projectForm.getName()));
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(PROJECT_FORM, projectForm);
            redirectAttributes.addFlashAttribute(ControllerConstants.BINDING_RESULT_PREFIX + PROJECT_FORM, bindingResult);
            return "redirect:" + "/projects/create";
        }
        
        projectsFacade.createProject(toProjectData(projectForm));
        redirectAttributes.addAttribute("createdProjectName", projectForm.getName());
        
        return "redirect:" + "/projects";
    }
    
    private ProjectData toProjectData(ProjectForm form) {
        ProjectData data = new ProjectData();
        data.setDescription(form.getDescription());
        data.setName(form.getName());
        return data;
    }
    
}
