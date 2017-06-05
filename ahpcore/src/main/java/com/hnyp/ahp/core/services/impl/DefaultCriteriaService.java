package com.hnyp.ahp.core.services.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.CriteriaService;

@Transactional
public class DefaultCriteriaService implements CriteriaService {

    @Resource
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Criteria> getForProjectDecision(ProjectDecision projectDecision) {
        return sessionFactory.getCurrentSession().createCriteria(Criteria.class)
                  .add(Restrictions.eq("projectDecision", projectDecision))
                  .addOrder(Order.asc("name"))
                  .list();
    }

}
