package com.hnyp.ahp.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.SessionFactory;

import com.hnyp.ahp.core.exception.ComparisonTableCalculationException;
import com.hnyp.ahp.core.models.ComparableItem;
import com.hnyp.ahp.core.models.ComparisonPair;
import com.hnyp.ahp.core.models.ComparisonResultValue;
import com.hnyp.ahp.core.models.ComparisonTable;
import com.hnyp.ahp.core.services.ComparisonTableService;
import com.hnyp.ahp.core.services.ModelService;
import com.hnyp.ahp.lib.Comparison;
import com.hnyp.ahp.lib.ComparisonMatrix;
import com.hnyp.ahp.lib.PriorityMatrix;

public class DefaultComparisonTableService implements ComparisonTableService {

    private static final double TABLE_CONSISTENCY_THRESHOLD = 0.1D;
    @Resource
    private SessionFactory sessionFactory;
    @Resource
    private ModelService modelService;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<ComparableItem> getComparableItems(ComparisonTable comparisonTable) {
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct comparableItem from ComparisonPair cp"
                                + " inner join cp.itemA as comparableItem"
                                + " inner join cp.comparisonTable as comparisonTable"
                                + " where comparisonTable = :comparisonTable")
                .setParameter("comparisonTable", comparisonTable)
                .list();
    }

    @Override
    public void calculate(ComparisonTable comparisonTable) {
        List<ComparisonPair> comparisonPairs = comparisonTable.getComparisonPairs();
        if (CollectionUtils.isEmpty(comparisonPairs)) {
            throw new ComparisonTableCalculationException(String.format("Comparison table %s does not contain comparison pairs", comparisonTable.getId()));
        }
        
        List<ComparableItem> comparableItems = getComparableItems(comparisonTable);
        
        try {
            ComparisonMatrix comparisonMatrix = createComparisonMatrix(comparisonTable, comparableItems);
            PriorityMatrix priorityMatrix = comparisonMatrix.createPriorityMatrix();
             
            comparisonTable.setConsistencyIndex(priorityMatrix.getConsistencyIndex());
            comparisonTable.setConsistencyRatio(priorityMatrix.getConsistencyRatio());
            comparisonTable.setConsistent(priorityMatrix.getConsistencyRatio() <= TABLE_CONSISTENCY_THRESHOLD);
            
            Map<String, Double> computedPriorityMap = getPriorityMap(comparisonMatrix, priorityMatrix);

            updateComparisonResults(comparisonTable, comparableItems, computedPriorityMap);

        } catch (Exception e) {
            throw new ComparisonTableCalculationException(String.format("Calculation of comparison table %s failed", comparisonTable.getId()), e);
        }
        
        comparisonTable.setCalculated(true);
        
        modelService.save(comparisonTable);
    }
    
    private Map<String, Double> getPriorityMap(ComparisonMatrix comparisonMatrix, PriorityMatrix priorityMatrix) {
        Map<String, Double> priorityMap = new HashMap<>();
        double[] priorities = priorityMatrix.getPriorities();
        List<String> alternativeNames = comparisonMatrix.getAlternativeNames();
        for (int i=0; i<alternativeNames.size(); i++) {
            priorityMap.put(alternativeNames.get(i), priorities[i]);
        }
        return priorityMap;
    }
    
    private void updateComparisonResults(ComparisonTable comparisonTable, List<ComparableItem> comparableItems, Map<String, Double> priorityMap) {
        
        List<ComparisonResultValue> comparisonResults = wrap(comparisonTable.getComparisonResults());
        
        priorityMap.entrySet().forEach(e -> {
            String alternativeName = e.getKey();
            double priority = e.getValue();
            ComparisonResultValue comparisonResult = findResultValueForComparableItemName(comparisonResults, alternativeName);
            if (comparisonResult == null) {
                ComparableItem comparableItem = findComparableItemByName(comparableItems, alternativeName);
                comparisonResult = createComparisonResultValue(comparableItem, priority);
                comparisonResults.add(comparisonResult);
            } else {
                comparisonResult.setValue(priority);
            }
        });
        
        comparisonTable.setComparisonResults(comparisonResults);
    }
    
    private <T> List<T> wrap(List<T> list) {
        if (list == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(list);
    }
    
    private ComparisonResultValue createComparisonResultValue(ComparableItem comparableItem, double value) {
        ComparisonResultValue comparisonResultValue = new ComparisonResultValue();
        comparisonResultValue.setComparableItem(comparableItem);
        comparisonResultValue.setValue(value);
        return comparisonResultValue;
    }
    
    private ComparisonResultValue findResultValueForComparableItemName(List<ComparisonResultValue> comparisonResultValues, String comparableItemName) {
        return comparisonResultValues.stream()
                .filter(v -> Objects.equals(comparableItemName, v.getComparableItem().getName()))
                .findFirst()
                .orElse(null);
    }
    
    private ComparableItem findComparableItemByName(List<ComparableItem> items, String name) {
        if (CollectionUtils.isNotEmpty(items)) {
            return items.stream().filter(item -> Objects.equals(item.getName(), name))
                .findFirst()
                .get();
        }
        return null;
    }
    
    private ComparisonMatrix createComparisonMatrix(ComparisonTable comparisonTable, List<ComparableItem> comparableItems) {
        List<ComparisonPair> comparisonPairs = comparisonTable.getComparisonPairs();
        
        Set<String> alternativeNames = comparableItems.stream().map(ComparableItem::getName).collect(Collectors.toSet());
        ComparisonMatrix comparisonMatrix = new ComparisonMatrix(alternativeNames);
        
        comparisonPairs.forEach(pair -> {
            comparisonMatrix.update(new Comparison(pair.getItemA().getName(), 
                    pair.getItemB().getName(), 
                    pair.getValue())
                    );
        });
        
        return comparisonMatrix;
    }

    @Override
    public void update(ComparisonTable comparisonTable, List<ComparisonPair> comparisonPairs) {
        comparisonPairs.forEach(pair -> {
            ComparisonPair comparisonPairToUpdate = getComparisonPairById(comparisonTable, pair.getId());
            comparisonPairToUpdate.setValue(pair.getValue());
            sessionFactory.getCurrentSession().persist(comparisonPairToUpdate);
        });
        comparisonTable.setCalculated(false);
        comparisonTable.setConsistencyIndex(0);
        comparisonTable.setConsistencyRatio(0);
        comparisonTable.setConsistent(false);
        modelService.save(comparisonTable);
    }
    
    private ComparisonPair getComparisonPairById(ComparisonTable comparisonTable, long id) {
        List<ComparisonPair> comparisonPairs = comparisonTable.getComparisonPairs();
        if (CollectionUtils.isEmpty(comparisonPairs)) {
            throw new IllegalStateException(String.format("comparison table %s doesn't contain any comparison pair", comparisonTable.getId()));
        }
        return comparisonPairs.stream().filter(pair -> pair.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("comparison table %s doesn't contain comparison pair %s", 
                        comparisonTable.getId(), id)));
    }


}
