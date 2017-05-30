package com.hnyp.ahp.web.forms;

public class CriteriaForm {

    private Long projectDecisionId;
    private Long id;
    private String name;
    private String description;

    public Long getProjectDecisionId() {
        return projectDecisionId;
    }

    public void setProjectDecisionId(Long projectDecisionId) {
        this.projectDecisionId = projectDecisionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
