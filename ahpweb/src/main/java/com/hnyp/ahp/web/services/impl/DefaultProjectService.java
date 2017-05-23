package com.hnyp.ahp.web.services.impl;

import static org.hibernate.criterion.Restrictions.and;
import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.web.services.ProjectService;
import com.hnyp.ahp.web.v2.models.Project;
import com.hnyp.ahp.web.v2.models.User;

@Transactional
public class DefaultProjectService implements ProjectService {

    @Resource
    private SessionFactory sessionFactory;
    
    @Override
    public Project getProject(long id) {
        return (Project) sessionFactory.getCurrentSession().get(Project.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Project> getUserProjects(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Project.class)
                .add(Restrictions.eq("user.id", user.getId())).list();
    }

    @Override
    public void save(Project project) {
        sessionFactory.getCurrentSession().saveOrUpdate(project);
    }

    @Override
    public Project getProjectByUserAndName(User user, String name) {
        Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(Project.class);
        searchCriteria.add(
                and(eq("owner", user), eq("name", name))
        );
        List<Project> projects = searchCriteria.list();
        return projects.stream().findFirst().orElse(null);
    }

}
