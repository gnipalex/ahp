package com.hnyp.ahp.web.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.data.VoteRequestData;
import com.hnyp.ahp.core.exception.VoteRequestAccessException;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.core.facades.ProjectFacade;
import com.hnyp.ahp.core.facades.VoteRequestFacade;

@Controller
@RequestMapping("/voteRequest")
public class VoteRequestDetailsController {

    @Resource
    private ProjectFacade projectFacade; 
    @Resource
    private ProjectDecisionFacade projectDecisionFacade;
    @Resource
    private VoteRequestFacade voteRequestFacade;
    
    @RequestMapping("/{id}")
    public String getInformationPage(@PathVariable long id, Model model) {
//        ProjectData projectData = projectFacade.getProjectDetails(projectId);
        VoteRequestData voteRequestData = voteRequestFacade.getById(id);
        model.addAttribute("voteRequestData", voteRequestData);
        long decisionId = voteRequestData.getProjectDecision().getId();
        ProjectDecisionData projectDecisionData = projectDecisionFacade.getProjectDecision(decisionId);
        model.addAttribute("projectDecisionData", projectDecisionData);
        return "voteRequest/voteRequestConfirmation";
    }
    
    @RequestMapping(value = "/{id}/accept", method = RequestMethod.POST)
    public String acceptRequest(@PathVariable long id) {
        voteRequestFacade.accept(id);
        VoteRequestData voteRequestData = voteRequestFacade.getById(id);
        ProjectDecisionData projectDecisionData = voteRequestData.getProjectDecision();
        return "redirect:" + String.format("/project/%s/decision/%s/alternativeComparison", 
                projectDecisionData.getProjectId(),
                projectDecisionData.getId() );
    }
    
    @RequestMapping(value = "/{id}/deny", method = RequestMethod.POST)
    public String denyRequest(@PathVariable long id, RedirectAttributes redirectAttributes) {
        voteRequestFacade.deny(id);
        redirectAttributes.addFlashAttribute("successMessage", "Request denied");
        return "redirect:/"; 
    }
    
    @ExceptionHandler(VoteRequestAccessException.class) 
    public String handleVoteRequestAccessException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", "Can not access vote request");
        return "redirect:/";
    }
}
