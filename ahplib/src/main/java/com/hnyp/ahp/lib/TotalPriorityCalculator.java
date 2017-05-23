package com.hnyp.ahp.lib;

import java.util.HashMap;
import java.util.Map;

public class TotalPriorityCalculator {

    private Map<String, Double> criteriaPriorities = new HashMap<>();
    private Map<String, Map<String, Double>> alternativePriorities = new HashMap<>();

    public TotalPriorityCalculator setCriteriaPriorities(Map<String, Double> criteriaPriorities) {
        this.criteriaPriorities = criteriaPriorities;
        return this;
    }

    public TotalPriorityCalculator addAlternativePrioritiesForCriteria(String criteria, Map<String, Double> alternativePriorities) {
        // todo alternatives should have same order
        this.alternativePriorities.put(criteria, alternativePriorities);
        return this;
    }

    public Map<String, Double> calculatePriorities() {
        assertSizesOfCriteriasAndAlternativesCorrect();
        Map<String, Double> result = new HashMap<>();
        criteriaPriorities.entrySet().forEach(e -> {
            String criteriaName = e.getKey();
            // todo check
            Map<String, Double> alternativePrioritiesForCriteria = alternativePriorities.get(criteriaName);
            // not finished

        });
        return result;
    }

    private void assertSizesOfCriteriasAndAlternativesCorrect() {
        if (criteriaPriorities.size() != alternativePriorities.size()) {
            throw new IllegalStateException("sizes of criteria map and alternatives map do not match");
        }
    }
}
