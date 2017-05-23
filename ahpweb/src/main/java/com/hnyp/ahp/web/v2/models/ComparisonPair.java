package com.hnyp.ahp.web.v2.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.hnyp.ahp.lib.ComparisonScale;

@Entity
@Table(name = "comparisonPair", 
        uniqueConstraints = { @UniqueConstraint( columnNames= {"comparisonResult_id", "itemA_id", "itemB_id"}) })
public class ComparisonPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    
    @ManyToOne
    @JoinColumn(name="comparisonResult_id", referencedColumnName="id", nullable=false)
    private ComparisonResult comparisonResult;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="itemA_id", referencedColumnName="id", nullable=false)
    private ComparableItem itemA;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="itemB_id", referencedColumnName="id", nullable=false)
    private ComparableItem itemB;
    
    @Column
    @Enumerated(EnumType.STRING)
    private ComparisonScale value;

    public ComparisonResult getComparisonResult() {
        return comparisonResult;
    }

    public void setComparisonResult(ComparisonResult comparisonResult) {
        this.comparisonResult = comparisonResult;
    }

    public ComparableItem getItemA() {
        return itemA;
    }

    public void setItemA(ComparableItem itemA) {
        this.itemA = itemA;
    }

    public ComparableItem getItemB() {
        return itemB;
    }

    public void setItemB(ComparableItem itemB) {
        this.itemB = itemB;
    }

    public ComparisonScale getValue() {
        return value;
    }

    public void setValue(ComparisonScale value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
