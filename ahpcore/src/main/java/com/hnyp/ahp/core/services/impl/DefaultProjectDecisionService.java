package com.hnyp.ahp.core.services.impl;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.models.ProjectDecisionStatus;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectDecision> getActiveDecisions(Project project) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProjectDecision.class);
        criteria.add(Restrictions.eq("project", project))
                .add(Restrictions.in("status", Arrays.asList(ProjectDecisionStatus.CREATED, 
                        ProjectDecisionStatus.VOTING, ProjectDecisionStatus.VOTING_FINISHED)))
                .addOrder(Order.desc("modifiedTS"));
        return criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectDecision> getDecisions(Project project, int page, int count) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ProjectDecision.class);
        criteria.add(Restrictions.eq("project", project))
                .addOrder(Order.desc("createdTS"))
                .setMaxResults(count)
                .setFirstResult(count * page - 1);
        return criteria.list();
    }

    @Override
    public void remove(long id) {
        ProjectDecision projectDecision = getProjectDecision(id);
        sessionFactory.getCurrentSession().delete(projectDecision);
    }
    
    

}
