package com.hnyp.ahp.web.facades;

import com.hnyp.ahp.web.forms.RegisterForm;

public interface AccountFacade {

    boolean accountExists(String email);
    
    void register(RegisterForm registerForm);
    
}
