package com.hnyp.ahp.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.core.facades.ProjectsFacade;
import com.hnyp.ahp.web.forms.ProjectDecisionForm;

@Controller
public class ProjectDecisionController extends AbstractController {

    @Autowired
    private ProjectDecisionFacade projectDecisionFacade;
    @Autowired
    private ProjectsFacade projectsFacade;
    
    @RequestMapping("/project/{projectId}/decision/{id}")
    public String projectDecision(@PathVariable long id, Model model) {
        model.addAttribute("projectDecisionData", projectDecisionFacade.getProjectDecision(id));
        return "projectDecision";
    }
    
    //// probably it should be in project controller ?
    @ModelAttribute("projectDecisionForm")
    public ProjectDecisionForm getForm() {
        return new ProjectDecisionForm();
    }
    
    @RequestMapping("/project/{projectId}/decision/create")
    public String createDecision(@PathVariable long projectId, Model model) {
        // check if project exist for current user
        model.addAttribute("projectId", projectId);
        return "createProjectDecision";
    }
    
    @RequestMapping(value = "/project/{projectId}/decision/create", method = RequestMethod.POST)
    public String doCreateDecision(@Valid ProjectDecisionForm projectDecisionForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        boolean projectExist = true;
        if (!projectExist) {
            // TODO handle attribute in jsp
            redirectAttributes.addFlashAttribute("errorMessage", "Project not found");
            return "redirect:" + "/projects";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("projectDecisionForm", projectDecisionForm);
            redirectAttributes.addFlashAttribute(ControllerConstants.BINDING_RESULT_PREFIX + "projectDecisionForm", bindingResult);
            return "redirect:" + String.format("/project/%s/decision/create", projectDecisionForm.getProjectId());
        }
        
        ProjectDecisionData projectDecisionData = toProjectDecisionData(projectDecisionForm);
        projectDecisionFacade.createProjectDecision(projectDecisionForm.getProjectId(), projectDecisionData);
        
        redirectAttributes.addAttribute("successMessage", String.format("Project decision '%' was created", projectDecisionForm.getGoal()));
        return "redirect:" + "/project/" + projectDecisionForm.getProjectId();
    }
    
    private ProjectDecisionData toProjectDecisionData(ProjectDecisionForm form) {
        ProjectDecisionData data = new ProjectDecisionData();
        data.setDescription(form.getDescription());
        data.setGoal(form.getGoal());
        return data;
    }
    
}
