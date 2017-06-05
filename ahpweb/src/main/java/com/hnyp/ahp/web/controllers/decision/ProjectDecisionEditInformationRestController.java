package com.hnyp.ahp.web.controllers.decision;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.web.forms.ProjectDecisionForm;
import com.hnyp.ahp.web.response.AjaxResponseWraper;

@RestController
@RequestMapping("/project/{projectId}/decision/{id}")
public class ProjectDecisionEditInformationRestController {

    @Autowired
    private ProjectDecisionFacade projectDecisionFacade;
    
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public AjaxResponseWraper<Void> updateBasicInformation(@PathVariable long projectId, @PathVariable long id, @Valid ProjectDecisionForm projectDecisionForm, 
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        AjaxResponseWraper<Void> response = new AjaxResponseWraper<>();
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
