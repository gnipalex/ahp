package com.hnyp.ahp.core.data;

import java.util.Map;

import com.hnyp.ahp.lib.ComparisonScale;

public class CriteriaComparisonTableData {
    
    private long projectDecisionId;
    
    private Map<String, Map<String, ComparisonScale>> comparisonTable;
    
    private Map<String, CriteriaData> criterias;

    public Map<String, Map<String, ComparisonScale>> getComparisonTable() {
        return comparisonTable;
    }

    public void setComparisonTable(Map<String, Map<String, ComparisonScale>> comparisonTable) {
        this.comparisonTable = comparisonTable;
    }

    public Map<String, CriteriaData> getCriterias() {
        return criterias;
    }

    public void setCriterias(Map<String, CriteriaData> criterias) {
        this.criterias = criterias;
    }

    public long getProjectDecisionId() {
        return projectDecisionId;
    }

    public void setProjectDecisionId(long projectDecisionId) {
        this.projectDecisionId = projectDecisionId;
    }
    
}
