package com.hnyp.ahp.web.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class ProjectDecision extends AbstractEntity {

    @OneToMany(mappedBy = "projectDecision")
    private List<VoteRequest> voteRequests;
    
    @OneToMany(mappedBy = "projectDecision")
    private List<Alternative> alternatives;
    
    @OneToMany(mappedBy = "projectDecision")
    private List<CriteriaComparisonVersion> criteriaComparisonVersions;
    
    @Column
    private String goal;
    
    
    
    
    
}
