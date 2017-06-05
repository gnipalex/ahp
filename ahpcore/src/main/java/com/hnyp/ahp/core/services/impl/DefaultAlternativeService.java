package com.hnyp.ahp.core.services.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.core.exception.AlternativeAlreadyExistException;
import com.hnyp.ahp.core.models.Alternative;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.AlternativeService;
import com.hnyp.ahp.core.services.ModelService;

public class DefaultAlternativeService implements AlternativeService {

    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private ModelService modelService;
    
    @SuppressWarnings("unchecked")
    @Transactional
    @Override
    public List<Alternative> getForProjectDecision(ProjectDecision projectDecision) {
        return sessionFactory.getCurrentSession().createCriteria(Alternative.class)
            .add(Restrictions.eq("projectDecision", projectDecision))
            .addOrder(Order.asc("name"))
            .list();
    }

    @Override
    public boolean doesAlternativeExist(ProjectDecision projectDecision, String name) {
        Alternative alternative = getAlternative(projectDecision, name);
        return alternative != null;
    }
    
    private Alternative getAlternative(ProjectDecision projectDecision, String name) {
        return (Alternative) sessionFactory.getCurrentSession().createCriteria(Alternative.class)
                .add(Restrictions.eq("projectDecision", projectDecision))
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }

    @Override
    public void create(Alternative alternative) {
        if (doesAlternativeExist(alternative.getProjectDecision(), alternative.getName())) {
            throw new AlternativeAlreadyExistException(String.format("alternative '%s' already exists for project decision with id '%s'", 
                    alternative.getName(), alternative.getProjectDecision().getId()));
        }
        modelService.save(alternative);
    }

    @Override
    public void update(Alternative alternative) {
        Alternative existingAlternative = getAlternative(alternative.getProjectDecision(), alternative.getName());
        if (existingAlternative.getId() != alternative.getId()) {
            throw new AlternativeAlreadyExistException(String.format("alternative '%s' already exists for project decision with id '%s'", 
                    alternative.getName(), alternative.getProjectDecision().getId()));
        }
        modelService.save(alternative);
    }

    @Override
    public Alternative getForProjectDecisionAndName(ProjectDecision projectDecision, String name) {
        return getAlternative(projectDecision, name);
    }
    
}
