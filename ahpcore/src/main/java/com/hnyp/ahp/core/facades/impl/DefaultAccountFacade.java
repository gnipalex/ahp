package com.hnyp.ahp.core.facades.impl;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.hnyp.ahp.core.data.RegisterData;
import com.hnyp.ahp.core.facades.AccountFacade;
import com.hnyp.ahp.core.models.User;
import com.hnyp.ahp.core.services.RoleService;
import com.hnyp.ahp.core.services.UserService;

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
    public void register(RegisterData registerForm) {
        User user = new User();
        user.setEmail(registerForm.getEmail());
        user.setFirstName(registerForm.getFirstName());
        user.setLastName(registerForm.getLastName());
        user.setPassword(registerForm.getPassword());
        user.setRoles(Arrays.asList(roleService.getByName("ROLE_USER")));
        userService.save(user);
    }

}
