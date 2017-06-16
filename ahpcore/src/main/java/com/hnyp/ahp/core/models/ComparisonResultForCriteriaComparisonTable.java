package com.hnyp.ahp.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

//@Entity
public class ComparisonResultForCriteriaComparisonTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne
    private Criteria criteria;

    @OneToOne
    private WeightedAlternativeComparisonResult decisionOwnerComparisonResult;

    @OneToOne
    private WeightedAlternativeComparisonResult groupCommonComparisonResult;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public WeightedAlternativeComparisonResult getDecisionOwnerComparisonResult() {
        return decisionOwnerComparisonResult;
    }

    public void setDecisionOwnerComparisonResult(WeightedAlternativeComparisonResult decisionOwnerComparisonResult) {
        this.decisionOwnerComparisonResult = decisionOwnerComparisonResult;
    }

    public WeightedAlternativeComparisonResult getGroupCommonComparisonResult() {
        return groupCommonComparisonResult;
    }

    public void setGroupCommonComparisonResult(WeightedAlternativeComparisonResult groupCommonComparisonResult) {
        this.groupCommonComparisonResult = groupCommonComparisonResult;
    }

}
