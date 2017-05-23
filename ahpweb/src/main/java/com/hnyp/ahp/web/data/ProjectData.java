package com.hnyp.ahp.web.data;

import java.util.List;

public class ProjectData {

    private long id;
    private String name;
    private String description;
    private List<ProjectDecisionData> projectDecisions;
    
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
    public List<ProjectDecisionData> getProjectDecisions() {
        return projectDecisions;
    }
    public void setProjectDecisions(List<ProjectDecisionData> projectDecisions) {
        this.projectDecisions = projectDecisions;
    }
    
}
