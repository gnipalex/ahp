package com.hnyp.ahp.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class VoteRequest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="projectDecision_id", referencedColumnName="id", nullable = false)
    private ProjectDecision projectDecision;
    
    @Column(nullable=false)
    private String email;
    
    @Column(unique=true, nullable=false)
    private String token;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private VoteRequestStatus status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registeredUser_id", referencedColumnName="id")
    private User registeredUser;

    public ProjectDecision getProjectDecision() {
        return projectDecision;
    }

    public void setProjectDecision(ProjectDecision projectDecision) {
        this.projectDecision = projectDecision;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public VoteRequestStatus getStatus() {
        return status;
    }

    public void setStatus(VoteRequestStatus status) {
        this.status = status;
    }

    public User getRegisteredUser() {
        return registeredUser;
    }

    public void setRegisteredUser(User registeredUser) {
        this.registeredUser = registeredUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
