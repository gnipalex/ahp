package com.hnyp.ahp.web.services;

public interface LoginService {

    boolean doesUserExist(String email, String password);
    
}
