package com.hnyp.ahp.web.services;

import com.hnyp.ahp.web.v2.models.User;

public interface UserService {
    
    User getByEmail(String email);
    
    void save(User user);
    
    User getCurrentUser();
    
    void setCurrentUser(User user);

}
