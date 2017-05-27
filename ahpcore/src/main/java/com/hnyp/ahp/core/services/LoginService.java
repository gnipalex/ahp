package com.hnyp.ahp.core.services;

public interface LoginService {

    boolean doesUserExist(String email, String password);
    
}
