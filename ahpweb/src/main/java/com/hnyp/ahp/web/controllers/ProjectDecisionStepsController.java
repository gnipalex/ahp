package com.hnyp.ahp.web.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.data.ProjectDecisionEditStatusData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.core.models.ProjectDecisionStatus;

@Controller
@RequestMapping("/project/{projectId}/decision/{id}")
public class ProjectDecisionStepsController extends AbstractController {

    private static Map<ProjectDecisionStatus, String> PROJECT_DECISION_STATUS_TO_PAGE_MAPPING = new HashMap<>();
    
    static {
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.CREATED, "/edit");
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.CRITERIA_COMPARISON, "/compareCriterias");
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.VOTING, "/voting");
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.VOTING_FINISHED, "/decide");
        PROJECT_DECISION_STATUS_TO_PAGE_MAPPING.put(ProjectDecisionStatus.FINISHED, "/result");
    }
    
    @Autowired
    private ProjectDecisionFacade projectDecisionFacade;

    @ModelAttribute
    public long projectId(@PathVariable long projectId) {
        return projectId;
    }
    
    @ModelAttribute
    public long projectDecisionId(@PathVariable long id) {
        return id;
    }
    
    @ModelAttribute
    public ProjectDecisionData projectDecisionData(@PathVariable long id) {
        return projectDecisionFacade.getProjectDecision(id);
    }
    
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
        return "decision/editProjectDecision";
    }
    
    @RequestMapping(value = "/finishEdit", method = RequestMethod.POST)
    public String finishEditing(@PathVariable long id, @PathVariable long projectId, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ProjectDecisionEditStatusData statusData = projectDecisionFacade.getProjectDecisionEditStatusData(id);
        if (!(statusData.isAlternativesProvided() 
                && statusData.isCriteriasProvided() 
                && statusData.isVoteRequestsProvided())) {
            redirectAttributes.addFlashAttribute(statusData);
            redirectAttributes.addFlashAttribute("errorMessage", "Not all necessary data is provided to continue decision");
            return "redirect:" + String.format("/project/%s/decision/%s/edit", projectId, id);
        }
        projectDecisionFacade.finishEditProjectDecision(id);
        redirectAttributes.addFlashAttribute("successMessage", "Decision editing is finished");
        return "redirect:" + String.format("/project/%s/decision/%s/compareCriterias", projectId, id);
    }
    
    @RequestMapping("/compareCriterias")
    public String criteriaComparison(@PathVariable long id, @PathVariable long projectId, Model model) {
        return "decision/compareCriterias";
    }
    
    @RequestMapping(value = "/finishCriteriaComparison", method = RequestMethod.POST)
    public String finishCriteriaComparison(@PathVariable long id, @PathVariable long projectId, Model model) {
        projectDecisionFacade.startProjectDecision(id);
        return String.format("/project/%s/decision/%s/voting", projectId, id);
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
