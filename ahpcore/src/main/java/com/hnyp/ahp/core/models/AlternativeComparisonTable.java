package com.hnyp.ahp.core.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * Stores alternative comparisons performed for user and criteria
 *
 */

@Entity
@DiscriminatorValue(value = "alternative")
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "voteRequest_id", "criteria_id", "projectDecision_id" }) } )
public class AlternativeComparisonTable extends ComparisonTable {
    
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="voteRequest_id", referencedColumnName="id")
//    private VoteRequest voteRequest;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="criteria_id", referencedColumnName="id")
    private Criteria criteria;

//    public VoteRequest getVoteRequest() {
//        return voteRequest;
//    }
//
//    public void setVoteRequest(VoteRequest voteRequest) {
//        this.voteRequest = voteRequest;
//    }
    
    public Criteria getCriteria() {
        return criteria;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
    
}
