package com.hnyp.ahp.core.facades;

import com.hnyp.ahp.core.data.RegisterData;

public interface AccountFacade {

    boolean accountExists(String email);
    
    void register(RegisterData registerForm);
    
}
