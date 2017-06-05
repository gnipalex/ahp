package com.hnyp.ahp.web.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.core.models.ProjectDecisionStatus;

@Controller
@RequestMapping("/project/{projectId}/decision/{id}")
public class ProjectDecisionStepsController extends AbstractController {

    private static Map<ProjectDecisionStatus, String> PROJECT_DECISION_STATUS_TO_PAGE_MAPPING = new HashMap<>();
    
    static {
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.CREATED, "/edit");
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.CRITERIA_COMPARISON, "/criteriaComparison");
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.VOTING, "/voting");
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.VOTING_FINISHED, "/decide");
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.FINISHED, "/result");
    }
    
    @Autowired
    private ProjectDecisionFacade projectDecisionFacade;
//    @Autowired
//    private ProjectsFacade projectsFacade;
    
    @RequestMapping("")
    public String dispatch(@PathVariable long id, @PathVariable long projectId, Model model) {
        ProjectDecisionData projectDecision = projectDecisionFacade.getProjectDecision(id);
        String redirectUrl = String.format("/project/%s/decision/%s", projectId, id);
        String suffix = PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.get(projectDecision.getStatus());
        return "redirect:" + redirectUrl + suffix;
    }
    
    @RequestMapping("/edit")
    public String edit(@PathVariable long id, @PathVariable long projectId, Model model) {
        model.addAttribute("projectDecisionData", projectDecisionFacade.getProjectDecision(id));
        model.addAttribute("projectId", projectId);
        return "decision/editProjectDecision";
    }
    
    @RequestMapping("/criteriaComparison")
    public String criteriaComparison(@PathVariable long id, @PathVariable long projectId, Model model) {
        model.addAttribute("projectDecisionData", projectDecisionFacade.getProjectDecision(id));
        return "decision/compareCriterias";
    }
    
    @RequestMapping("/voting")
    public String voting(@PathVariable long id, @PathVariable long projectId, Model model) {
        model.addAttribute("projectDecisionData", projectDecisionFacade.getProjectDecision(id));
        return "decision/voting";
    }
    
    @RequestMapping("/decide")
    public String decide(@PathVariable long id, @PathVariable long projectId, Model model) {
        model.addAttribute("projectDecisionData", projectDecisionFacade.getProjectDecision(id));
        return "decision/voting";
    }
    
}
