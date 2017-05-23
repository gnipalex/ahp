package com.hnyp.ahp.web.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hnyp.ahp.lib.ComparisonScale;

@Entity
@Table(uniqueConstraints= { @UniqueConstraint(columnNames= {"criteria_a_id", "criteria_b_id", "projectDecision_id", "version_id"}) })
public class CriteriaComparison extends AbstractEntity {
    
    @ManyToOne
    @JoinColumn(name="criteria_a_id" ,referencedColumnName="id", nullable=false)
    private Criteria criteriaA;
    
    @ManyToOne
    @JoinColumn(name="criteria_b_id", referencedColumnName="id", nullable=false)
    private Criteria criteriaB;
    
    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private ComparisonScale value;
    
    @ManyToOne
    @JoinColumn(name="projectDecision_id", referencedColumnName="id", nullable=false)
    private ProjectDecision projectDecision;
    
    @ManyToOne
    @JoinColumn(name="version_id", referencedColumnName="id")
    private CriteriaComparisonVersion version;

    public Criteria getCriteriaA() {
        return criteriaA;
    }

    public void setCriteriaA(Criteria criteriaA) {
        this.criteriaA = criteriaA;
    }

    public Criteria getCriteriaB() {
        return criteriaB;
    }

    public void setCriteriaB(Criteria criteriaB) {
        this.criteriaB = criteriaB;
    }

    public ComparisonScale getValue() {
        return value;
    }

    public void setValue(ComparisonScale value) {
        this.value = value;
    }

    public ProjectDecision getProjectDecision() {
        return projectDecision;
    }

    public void setProjectDecision(ProjectDecision projectDecision) {
        this.projectDecision = projectDecision;
    }

    public CriteriaComparisonVersion getVersion() {
        return version;
    }

    public void setVersion(CriteriaComparisonVersion version) {
        this.version = version;
    }

}
