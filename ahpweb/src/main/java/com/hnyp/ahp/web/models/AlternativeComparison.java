package com.hnyp.ahp.web.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hnyp.ahp.lib.ComparisonScale;

@Entity
@Table(uniqueConstraints= { @UniqueConstraint(columnNames= {"alternative_a_id", "alternative_b_id", "criteria_id", "voteruser_id"}) })
public class AlternativeComparison extends AbstractEntity {

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="alternative_a_id", referencedColumnName="id", nullable=false)
	private Alternative alternativeA;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="alternative_b_id", referencedColumnName="id", nullable=false)
	private Alternative alternativeB;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
	private ComparisonScale value;
    
    @ManyToOne
    @JoinColumn(name="criteria_id", referencedColumnName="id", nullable=false)
	private Criteria criteria;
    
    @ManyToOne
    @JoinColumn(name="voteruser_id",referencedColumnName="id", nullable=false)
	private User voterUser;

    public Alternative getAlternativeA() {
        return alternativeA;
    }
    
    public void setAlternativeA(Alternative alternativeA) {
        this.alternativeA = alternativeA;
    }

    public Alternative getAlternativeB() {
        return alternativeB;
    }

    public void setAlternativeB(Alternative alternativeB) {
        this.alternativeB = alternativeB;
    }

    public ComparisonScale getValue() {
        return value;
    }

    public void setValue(ComparisonScale value) {
        this.value = value;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public User getVoterUser() {
        return voterUser;
    }

    public void setVoterUser(User voterUser) {
        this.voterUser = voterUser;
    }

}
