package com.hnyp.ahp.core.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.core.exception.AlternativeComparisonCreateException;
import com.hnyp.ahp.core.models.Alternative;
import com.hnyp.ahp.core.models.AlternativeComparisonTable;
import com.hnyp.ahp.core.models.ComparableItem;
import com.hnyp.ahp.core.models.ComparisonPair;
import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.models.User;
import com.hnyp.ahp.core.models.VoteRequest;
import com.hnyp.ahp.core.services.AlternativeComparisonService;
import com.hnyp.ahp.core.services.AlternativeService;
import com.hnyp.ahp.core.services.CriteriaService;
import com.hnyp.ahp.core.services.ModelService;
import com.hnyp.ahp.core.services.UserService;
import com.hnyp.ahp.core.services.VoteRequestService;
import com.hnyp.ahp.lib.Comparison;
import com.hnyp.ahp.lib.ComparisonMatrix;

public class DefaultAlternativeComparisonService implements AlternativeComparisonService {

    @Resource
    private CriteriaService criteriaService;
    @Resource
    private AlternativeService alternativeService;
    @Resource
    private UserService userService;
    @Resource
    private VoteRequestService voteRequestService;
    @Resource
    private ModelService modelService;
    @Resource
    private SessionFactory sessionFactory;
    
    @Override
    public void createForProjectDecision(ProjectDecision projectDecision) {
        List<Criteria> criterias = criteriaService.getForProjectDecision(projectDecision);
        if (CollectionUtils.isEmpty(criterias)) {
            throw new AlternativeComparisonCreateException(String.format("Cannot create alternative comparisons for decision %s: criterias are empty", projectDecision.getId()));
        }
        
        List<Alternative> alternatives = alternativeService.getForProjectDecision(projectDecision);
        if (CollectionUtils.isEmpty(alternatives)) {
            throw new AlternativeComparisonCreateException(String.format("Cannot create alternative comparisons for decision %s: alternatives are empty", projectDecision.getId()));
        }
        
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new AlternativeComparisonCreateException("Cannot create alternative comparisons for anonymous user");
        }
        
        if (!hasVoteRequestForUser(projectDecision, currentUser)) {
            throw new AlternativeComparisonCreateException(String.format("Cannot create alternative comparisons for decision %s: there is no vote requests for current user %s", projectDecision.getId(), currentUser.getEmail()));
        }
        
        criterias.forEach(criteria -> {
            AlternativeComparisonTable comparisonTable = new AlternativeComparisonTable();
            comparisonTable.setCriteria(criteria);
            comparisonTable.setProjectDecision(projectDecision);
            
            List<ComparisonPair> comparisonPairs = createBlankComparisonPairs(alternatives);
            comparisonPairs.stream().forEach(cp -> cp.setComparisonTable(comparisonTable));
            comparisonTable.setComparisonPairs(comparisonPairs);
            
            comparisonTable.setUser(currentUser);
            
            comparisonTable.setCalculated(false);
            comparisonTable.setFinished(false);
            
            modelService.save(comparisonTable);
        });
    }
    
    // TODO c/p from DefaultCriteriaComparisonService
    private List<ComparisonPair> createBlankComparisonPairs(List<Alternative> alternatives) {
        Set<String> alternativeNames = alternatives.stream().map(Alternative::getName).collect(Collectors.toSet());
        
        ComparisonMatrix comparisonMatrix = new ComparisonMatrix(alternativeNames);
        
        Comparison[][] comparisons = comparisonMatrix.getComparisons();
        
        List<ComparisonPair> comparisonPairs = Arrays.stream(comparisons).flatMap(Arrays::stream).map(c -> {
            ComparisonPair comparisonPair = new ComparisonPair();
            comparisonPair.setItemA(getByName(alternatives, c.getAlternativeA()));
            comparisonPair.setItemB(getByName(alternatives, c.getAlternativeB()));
            comparisonPair.setValue(c.getValue());
            return comparisonPair;
        }).collect(Collectors.toList());
        
        return comparisonPairs;
    }
    
    //TODO c/p fron DefaultCriteriaComparisonService
    private ComparableItem getByName(List<? extends ComparableItem> comparables, String name) {
        return comparables.stream().filter(c -> Objects.equals(name, c.getName())).findFirst().get();
    }
    
    
    private boolean hasVoteRequestForUser(ProjectDecision decision, User user) {
        List<VoteRequest> voteRequests = decision.getVoteRequests();
        return CollectionUtils.isNotEmpty(voteRequests) 
                && voteRequests.stream().anyMatch(request -> requestHasUser(request, user));
    }
    
    private boolean requestHasUser(VoteRequest request, User user) {
        User registeredUser = request.getRegisteredUser();
        return registeredUser != null && registeredUser.getId() == user.getId();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AlternativeComparisonTable> getForProjectDecision(ProjectDecision projectDecision) {
        User currentUser = userService.getCurrentUser();
        return sessionFactory.getCurrentSession().createCriteria(AlternativeComparisonTable.class)
                .add(Restrictions.eq("user", currentUser))
                .add(Restrictions.eq("projectDecision", projectDecision))
                .list();
    }

    @Override
    public AlternativeComparisonTable getForProjectDecisionAndId(ProjectDecision projectDecision, long id) {
        User currentUser = userService.getCurrentUser();
        return (AlternativeComparisonTable) sessionFactory.getCurrentSession().createCriteria(AlternativeComparisonTable.class)
                .add(Restrictions.eq("user", currentUser))
                .add(Restrictions.eq("projectDecision", projectDecision))
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    

}
