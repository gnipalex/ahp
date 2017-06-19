package com.hnyp.ahp.core.services.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.hnyp.ahp.core.exception.ProjectDecisionIllegalStateException;
import com.hnyp.ahp.core.models.Alternative;
import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.CriteriaComparisonTable;
import com.hnyp.ahp.core.models.Project;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.models.ProjectDecisionStatus;
import com.hnyp.ahp.core.models.VoteRequest;
import com.hnyp.ahp.core.services.AlternativeService;
import com.hnyp.ahp.core.services.CriteriaComparisonService;
import com.hnyp.ahp.core.services.CriteriaService;
import com.hnyp.ahp.core.services.ProjectDecisionService;
import com.hnyp.ahp.core.services.VoteRequestService;

@Transactional
public class DefaultProjectDecisionService implements ProjectDecisionService {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private CriteriaService criteriaService;
    @Autowired
    private CriteriaComparisonService criteriaComparisonService;
    @Autowired
    private AlternativeService alternativeService;
    @Autowired
    private VoteRequestService voteRequestService;
    
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
       return sessionFactory.getCurrentSession().createCriteria(ProjectDecision.class)
                .add(Restrictions.eq("project", project))
                .add(Restrictions.in("status", Arrays.asList(ProjectDecisionStatus.CREATED, 
                        ProjectDecisionStatus.VOTING, ProjectDecisionStatus.VOTING_FINISHED)))
                .addOrder(Order.desc("modifiedTS"))
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProjectDecision> getDecisions(Project project, int page, int count) {
        return sessionFactory.getCurrentSession()
                .createCriteria(ProjectDecision.class)
                .add(Restrictions.eq("project", project))
                .addOrder(Order.desc("createdTS"))
                .setMaxResults(count)
                .setFirstResult(count * page - 1)
                .list();
    }

    @Override
    public void remove(long id) {
        ProjectDecision projectDecision = getProjectDecision(id);
        sessionFactory.getCurrentSession().delete(projectDecision);
    }

    @Override
    public void startProjectDecision(ProjectDecision projectDecision) {
        if (!ProjectDecisionStatus.CREATED.equals(projectDecision.getStatus()) &&
                !ProjectDecisionStatus.CRITERIA_COMPARISON.equals(projectDecision.getStatus())) {
            throw new ProjectDecisionIllegalStateException(String.format("project decision %s can not be started, "
                    + "its status is %s", projectDecision.getId(), projectDecision.getStatus()));
        }
        
        List<CriteriaComparisonTable> criteriaComparisonTables = criteriaComparisonService.getForDecision(projectDecision);
        if (CollectionUtils.isEmpty(criteriaComparisonTables)) {
            throw new ProjectDecisionIllegalStateException(String.format("project decision %s can not be started, "
                    + "it doesn't contain at least one criteria comparison", projectDecision.getId()));
        }

        projectDecision.setModifiedTS(new Date());
        
        projectDecision.setStatus(ProjectDecisionStatus.VOTING);
        
        voteRequestService.processRequestsForDecision(projectDecision);
        
        save(projectDecision);
    }

    @Override
    public void finishEditProjectDecision(ProjectDecision projectDecision) {
        List<Alternative> alternatives = alternativeService.getForProjectDecision(projectDecision);
        assertSizeIsAtLeast(2, alternatives, String.format("project decision %s can not be started, "
                    + "count of alternatives must be >= 2", projectDecision.getId()));
        
        List<Criteria> criterias = criteriaService.getForProjectDecision(projectDecision);
        assertSizeIsAtLeast(2, criterias, String.format("project decision %s can not be started, "
                    + "count of criterias must be >= 2", projectDecision.getId()));
        
        List<VoteRequest> voteRequests = projectDecision.getVoteRequests();
        assertSizeIsAtLeast(1, voteRequests, String.format("project decision %s can not be started, "
                    + "count of vote requests must be >= 1", projectDecision.getId()));
        
        projectDecision.setModifiedTS(new Date());
        
        projectDecision.setStatus(ProjectDecisionStatus.CRITERIA_COMPARISON);
        
        save(projectDecision);
    }
    
    private void assertSizeIsAtLeast(int desiredSize, Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection) || collection.size() < desiredSize) {
            throw new IllegalStateException(message);
        }
    }
    
    

}
