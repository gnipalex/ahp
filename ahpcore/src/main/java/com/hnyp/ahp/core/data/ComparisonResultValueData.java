package com.hnyp.ahp.core.data;

public class ComparisonResultValueData {

    private long id;
    private ComparableItemData comparableItem;
    private double value;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public ComparableItemData getComparableItem() {
        return comparableItem;
    }
    public void setComparableItem(ComparableItemData comparableItem) {
        this.comparableItem = comparableItem;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    
    
    
}
