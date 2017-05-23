package com.hnyp.ahp.web.services.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.web.models.CriteriaComparisonVersion;
import com.hnyp.ahp.web.models.Project;
import com.hnyp.ahp.web.services.CriteriaComparisonVersionService;

@Transactional
public class DefaultCriteriaComparisonVersionService implements CriteriaComparisonVersionService {

    @Resource
    private SessionFactory sessionFactory;
    
    @Override
    public CriteriaComparisonVersion getVersion(Project project, long id) {
        Criterion searchCriterion = Restrictions.and(Restrictions.eq("project.id", project.getId()), Restrictions.eq("id", id));
        return (CriteriaComparisonVersion) sessionFactory.getCurrentSession().createCriteria(CriteriaComparisonVersion.class).add(searchCriterion).uniqueResult();
    }

}
