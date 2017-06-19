package com.hnyp.ahp.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class TotalPriorityCalculator {

    private Map<String, Double> criteriaPriorities = new HashMap<>();
    private Map<String, Map<String, Double>> alternativePriorities = new HashMap<>();

    public TotalPriorityCalculator setCriteriaPriorities(Map<String, Double> criteriaPriorities) {
        if (!this.criteriaPriorities.isEmpty()) {
            throw new IllegalStateException("criteria priorities can not be changed once they are set");
        }
        this.criteriaPriorities = criteriaPriorities;
        return this;
    }

    public TotalPriorityCalculator addAlternativePrioritiesForCriteria(String criteria, Map<String, Double> alternativePriorities) {
        assertSupportsCriteria(criteria);
        this.alternativePriorities.put(criteria, alternativePriorities);
        return this;
    }

    public Map<String, Double> getWeightedPriorities() {
        assertSizesOfCriteriasAndAlternativesCorrect();
        
        Map<String, Map<String, Double>> weightedAlternativePrioritiesForCriteria = new HashMap<>();
        criteriaPriorities.entrySet().forEach(e -> {
            String criteriaName = e.getKey();
            Map<String, Double> alternativePrioritiesForCriteria = alternativePriorities.get(criteriaName);
            Map<String,Double> weightedAlternativePriorities = alternativePrioritiesForCriteria.entrySet().stream()
                .collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue() * criteriaPriorities.get(criteriaName)));
            weightedAlternativePrioritiesForCriteria.put(criteriaName, weightedAlternativePriorities);
        });

        Map<String, Double> weightedAlternativePriorities = weightedAlternativePrioritiesForCriteria.values().stream()
            .map(Map::entrySet)
            .flatMap(Set::stream)
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue, Double::sum));
        
        return weightedAlternativePriorities;
    }
    
    private void assertSupportsCriteria(String criteria) {
        if (!criteriaPriorities.containsKey(criteria)) {
            throw new IllegalArgumentException(String.format("criteria %s is not included in criteria priorities", criteria));
        }
    }

    // alternative must be compared by each criteria
    private void assertSizesOfCriteriasAndAlternativesCorrect() {
        if (criteriaPriorities.size() != alternativePriorities.size()) {
            throw new IllegalStateException("sizes of criteria map and alternatives map do not match");
        }
    }
    
    public static void main(String[] args) {
        Map<String, Double> criteriaPriorities = new HashMap<String, Double>() {
            {
                put("C1", 0.2);
                put("C2", 0.3);
                put("C3", 0.5);
            }
        };
        Map<String, Double> alternativeForC1 = new HashMap<String, Double>() {
            {
                put("a1", 0.5);
                put("a2", 0.5);
            }
        };
        Map<String, Double> alternativeForC2 = new HashMap<String, Double>() {
            {
                put("a1", 0.6);
                put("a2", 0.4);
            }
        };
        Map<String, Double> alternativeForC3 = new HashMap<String, Double>() {
            {
                put("a1", 0.2);
                put("a2", 0.8);
            }
        };
        Map<String, Double> weightedPriorities = new TotalPriorityCalculator()
            .setCriteriaPriorities(criteriaPriorities)
            .addAlternativePrioritiesForCriteria("C1", alternativeForC1)
            .addAlternativePrioritiesForCriteria("C2", alternativeForC2)
            .addAlternativePrioritiesForCriteria("C3", alternativeForC3)
            .getWeightedPriorities();
        
        System.out.println(weightedPriorities);
        
    }
    
}
