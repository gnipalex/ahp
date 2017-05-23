package com.hnyp.ahp.web.facades.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.hnyp.ahp.web.facades.AccountFacade;
import com.hnyp.ahp.web.forms.RegisterForm;
import com.hnyp.ahp.web.services.RoleService;
import com.hnyp.ahp.web.services.UserService;
import com.hnyp.ahp.web.v2.models.User;

@Transactional
public class DefaultAccountFacade implements AccountFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    
    @Override
    public boolean accountExists(String email) {
        return userService.getByEmail(email) != null;
    }

    @Override
    public void register(RegisterForm registerForm) {
        User user = new User();
        user.setEmail(registerForm.getEmail());
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setPassword(registerForm.getPassword());
        user.setRoles(Arrays.asList(roleService.getByName("ROLE_USER")));
        userService.save(user);
    }

}
