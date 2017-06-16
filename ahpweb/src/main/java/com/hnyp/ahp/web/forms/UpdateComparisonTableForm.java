package com.hnyp.ahp.web.forms;

import java.util.List;

import com.hnyp.ahp.lib.ComparisonScale;

public class UpdateComparisonTableForm {

    private String name;
    private String description;
    private List<Comparison> comparisons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comparison> getComparisons() {
        return comparisons;
    }

    public void setComparisons(List<Comparison> comparisons) {
        this.comparisons = comparisons;
    }

    public static class Comparison {
        private long id;
        private ComparisonScale value;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public ComparisonScale getValue() {
            return value;
        }

        public void setValue(ComparisonScale value) {
            this.value = value;
        }

    }

}
