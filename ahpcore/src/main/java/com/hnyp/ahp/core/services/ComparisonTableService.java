package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.models.ComparableItem;
import com.hnyp.ahp.core.models.ComparisonPair;
import com.hnyp.ahp.core.models.ComparisonTable;

public interface ComparisonTableService {

    List<ComparableItem> getComparableItems(ComparisonTable comparisonTable);
    
    void calculate(ComparisonTable comparisonTable);
    
    void update(ComparisonTable comparisonTable, List<ComparisonPair> comparisonPairs);
    
}
