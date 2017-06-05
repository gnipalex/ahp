package com.hnyp.ahp.core.data;

import java.util.List;

public class ProjectData {

    private long id;
    private String name;
    private String description;
    private List<ProjectDecisionData> decisions;
    private List<ProjectDecisionData> activeDecisions;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<ProjectDecisionData> getDecisions() {
        return decisions;
    }
    public void setDecisions(List<ProjectDecisionData> decisions) {
        this.decisions = decisions;
    }
    public List<ProjectDecisionData> getActiveDecisions() {
        return activeDecisions;
    }
    public void setActiveDecisions(List<ProjectDecisionData> activeDecisions) {
        this.activeDecisions = activeDecisions;
    }
    
}
