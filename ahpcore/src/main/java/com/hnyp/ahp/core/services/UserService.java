package com.hnyp.ahp.core.services;

import com.hnyp.ahp.core.models.User;

public interface UserService {
    
    User getByEmail(String email);
    
    void save(User user);
    
    User getCurrentUser();
    
    void setCurrentUser(User user);

}
