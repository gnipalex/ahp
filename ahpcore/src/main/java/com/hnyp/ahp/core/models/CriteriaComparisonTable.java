package com.hnyp.ahp.core.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue(value = "criteria")
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames={"name", "projectDecision_id"}) })
public class CriteriaComparisonTable extends ComparisonTable {

    @Column(nullable=false)
    private String name;
    
    @Column
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
