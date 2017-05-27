package com.hnyp.ahp.core.services.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.services.CriteriaService;

@Transactional
public class DefaultCriteriaService implements CriteriaService {

    @Resource
    private SessionFactory sessionFactory;

    @Override
    public Criteria getById(long id) {
        return (Criteria) sessionFactory.getCurrentSession()
                .byId(Criteria.class).getReference(id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Criteria> getForProject(Project project) {
        return sessionFactory.getCurrentSession().createCriteria(Criteria.class)
                .add(Restrictions.eq("project.id", project.getId())).list();
    }

    @Override
    public void save(Criteria criteria) {
        sessionFactory.getCurrentSession().saveOrUpdate(criteria);
    }

}
