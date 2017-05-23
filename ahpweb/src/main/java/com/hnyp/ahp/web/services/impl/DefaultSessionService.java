package com.hnyp.ahp.web.services.impl;

import java.util.HashMap;
import java.util.Map;

import com.hnyp.ahp.web.services.SessionService;

public class DefaultSessionService implements SessionService {

    private Map<String, Object> storage = new HashMap<>();
    
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String key) {
        return (T) storage.get(key);
    }

    @Override
    public <T> void setAttribute(String key, T value) {
        storage.put(key, value);
    }

}
