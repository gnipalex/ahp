package com.hnyp.ahp.core.services.impl;

import javax.transaction.Transactional;

import com.hnyp.ahp.core.services.CriteriaComparisonService;

@Transactional
public class DefaultCriteriaComparisonService implements CriteriaComparisonService {

   /* @Resource
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<CriteriaComparison> getCriteriaComparisons(Project project, CriteriaComparisonVersion version) {
        Criterion searchCriteria = and(
                eq("project.id", project.getId()), 
                eq("version.id", version.getId())
        );
        return sessionFactory.getCurrentSession().createCriteria(CriteriaComparison.class)
            .add(searchCriteria).list();
    }

    @Override
    public void update(CriteriaComparison comparison) {
        sessionFactory.getCurrentSession().saveOrUpdate(comparison);
    }

    @Override
    public List<CriteriaComparison> createBlankComparisons(Project project, CriteriaComparisonVersion version) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CriteriaComparison getCriteriaComparison(Comparison comparison, CriteriaComparisonVersion version) {
        long criteriaAId = Long.valueOf(comparison.getAlternativeA());
        long criteriaBId = Long.valueOf(comparison.getAlternativeB());
        Criterion searchCriteria = and(
                eq("criteriaA.id", criteriaAId), 
                eq("criteriaB.id", criteriaBId), 
                eq("version.id", version.getId())
        );
        return (CriteriaComparison) sessionFactory.getCurrentSession().createCriteria(CriteriaComparison.class)
                .add(searchCriteria).uniqueResult();
    }

    @Override
    public Comparison getComparison(CriteriaComparison criteriaComparison) {
        Comparison comparison = new Comparison();
        comparison.setAlternativeA(String.valueOf(criteriaComparison.getCriteriaA().getId()));
        comparison.setAlternativeB(String.valueOf(criteriaComparison.getCriteriaB().getId()));
        comparison.setValue(criteriaComparison.getValue());
        return comparison;
    }
*/
}
