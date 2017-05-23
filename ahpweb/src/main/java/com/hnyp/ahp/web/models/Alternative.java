package com.hnyp.ahp.web.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints= { @UniqueConstraint(columnNames= {"projectDecision_id", "name"}) })
public class Alternative extends AbstractEntity {
    
    @Column(nullable=false)
    private String name;
    
    @Column
	private String description;
    
    @ManyToOne
    @JoinColumn(name="projectDecision_id", referencedColumnName="id", nullable=false)
    private ProjectDecision projectDecision;
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ProjectDecision getProjectDecision() {
        return projectDecision;
    }
    public void setProjectDecision(ProjectDecision projectDecision) {
        this.projectDecision = projectDecision;
    }
    
}
