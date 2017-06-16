package com.hnyp.ahp.core.data;

import java.util.List;

import com.hnyp.ahp.core.models.ProjectDecisionStatus;

public class ProjectDecisionData {

    private long id;
    private long projectId;
    private String goal;
    private String description;
    private ProjectDecisionStatus status;
    private List<CriteriaData> criterias;
    private List<AlternativeData> alternatives;
    private List<VoteRequestData> voteRequests;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CriteriaData> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<CriteriaData> criterias) {
        this.criterias = criterias;
    }

    public List<AlternativeData> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<AlternativeData> alternatives) {
        this.alternatives = alternatives;
    }

    public List<VoteRequestData> getVoteRequests() {
        return voteRequests;
    }

    public void setVoteRequests(List<VoteRequestData> voteRequests) {
        this.voteRequests = voteRequests;
    }

    public ProjectDecisionStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectDecisionStatus status) {
        this.status = status;
    }

}
