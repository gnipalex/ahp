package com.hnyp.ahp.core.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnyp.ahp.core.models.Role;
import com.hnyp.ahp.core.services.RoleService;

@Transactional
public class DefaultRoleService implements RoleService {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Role getByName(String name) {
        Criteria searchCriteria = getCurrentSession().createCriteria(Role.class)
            .add(Restrictions.eq("name", name));
        List<Role> roles = searchCriteria.list();
        return roles.isEmpty() ? null : roles.get(0); 
    }
    
    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
