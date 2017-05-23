package com.hnyp.ahp.web.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints= { @UniqueConstraint(columnNames= {"name", "owner_id"}) })
public class Project extends AbstractEntity {
    
    @Column(nullable=false)
    private String name;
    
    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name="owner_id", referencedColumnName="id", nullable=false)
    private User owner;
    
    @OneToMany(mappedBy="project", cascade=CascadeType.REMOVE)
    private List<ProjectDecision> decisions;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<ProjectDecision> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<ProjectDecision> decisions) {
        this.decisions = decisions;
    }
    
}
