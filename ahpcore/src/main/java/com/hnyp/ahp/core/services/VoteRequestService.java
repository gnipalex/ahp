package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.models.User;
import com.hnyp.ahp.core.models.VoteRequest;

public interface VoteRequestService {

    void processRequestsForDecision(ProjectDecision decision);
    
    VoteRequest getById(long id);
    
    VoteRequest getByToken(String token);
    
    List<VoteRequest> getActiveVoteRequests(User user);
    
    void accept(VoteRequest voteRequest);
    
    void deny(VoteRequest voteRequest);
    
}
