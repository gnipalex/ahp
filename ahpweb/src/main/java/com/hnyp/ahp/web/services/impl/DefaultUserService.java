package com.hnyp.ahp.web.services.impl;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hnyp.ahp.web.v2.models.User;
import com.hnyp.ahp.web.services.SessionService;
import com.hnyp.ahp.web.services.UserService;

@Transactional
public class DefaultUserService implements UserService {

    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private SessionService sessionService;
    
    @Override
    public User getByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.email=:email");
        query.setString("email", email);
        
        @SuppressWarnings("unchecked")
        Optional<User> user = query.list().stream().findFirst();
        user.ifPresent(u -> {
            Hibernate.initialize(u.getRoles());
        });
        return user.orElse(null);
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            User user = getByEmail(authentication.getName());
            if (user == null) {
                throw new IllegalStateException("authenticated user " + authentication.getName() + " is not found in database");
            }
            return user;
        }
//        User currentUser = sessionService.getAttribute("currentUser");
//        if (currentUser != null) {
//            sessionFactory.getCurrentSession().refresh(currentUser);
//        }
//        return currentUser;
        throw new IllegalStateException("user is not authenticated");
    }

    @Override
    public void setCurrentUser(User user) {
        sessionService.setAttribute("currentUser", user);
    }

}
