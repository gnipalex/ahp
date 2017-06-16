package com.hnyp.ahp.core.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


/**
 * Stores combined result of all alternative comparison tables grouped by vote request, 
 * also weighted by criteria comparison results
 *
 */
//@Entity
public class WeightedAlternativeComparisonResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "criteriaComparisonTable_id")
//    private CriteriaComparisonTable criteriaComparisonTable;
//    
//    @ManyToMany(targetEntity = AlternativeComparisonTable.class, cascade = CascadeType.ALL )
//    @JoinTable(name = "WACR_alternativeComparisonTables", 
//        joinColumns = { @JoinColumn(name = "WACR_id")  },
//        inverseJoinColumns = { @JoinColumn(name = "alternativeComparisonTable_id") }
//    )
//    private List<AlternativeComparisonTable> alternativeComparisonTables;
//    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "ComparisonResultValue", 
        joinColumns = { @JoinColumn(name = "WACR_id")  },
        inverseJoinColumns = { @JoinColumn(name = "id") }
    )
    private List<ComparisonResultValue> combinedWeightedResultValues;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ComparisonResultValue> getCombinedWeightedResultValues() {
        return combinedWeightedResultValues;
    }

    public void setCombinedWeightedResultValues(List<ComparisonResultValue> combinedWeightedResultValues) {
        this.combinedWeightedResultValues = combinedWeightedResultValues;
    }
    
}
