package com.hnyp.ahp.core.models;

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
import javax.persistence.ManyToOne;

/**
 * Represents calculated weight of comparableItem with respect to comparisonTable
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType=DiscriminatorType.STRING)
public class ComparisonResultValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    
    @ManyToOne
    @JoinColumn(name="comparableItem_id", nullable=false)
    private ComparableItem comparableItem;
    
    @Column
    private double value;

    public ComparableItem getComparableItem() {
        return comparableItem;
    }

    public void setComparableItem(ComparableItem comparableItem) {
        this.comparableItem = comparableItem;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
