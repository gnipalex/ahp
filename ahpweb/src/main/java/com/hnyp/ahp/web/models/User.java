package com.hnyp.ahp.web.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User extends AbstractEntity {
    
    @Column(unique=true, nullable=false)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column
    private String firstName;
    
    @Column
    private String lastName;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinColumn(referencedColumnName="id")
    private Collection<Role> roles;
    
    @OneToMany(mappedBy = "owner")
    private List<Project> projects;
    
    @OneToMany(mappedBy = "registeredUser")
    private List<VoteRequest> voteRequests;
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

}
