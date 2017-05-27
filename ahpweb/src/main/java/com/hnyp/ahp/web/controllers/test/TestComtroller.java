package com.hnyp.ahp.web.controllers.test;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnyp.ahp.web.v2.models.Alternative;
import com.hnyp.ahp.web.v2.models.Criteria;
import com.hnyp.ahp.web.v2.models.Project;
import com.hnyp.ahp.web.v2.models.ProjectDecision;
import com.hnyp.ahp.web.v2.models.User;

//@Controller
@RequestMapping("/test")
public class TestComtroller {

    @Autowired
    private SessionFactory sessionFactory;
    
    @RequestMapping("/createStructure")
    @Transactional
    @ResponseBody
    public String createStructure() {
        
        try {
            
//            Project project = (Project) sessionFactory.getCurrentSession().get(Project.class, 1L);
            
            ProjectDecision decision = (ProjectDecision) sessionFactory.getCurrentSession().get(ProjectDecision.class, 1L);
            
            
            
            sessionFactory.getCurrentSession().flush();
            
            return "decision with alternatives were saved";
        } catch (Exception e) {
            return "an error occured : " + e.getMessage();
        }
        
    }
    private void createDecisionWithAlternatives() {
        Project project = (Project) sessionFactory.getCurrentSession().get(Project.class, 1L);
        
        ProjectDecision decision = new ProjectDecision();
        decision.setProject(project);
        decision.setGoal("first decision");
        sessionFactory.getCurrentSession().persist(decision);
        
        Alternative alternative1 = new Alternative();
        alternative1.setName("alternative1");
        alternative1.setProjectDecision(decision);
        sessionFactory.getCurrentSession().persist(alternative1);
        
        Alternative alternative2 = new Alternative();
        alternative2.setName("alternative2");
        alternative2.setProjectDecision(decision);
        sessionFactory.getCurrentSession().persist(alternative2);
        
        Criteria criteria1 = new Criteria();
        criteria1.setName("criteria1");
        criteria1.setProjectDecision(decision);
        sessionFactory.getCurrentSession().persist(criteria1);
    }
    
    private Project createProjectWithUser() {
        User user = new User();
        user.setEmail("aa@email.com");
        user.setLastName("Oleksandr");
        user.setPassword("aa");
        
        sessionFactory.getCurrentSession().persist(user);
        
        Project project = new Project();
        project.setName("First project");
        project.setDescription("This is test project");
        project.setOwner(user);
        
        sessionFactory.getCurrentSession().persist(project);
        return project;
    }
    
}
