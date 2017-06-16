package com.hnyp.ahp.core.services.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.core.exception.VoteRequestAccessException;
import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.models.User;
import com.hnyp.ahp.core.models.VoteRequest;
import com.hnyp.ahp.core.models.VoteRequestStatus;
import com.hnyp.ahp.core.services.EmailService;
import com.hnyp.ahp.core.services.ModelService;
import com.hnyp.ahp.core.services.UserService;
import com.hnyp.ahp.core.services.VoteRequestService;
import com.hnyp.ahp.core.services.data.EmailMessage;

public class DefaultVoteRequestService implements VoteRequestService {

    @Resource
    private UserService userService;
    @Resource
    private ModelService modelService;
    @Resource
    private EmailService emailService;
    @Resource
    private SessionFactory sessionFactory;
    
    @Override
    public void sendVoteRequestsForDecision(ProjectDecision decision) {
        List<VoteRequest> voteRequests = decision.getVoteRequests();
        if (CollectionUtils.isNotEmpty(voteRequests)) {
            voteRequests.forEach(this::processVoteRequest);
        }
    }
    
    private void processVoteRequest(VoteRequest voteRequest) {
        User user = userService.getByEmail(voteRequest.getEmail());
        if (user != null) {
            voteRequest.setRegisteredUser(user);
        }
        sendEmail(voteRequest);
        voteRequest.setStatus(VoteRequestStatus.SENT);
        modelService.save(voteRequest);
    }
    
    private void sendEmail(VoteRequest request) {
        EmailMessage message = new EmailMessage();
        message.setRecipient(request.getEmail());
        message.setSender("noreply@dssahp");
        message.setSubject(String.format("DSS AHP : Vote Request"));
        message.setBody(getMessageBody(request));
        emailService.sendEmail(message);
    }
    
    // TODO use velocity templating
    private String getMessageBody(VoteRequest request) {
        StringBuilder message = new StringBuilder();
        message.append(String.format("Hi %s,\n", request.getName()));
        message.append(String.format("You've been requested to help with decision '%s' by %s", 
                request.getProjectDecision().getGoal(), 
                getOwnerName(request)));
        message.append("\n");
        message.append(String.format("Please follow the link %s", prepareVoteRequestLink(request)));
        return message.toString();
    }
    
    private String getOwnerName(VoteRequest voteRequest) {
        return Optional.ofNullable(voteRequest.getProjectDecision())
            .map(ProjectDecision::getProject)
            .map(Project::getOwner)
            .map(u -> u.getFirstName() + " " + u.getLastName())
            .orElse("");
    }
    
    // TODO should be configurable
    private String prepareVoteRequestLink(VoteRequest voteRequest) {
        String hostAndApplication = "http://localhost:8080/ahpweb";
        String endpoint = "/voteRequest/";
        return hostAndApplication + endpoint + voteRequest.getToken();
    }

    @Override
    public VoteRequest getById(long id) {
        return modelService.getById(VoteRequest.class, id);
    }

    @Override
    public VoteRequest getByToken(String token) {
        return (VoteRequest) sessionFactory.getCurrentSession().createCriteria(VoteRequest.class)
                .add(Restrictions.eq("token", token))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<VoteRequest> getActiveVoteRequests(User user) {
        return sessionFactory.getCurrentSession().createCriteria(VoteRequest.class)
                .add(Restrictions.in("status", new Object[] {VoteRequestStatus.CONFIRMED, VoteRequestStatus.CREATED, VoteRequestStatus.SENT}))
                .add(Restrictions.eq("registeredUser", user))
                .list();
    }

    @Override
    public void accept(VoteRequest voteRequest) {
        User currentUser = userService.getCurrentUser();
        if ( voteRequest.getRegisteredUser() == null 
                || currentUser.getId() != voteRequest.getRegisteredUser().getId()) {
            String userName = currentUser != null ? currentUser.getEmail() : "";
            throw new VoteRequestAccessException(String.format("Current user [%s] has no access to vote request %s", userName, voteRequest.getId()));
        }
        voteRequest.setStatus(VoteRequestStatus.CONFIRMED);
        modelService.save(voteRequest);
    }

    @Override
    public void deny(VoteRequest voteRequest) {
        User currentUser = userService.getCurrentUser();
        if ( voteRequest.getRegisteredUser() == null 
                || currentUser.getId() != voteRequest.getRegisteredUser().getId()) {
            String userName = currentUser != null ? currentUser.getEmail() : "";
            throw new VoteRequestAccessException(String.format("Current user [%s] has no access to vote request %s", userName, voteRequest.getId()));
        }
        voteRequest.setStatus(VoteRequestStatus.DENIED);
        modelService.save(voteRequest);
    }
    
}
