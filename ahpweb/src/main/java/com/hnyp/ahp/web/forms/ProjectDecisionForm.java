package com.hnyp.ahp.web.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class ProjectDecisionForm {

    @NotBlank(message = "You should provide a goal")
    private String goal;

    @Size(max = 500, message = "Value is too long, please shorten it too be not more than 500 characters")
    private String description;

    private long projectId;

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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

}
