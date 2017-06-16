package com.hnyp.ahp.core.data;

public class ProjectDecisionEditStatusData {

    private boolean alternativesProvided;
    private boolean criteriasProvided;
    private boolean voteRequestsProvided;

    public boolean isAlternativesProvided() {
        return alternativesProvided;
    }

    public void setAlternativesProvided(boolean alternativesProvided) {
        this.alternativesProvided = alternativesProvided;
    }

    public boolean isCriteriasProvided() {
        return criteriasProvided;
    }

    public void setCriteriasProvided(boolean criteriasProvided) {
        this.criteriasProvided = criteriasProvided;
    }

    public boolean isVoteRequestsProvided() {
        return voteRequestsProvided;
    }

    public void setVoteRequestsProvided(boolean voteRequestsProvided) {
        this.voteRequestsProvided = voteRequestsProvided;
    }

}
