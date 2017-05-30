package com.hnyp.ahp.core.data;

import java.util.List;
import java.util.Map;

import com.hnyp.ahp.lib.ComparisonScale;

public class ComparisonTableData {

    private long projectDecisionId;
    private Map<String, Map<String, ComparisonScale>> comparisonTable;
    private List<ComparableItemData> comparableItems;
    private double consistencyRatio;
    private boolean consistent;
    private boolean structureValid;
    private boolean finished;
    
    public long getProjectDecisionId() {
        return projectDecisionId;
    }
    public void setProjectDecisionId(long projectDecisionId) {
        this.projectDecisionId = projectDecisionId;
    }
    public Map<String, Map<String, ComparisonScale>> getComparisonTable() {
        return comparisonTable;
    }
    public void setComparisonTable(Map<String, Map<String, ComparisonScale>> comparisonTable) {
        this.comparisonTable = comparisonTable;
    }
    public List<ComparableItemData> getComparableItems() {
        return comparableItems;
    }
    public void setComparableItems(List<ComparableItemData> comparableItems) {
        this.comparableItems = comparableItems;
    }
    public double getConsistencyRatio() {
        return consistencyRatio;
    }
    public void setConsistencyRatio(double consistencyRatio) {
        this.consistencyRatio = consistencyRatio;
    }
    public boolean isConsistent() {
        return consistent;
    }
    public void setConsistent(boolean consistent) {
        this.consistent = consistent;
    }
    public boolean isStructureValid() {
        return structureValid;
    }
    public void setStructureValid(boolean structureValid) {
        this.structureValid = structureValid;
    }
    public boolean isFinished() {
        return finished;
    }
    public void setFinished(boolean finished) {
        this.finished = finished;
    }
    
    
}
