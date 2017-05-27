package com.hnyp.ahp.core.services.impl;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.ProjectDecisionService;

@Transactional
public class DefaultProjectDecisionService implements ProjectDecisionService {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public ProjectDecision getProjectDecision(long id) {
        return (ProjectDecision) sessionFactory.getCurrentSession().get(ProjectDecision.class, id);
    }

    @Override
    public void save(ProjectDecision projectDecision) {
        sessionFactory.getCurrentSession().save(projectDecision);
    }

}
