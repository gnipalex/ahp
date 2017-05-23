package com.hnyp.ahp.web.services;

public interface SessionService {

    <T> T getAttribute(String key);
    
    <T> void setAttribute(String key, T value);
    
}
