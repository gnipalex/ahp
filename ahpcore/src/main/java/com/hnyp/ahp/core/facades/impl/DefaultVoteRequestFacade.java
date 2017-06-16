package com.hnyp.ahp.core.facades.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.VoteRequestData;
import com.hnyp.ahp.core.facades.VoteRequestFacade;
import com.hnyp.ahp.core.models.User;
import com.hnyp.ahp.core.models.VoteRequest;
import com.hnyp.ahp.core.services.AlternativeComparisonService;
import com.hnyp.ahp.core.services.UserService;
import com.hnyp.ahp.core.services.VoteRequestService;
import com.hnyp.ahp.core.util.Converters;

@Transactional
public class DefaultVoteRequestFacade implements VoteRequestFacade {

    @Resource
    private VoteRequestService voteRequestService;
    @Resource
    private AlternativeComparisonService alternativeComparisonService;
    @Resource
    private UserService userService;
    @Resource
    private Converter<VoteRequest, VoteRequestData> voteRequestWithDecisionDataConverter;
    
    @Override
    public VoteRequestData getById(long id) {
        VoteRequest voteRequest = voteRequestService.getById(id);
        return voteRequestWithDecisionDataConverter.convert(voteRequest);
    }

    @Override
    public List<VoteRequestData> getActiveRequests() {
        User currentUser = userService.getCurrentUser();
        List<VoteRequest> voteRequests = voteRequestService.getActiveVoteRequests(currentUser);
        return Converters.convertAll(voteRequests, voteRequestWithDecisionDataConverter);
    }

    @Override
    public void accept(long id) {
        VoteRequest voteRequest = voteRequestService.getById(id);
        voteRequestService.accept(voteRequest);
        //TODO should it be here ?
        alternativeComparisonService.createForProjectDecision(voteRequest.getProjectDecision());
    }

    @Override
    public void deny(long id) {
        VoteRequest voteRequest = voteRequestService.getById(id);
        voteRequestService.deny(voteRequest);
    }

}
