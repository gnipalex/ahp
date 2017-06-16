package com.hnyp.ahp.core.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public class ComparisonTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="comparisonTable", cascade = CascadeType.ALL)
    private List<ComparisonPair> comparisonPairs;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ComparisonTable_ComparisonResultValue", 
        joinColumns = { @JoinColumn(name="comparisonTable_id") },
        inverseJoinColumns = { @JoinColumn(name="comparisonResultValue_id") }
    )
    private List<ComparisonResultValue> comparisonResults;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="projectDecision_id", referencedColumnName="id", nullable=false)
    private ProjectDecision projectDecision;
    
    @Column
    private boolean calculated;
    
    @Column
    private boolean finished;
    
    @Column
    private double consistencyIndex;
    
    @Column
    private double consistencyRatio;
    
    @Column
    private boolean consistent;

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

    public List<ComparisonResultValue> getComparisonResults() {
        return comparisonResults;
    }

    public void setComparisonResults(List<ComparisonResultValue> comparisonResults) {
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

    public double getConsistencyRatio() {
        return consistencyRatio;
    }

    public void setConsistencyRatio(double consistencyRatio) {
        this.consistencyRatio = consistencyRatio;
    }

    public boolean isConsistent() {
        return consistent;
    }

    public void setConsistent(boolean consistent) {
        this.consistent = consistent;
    }
    
}
