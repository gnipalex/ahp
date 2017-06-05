package com.hnyp.ahp.core.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projectDecision", cascade = CascadeType.ALL)
    private List<VoteRequest> voteRequests;

    // doesn't work
    // need to select by query 
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "ComparableItem", joinColumns = { @JoinColumn(name = "projectDecision_id") }, inverseJoinColumns = {
//            @JoinColumn(name = "id") })
//    private List<Alternative> alternatives;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "ComparableItem", joinColumns = { @JoinColumn(name = "projectDecision_id") }, inverseJoinColumns = {
//            @JoinColumn(name = "id") })
//    private List<Criteria> criterias;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CriteriaComparisonTable> criteriaComparisonTables;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AlternativeComparisonTable> alternativeComparisonTables;

    @Column
    private String goal;

    @Column
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectDecisionStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Column
    private Date createdTS;

    @Column
    private Date modifiedTS;

    public List<VoteRequest> getVoteRequests() {
        return voteRequests;
    }

    public void setVoteRequests(List<VoteRequest> voteRequests) {
        this.voteRequests = voteRequests;
    }

//    public List<Alternative> getAlternatives() {
//        return alternatives;
//    }
//
//    public void setAlternatives(List<Alternative> alternatives) {
//        this.alternatives = alternatives;
//    }
//
//    public List<Criteria> getCriterias() {
//        return criterias;
//    }
//
//    public void setCriterias(List<Criteria> criterias) {
//        this.criterias = criterias;
//    }

    public List<CriteriaComparisonTable> getCriteriaComparisonTables() {
        return criteriaComparisonTables;
    }

    public void setCriteriaComparisonTables(List<CriteriaComparisonTable> criteriaComparisonTables) {
        this.criteriaComparisonTables = criteriaComparisonTables;
    }

    public List<AlternativeComparisonTable> getAlternativeComparisonTables() {
        return alternativeComparisonTables;
    }

    public void setAlternativeComparisonTables(List<AlternativeComparisonTable> alternativeComparisonTables) {
        this.alternativeComparisonTables = alternativeComparisonTables;
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

    public ProjectDecisionStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectDecisionStatus status) {
        this.status = status;
    }

    public Date getCreatedTS() {
        return createdTS;
    }

    public void setCreatedTS(Date createdTS) {
        this.createdTS = createdTS;
    }

    public Date getModifiedTS() {
        return modifiedTS;
    }

    public void setModifiedTS(Date modifiedTS) {
        this.modifiedTS = modifiedTS;
    }

}