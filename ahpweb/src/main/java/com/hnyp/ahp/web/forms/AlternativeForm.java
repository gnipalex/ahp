package com.hnyp.ahp.web.forms;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class AlternativeForm {

    @NotBlank(message = "Please provide value")
    @Size(max=50, message = "Value should not exceed 50 characters")
    private String name;
    @Size(max=500, message = "Value should not exceed 500 characters")
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
