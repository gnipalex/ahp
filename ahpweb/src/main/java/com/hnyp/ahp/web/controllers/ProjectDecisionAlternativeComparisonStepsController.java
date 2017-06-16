package com.hnyp.ahp.web.controllers;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.core.facades.ProjectFacade;

@Controller
@RequestMapping("/project/{projectId}/decision/{decisionId}")
public class ProjectDecisionAlternativeComparisonStepsController {

//    @Resource
//    private VoteRequestFacade voteRequestFacade;
    @Resource
    private ProjectDecisionFacade projectDecisionFacade;
    @Resource
    private ProjectFacade projectFacade;
    
//    @RequestMapping("/info")
//    public String getInformationPage(@PathVariable long decisionId, @PathVariable long projectId, Model model) {
//        ProjectDecisionData projectDecisionData = projectDecisionFacade.getProjectDecision(decisionId);
//        ProjectData projectData = projectFacade.getProjectDetails(projectId);
//        model.addAttribute("projectDecisionData", projectDecisionData);
//        model.addAttribute("projectData", projectData);
//        return "alternativeComparison/decisionInformation";
//    }
    
    @RequestMapping("/voteRequest/{voteRequestId}")
    public String getVoteRequestInfo(@PathVariable long decisionId, @PathVariable long projectId, Model model) {
        ProjectDecisionData projectDecisionData = projectDecisionFacade.getProjectDecision(decisionId);
        ProjectData projectData = projectFacade.getProjectDetails(projectId);
        model.addAttribute("projectDecisionData", projectDecisionData);
        model.addAttribute("projectData", projectData);
        return "alternativeComparison/decisionInformation";
    }
    
    @RequestMapping("/alternativeComparison")
    public String getAlternativesComparisonPage(@PathVariable long decisionId, @PathVariable long projectId, Model model) {
        ProjectDecisionData projectDecisionData = projectDecisionFacade.getProjectDecision(decisionId);
        ProjectData projectData = projectFacade.getProjectDetails(projectId);
        model.addAttribute("projectDecisionData", projectDecisionData);
        model.addAttribute("projectData", projectData);
        
        return "alternativeComparison/compareAlternatives";
    }
    
    
}
