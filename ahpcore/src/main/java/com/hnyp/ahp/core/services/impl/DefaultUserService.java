package com.hnyp.ahp.core.services.impl;

import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.hnyp.ahp.core.models.User;
import com.hnyp.ahp.core.services.SessionService;
import com.hnyp.ahp.core.services.UserService;

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
        User currentUser = sessionService.getAttribute("currentUser");
        if (currentUser == null) {
            throw new IllegalStateException("user is not authenticated");
        }
        sessionFactory.getCurrentSession().refresh(currentUser);
        return currentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        sessionService.setAttribute("currentUser", user);
    }

}
