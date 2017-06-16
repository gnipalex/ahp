package com.hnyp.ahp.web.controllers;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.hnyp.ahp.core.data.ProjectData;
import com.hnyp.ahp.core.data.ProjectDecisionData;
import com.hnyp.ahp.core.data.VoteRequestData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.core.facades.ProjectFacade;
import com.hnyp.ahp.core.facades.VoteRequestFacade;
import com.hnyp.ahp.core.services.UserService;

public class AbstractController {

    @Resource
    private ProjectFacade projectsFacade;
    @Resource
    private UserService userService;
    @Resource
    private VoteRequestFacade voteRequestFacade;
    
//    private ProjectDecisionFacade
    
    @ModelAttribute("currentUserProjects")
    public List<ProjectData> getUserProjects() {
        if (userService.getCurrentUser() != null) {
            return projectsFacade.getUserProjects();
        }
        return Collections.emptyList();
    }
    
    @ModelAttribute("activeVoteRequests")
    public List<VoteRequestData> getActiveVoteRequests() {
        if (userService.getCurrentUser() != null) {
            return voteRequestFacade.getActiveRequests();
        }
        return Collections.emptyList();
    }
    
//    @ModelAttribute("activeProjectDecisions")
//    public List<ProjectDecisionData> getActiveProjectDecisions() {
//        if (userService.getCurrentUser() != null) {
//            return 
//        }
//    }
    
}
