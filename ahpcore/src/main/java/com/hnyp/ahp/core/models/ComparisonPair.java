package com.hnyp.ahp.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.hnyp.ahp.lib.ComparisonScale;

@Entity
@Table(name = "comparisonPair", 
        uniqueConstraints = { @UniqueConstraint( columnNames= {"comparisonTable_id", "itemA_id", "itemB_id"}) })
public class ComparisonPair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="comparisonTable_id", referencedColumnName="id", nullable=false)
    private ComparisonTable comparisonTable;
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="itemA_id", referencedColumnName="id", nullable=false)
    private ComparableItem itemA;
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="itemB_id", referencedColumnName="id", nullable=false)
    private ComparableItem itemB;
    
    @Column
    @Enumerated(EnumType.STRING)
    private ComparisonScale value;

    public ComparisonTable getComparisonTable() {
        return comparisonTable;
    }

    public void setComparisonTable(ComparisonTable comparisonTable) {
        this.comparisonTable = comparisonTable;
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
