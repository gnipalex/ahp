package com.hnyp.ahp.web.v2.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue(value = "alternative")
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "voter_id", "criteria_id", "projectDecision_id" }) } )
public class AlternativeComparisonResult extends ComparisonResult {
    
    @ManyToOne
    @JoinColumn(name="voter_id", referencedColumnName="id", nullable=false)
    private User voter;
    
    @ManyToOne
    @JoinColumn(name="criteria_id", referencedColumnName="id", nullable=false)
    private Criteria criteria;

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
    
}
