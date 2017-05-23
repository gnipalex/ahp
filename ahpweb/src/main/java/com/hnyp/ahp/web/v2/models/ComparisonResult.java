package com.hnyp.ahp.web.v2.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public class ComparisonResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="comparisonResult_comparableItem", 
        joinColumns = { @JoinColumn(name="comparisonResult_id", nullable=false, updatable=false) },
        inverseJoinColumns = { @JoinColumn(name="comparableItem_id", nullable=false, updatable=false) })
    private List<ComparableItem> comparables;
    
    @OneToMany(mappedBy="comparisonResult", cascade = CascadeType.ALL)
    private List<ComparisonPair> comparisonPairs;
    
    @OneToMany(mappedBy = "comparisonResult", cascade = CascadeType.ALL)
    private List<ComparisonResultRow> comparisonResults;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="projectDecision_id", referencedColumnName="id", nullable=false)
    private ProjectDecision projectDecision;
    
    @Column
    private boolean calculated;
    
    @Column
    private boolean finished;
    
    @Column
    private double consistencyIndex;

    public List<ComparableItem> getComparables() {
        return comparables;
    }

    public void setComparables(List<ComparableItem> comparables) {
        this.comparables = comparables;
    }

    public List<ComparisonPair> getComparisonPairs() {
        return comparisonPairs;
    }

    public void setComparisonPairs(List<ComparisonPair> comparisonPairs) {
        this.comparisonPairs = comparisonPairs;
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<ComparisonResultRow> getComparisonResults() {
        return comparisonResults;
    }

    public void setComparisonResults(List<ComparisonResultRow> comparisonResults) {
        this.comparisonResults = comparisonResults;
    }

    public double getConsistencyIndex() {
        return consistencyIndex;
    }

    public void setConsistencyIndex(double consistencyIndex) {
        this.consistencyIndex = consistencyIndex;
    }

    public ProjectDecision getProjectDecision() {
        return projectDecision;
    }

    public void setProjectDecision(ProjectDecision projectDecision) {
        this.projectDecision = projectDecision;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
