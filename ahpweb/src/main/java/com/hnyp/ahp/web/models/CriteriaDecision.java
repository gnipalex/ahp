package com.hnyp.ahp.web.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class CriteriaDecision extends AbstractEntity {

    @OneToMany(mappedBy = "decision")
    private List<CriteriaComparison> comparisons;
    
    @Column(nullable=false)
    private String version;
    
    @ManyToOne
    @JoinColumn(name="projectDecision_id", referencedColumnName="id")
    private ProjectDecision projectDecision;
    
    
    
}
