package com.hnyp.ahp.web.spring;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hnyp.ahp.web.services.UserService;
import com.hnyp.ahp.web.v2.models.Role;
import com.hnyp.ahp.web.v2.models.User;

public class CustomUserDetailsService implements UserDetailsService  {

    @Resource
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByEmail(userName);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("user with login %s does not exist" , userName));
        }
        return createUser(user);
    }
    
    private org.springframework.security.core.userdetails.User createUser(User user) {
        String userName = user.getEmail();
        String password = user.getPassword();
        return new org.springframework.security.core.userdetails.User(userName, password, getAuthorities(user));
    }
    
    private Collection<GrantedAuthority> getAuthorities(User user) {
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            return user.getRoles().stream()
                        .map(Role::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
