package com.hnyp.ahp.web.v2.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProjectDecision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    
    @OneToMany(mappedBy = "projectDecision")
    private List<VoteRequest> voteRequests;
    
    @OneToMany
    @JoinTable(name = "Alternative", joinColumns = { @JoinColumn(name = "projectDecision_id") }, 
                    inverseJoinColumns = { @JoinColumn(name = "id") } )
    private List<Alternative> alternatives;
    
    @OneToMany
    @JoinTable(name = "Criteria", joinColumns = { @JoinColumn(name = "projectDecision_id") }, 
                    inverseJoinColumns = { @JoinColumn(name = "id") } )
    private List<Criteria> criterias;
    
    @OneToMany
    private List<CriteriaComparisonTable> criteriaComparisonResults;
    
    @OneToMany
    private List<AlternativeComparisonTable> alternativeComparisonResults;
    
    @Column
    private String goal;
    
    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    public List<VoteRequest> getVoteRequests() {
        return voteRequests;
    }

    public void setVoteRequests(List<VoteRequest> voteRequests) {
        this.voteRequests = voteRequests;
    }

    public List<Alternative> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<Alternative> alternatives) {
        this.alternatives = alternatives;
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }

    public List<CriteriaComparisonTable> getCriteriaComparisonResults() {
        return criteriaComparisonResults;
    }

    public void setCriteriaComparisonResults(List<CriteriaComparisonTable> criteriaComparisonResults) {
        this.criteriaComparisonResults = criteriaComparisonResults;
    }

    public List<AlternativeComparisonTable> getAlternativeComparisonResults() {
        return alternativeComparisonResults;
    }

    public void setAlternativeComparisonResults(List<AlternativeComparisonTable> alternativeComparisonResults) {
        this.alternativeComparisonResults = alternativeComparisonResults;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}