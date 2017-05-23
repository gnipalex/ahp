package com.hnyp.ahp.web.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class RegisterForm {

    @Email(message = "Please provide valid email")
    @NotBlank(message = "Please provide email")
    private String email;
    @NotBlank(message = "Please provide password")
    private String password;
    @NotBlank(message = "Please provide your first name")
    private String firstName;
    @NotBlank(message = "Please provide your last name") 
    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
