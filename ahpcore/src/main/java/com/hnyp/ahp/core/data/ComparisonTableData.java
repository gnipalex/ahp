package com.hnyp.ahp.core.data;

import java.util.List;
import java.util.Map;

public class ComparisonTableData {

    private long id;
    private long projectDecisionId;
    private Map<String, Map<String, ComparisonPairData>> comparisonTable;
    private List<ComparableItemData> comparableItems;
    private List<ComparisonResultValueData> comparisonResults;
    private double consistencyRatio;
    private boolean consistent;
    private boolean structureValid;
    private boolean finished;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectDecisionId() {
        return projectDecisionId;
    }

    public void setProjectDecisionId(long projectDecisionId) {
        this.projectDecisionId = projectDecisionId;
    }

    public Map<String, Map<String, ComparisonPairData>> getComparisonTable() {
        return comparisonTable;
    }

    public void setComparisonTable(Map<String, Map<String, ComparisonPairData>> comparisonTable) {
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

    public List<ComparisonResultValueData> getComparisonResults() {
        return comparisonResults;
    }

    public void setComparisonResults(List<ComparisonResultValueData> comparisonResults) {
        this.comparisonResults = comparisonResults;
    }

}
