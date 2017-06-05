package com.hnyp.ahp.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.web.forms.ProjectDecisionForm;
import com.hnyp.ahp.web.response.ResponseWraper;

@Controller
@RequestMapping("/project/{projectId}/decision/{id}")
public class ProjectDecisionEditStepController {
    
    @Autowired
    private ProjectDecisionFacade projectDecisionFacade;


    @RequestMapping("/edit")
    public String edit(@PathVariable long id, @PathVariable long projectId, Model model) {
        model.addAttribute("projectDecisionData", projectDecisionFacade.getProjectDecision(id));
        return "decision/editProjectDecision";
    }
    
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseWraper<Void> updateBasicInformation(@PathVariable long projectId, @PathVariable long id, @Valid ProjectDecisionForm projectDecisionForm, 
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ResponseWraper<Void> response = new ResponseWraper<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(fe -> {
                response.addErrorMessage(fe.getField(), fe.getDefaultMessage());
            });
            response.setError(true);
        } else {
            ProjectDecisionData projectDecisionData = toProjectDecisionData(projectDecisionForm);
            projectDecisionData.setId(id);
            projectDecisionFacade.update(projectDecisionData);
        }
        return response;
    }
    
    private ProjectDecisionData toProjectDecisionData(ProjectDecisionForm form) {
        ProjectDecisionData data = new ProjectDecisionData();
        data.setDescription(form.getDescription());
        data.setGoal(form.getGoal());
        return data;
    }
    
}