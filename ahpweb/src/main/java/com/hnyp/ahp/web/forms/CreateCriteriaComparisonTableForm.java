package com.hnyp.ahp.web.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class CreateCriteriaComparisonTableForm {

    @NotBlank(message="Please provide name")
    @Size(max=50, message="Value is too long, please specify up to 50 characters")
    private String name;
    @Size(max=500, message="Value is too long, please specify up to 500 characters")
    private String description;

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
