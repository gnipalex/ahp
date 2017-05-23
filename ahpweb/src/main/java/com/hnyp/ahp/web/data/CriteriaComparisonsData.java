package com.hnyp.ahp.web.data;

import java.util.List;

import com.hnyp.ahp.lib.Comparison;

public class CriteriaComparisonsData {

    private List<String> criteriaNames;
    
    private double[] computedPriority;
    
    private double computedConsistencyRatio;
    
    private Comparison[][] comparisons;
    
    private String comparisonsVersion;
    
    private long comparisonVersionId;
    
    private long projectId;
    

    public List<String> getCriteriaNames() {
        return criteriaNames;
    }

    public void setCriteriaNames(List<String> criteriaNames) {
        this.criteriaNames = criteriaNames;
    }

    public Comparison[][] getComparisons() {
        return comparisons;
    }

    public void setComparisons(Comparison[][] comparisons) {
        this.comparisons = comparisons;
    }

    public String getComparisonsVersion() {
        return comparisonsVersion;
    }

    public void setComparisonsVersion(String comparisonsVersion) {
        this.comparisonsVersion = comparisonsVersion;
    }

    public long getComparisonVersionId() {
        return comparisonVersionId;
    }

    public void setComparisonVersionId(long comparisonVersionId) {
        this.comparisonVersionId = comparisonVersionId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public double[] getComputedPriority() {
        return computedPriority;
    }

    public void setComputedPriority(double[] computedPriority) {
        this.computedPriority = computedPriority;
    }

    public double getComputedConsistencyRatio() {
        return computedConsistencyRatio;
    }

    public void setComputedConsistencyRatio(double computedConsistencyRatio) {
        this.computedConsistencyRatio = computedConsistencyRatio;
    }
    
}
