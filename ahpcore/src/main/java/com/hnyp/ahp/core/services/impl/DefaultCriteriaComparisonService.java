package com.hnyp.ahp.core.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.hnyp.ahp.core.exception.CreateBlankCriteriaComparisonException;
import com.hnyp.ahp.core.models.ComparisonPair;
import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.CriteriaComparisonTable;
import com.hnyp.ahp.core.models.ProjectDecision;
import com.hnyp.ahp.core.services.CriteriaComparisonService;
import com.hnyp.ahp.core.services.CriteriaService;
import com.hnyp.ahp.core.services.ModelService;
import com.hnyp.ahp.lib.Comparison;
import com.hnyp.ahp.lib.ComparisonMatrix;

@Transactional
public class DefaultCriteriaComparisonService implements CriteriaComparisonService {

    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private ModelService modelService;
    @Resource
    private CriteriaService criteriaService;
    
    @Override
    public void create(CriteriaComparisonTable comparisonTable) {
        if (comparisonTable.getProjectDecision() == null) {
            throw new CreateBlankCriteriaComparisonException("project decision must be set for criteria comparison table");
        }
        
        List<Criteria> criteriasForProjectDecision = criteriaService.getForProjectDecision(comparisonTable.getProjectDecision());
        if (CollectionUtils.isEmpty(criteriasForProjectDecision)) {
            throw new CreateBlankCriteriaComparisonException(String.format("criterias not found for project decision %s", comparisonTable.getProjectDecision().getId()));
        }
        
        List<ComparisonPair> comparisonPairs = createBlankComparisonPairs(criteriasForProjectDecision);
        
        comparisonPairs.stream().forEach(cp -> cp.setComparisonTable(comparisonTable));
        
        comparisonTable.setComparisonPairs(comparisonPairs);
        
        comparisonTable.setCalculated(false);
        comparisonTable.setFinished(false);
        
        sessionFactory.getCurrentSession().save(comparisonTable);
    }
    
    private List<ComparisonPair> createBlankComparisonPairs(List<Criteria> criteriasForProjectDecision) {
        Set<String> criteriaNames = criteriasForProjectDecision.stream().map(Criteria::getName).collect(Collectors.toSet());
        
        ComparisonMatrix comparisonMatrix = new ComparisonMatrix(criteriaNames);
        
        Comparison[][] comparisons = comparisonMatrix.getComparisons();
        
        List<ComparisonPair> comparisonPairs = Arrays.stream(comparisons).flatMap(Arrays::stream).map(c -> {
            ComparisonPair comparisonPair = new ComparisonPair();
            comparisonPair.setItemA(getByName(criteriasForProjectDecision, c.getAlternativeA()));
            comparisonPair.setItemB(getByName(criteriasForProjectDecision, c.getAlternativeB()));
            comparisonPair.setValue(c.getValue());
            return comparisonPair;
        }).collect(Collectors.toList());
        
        return comparisonPairs;
    }
    
    private Criteria getByName(List<Criteria> criterias, String name) {
        return criterias.stream().filter(c -> Objects.equals(name, c.getName())).findFirst().get();
    }

    @Override
    public CriteriaComparisonTable getForDecisionAndName(ProjectDecision projectDecision, String name) {
        return (CriteriaComparisonTable) sessionFactory.getCurrentSession().createCriteria(CriteriaComparisonTable.class)
                .add(Restrictions.eq("projectDecision", projectDecision))
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CriteriaComparisonTable> getForDecision(ProjectDecision projectDecision) {
        return sessionFactory.getCurrentSession().createCriteria(CriteriaComparisonTable.class)
                .add(Restrictions.eq("projectDecision", projectDecision))
                .list();
    }

    @Override
    public CriteriaComparisonTable getForDecisionAndId(ProjectDecision projectDecision, long id) {
        return (CriteriaComparisonTable) sessionFactory.getCurrentSession().createCriteria(CriteriaComparisonTable.class)
                .add(Restrictions.eq("projectDecision", projectDecision))
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    
    
}
