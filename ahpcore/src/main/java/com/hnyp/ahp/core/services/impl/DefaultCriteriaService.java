package com.hnyp.ahp.core.services.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.core.exception.CriteriaAlreadyExistException;
import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.CriteriaService;
import com.hnyp.ahp.core.services.ModelService;

@Transactional
public class DefaultCriteriaService implements CriteriaService {

    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private ModelService modelService;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Criteria> getForProjectDecision(ProjectDecision projectDecision) {
        return sessionFactory.getCurrentSession().createCriteria(Criteria.class)
                  .add(Restrictions.eq("projectDecision", projectDecision))
                  .addOrder(Order.asc("name"))
                  .list();
    }

    @Override
    public void create(Criteria criteria) {
        if (doesCriteriaExist(criteria.getProjectDecision(), criteria.getName())) {
            throw new CriteriaAlreadyExistException("Criteria already exist");
        }
        modelService.save(criteria);
    }

    @Override
    public void updateCriteria(Criteria criteria) {
        Criteria otherCriteria = getCriteria(criteria.getProjectDecision(), criteria.getName());
        if (otherCriteria != null && otherCriteria.getId() != criteria.getId()) {
            throw new CriteriaAlreadyExistException("Criteria already exist");
        }
    }
    
    public boolean doesCriteriaExist(ProjectDecision projectDecision, String name) {
        Criteria criteria = getCriteria(projectDecision, name);
        return criteria != null;
    }
    
    private Criteria getCriteria(ProjectDecision projectDecision, String name) {
        return (Criteria) sessionFactory.getCurrentSession().createCriteria(Criteria.class)
                .add(Restrictions.eq("projectDecision", projectDecision))
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }

    @Override
    public Criteria getForProjectDecisionAndName(ProjectDecision projectDecision, String name) {
        return getCriteria(projectDecision, name);
    }

}
