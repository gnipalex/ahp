package com.hnyp.ahp.core.data;

import com.hnyp.ahp.core.models.VoteRequestStatus;

public class VoteRequestData {

    private long id;
    private String email;
    private String name;
    private String comment;
    private String token;
    private VoteRequestStatus status;
    private UserData registeredUser;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public UserData getRegisteredUser() {
        return registeredUser;
    }
    public void setRegisteredUser(UserData registeredUser) {
        this.registeredUser = registeredUser;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
