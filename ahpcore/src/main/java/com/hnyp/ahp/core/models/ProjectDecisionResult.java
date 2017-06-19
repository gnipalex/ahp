package com.hnyp.ahp.core.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProjectDecisionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private CriteriaComparisonTable criteriaComparison;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "projectDecisionResult_id")
    private List<ComparisonResultValue> comparisonResults;

    @ManyToOne(cascade = CascadeType.ALL)
    private ProjectDecision projectDecision;
    
    @Column
    @Enumerated(EnumType.STRING)
    private ProjectDecisionResultType type;
    
    @Column
    private boolean hasInconsistency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CriteriaComparisonTable getCriteriaComparison() {
        return criteriaComparison;
    }

    public void setCriteriaComparison(CriteriaComparisonTable criteriaComparison) {
        this.criteriaComparison = criteriaComparison;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ComparisonResultValue> getComparisonResults() {
        return comparisonResults;
    }

    public void setComparisonResults(List<ComparisonResultValue> comparisonResults) {
        this.comparisonResults = comparisonResults;
    }

    public ProjectDecision getProjectDecision() {
        return projectDecision;
    }

    public void setProjectDecision(ProjectDecision projectDecision) {
        this.projectDecision = projectDecision;
    }

    public ProjectDecisionResultType getType() {
        return type;
    }

    public void setType(ProjectDecisionResultType type) {
        this.type = type;
    }

    public boolean isHasInconsistency() {
        return hasInconsistency;
    }

    public void setHasInconsistency(boolean hasInconsistency) {
        this.hasInconsistency = hasInconsistency;
    }
    
}
