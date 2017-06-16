package com.hnyp.ahp.core.data.populators;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ComparisonPairData;
import com.hnyp.ahp.core.data.ComparisonTableData;
import com.hnyp.ahp.core.models.ComparableItem;
import com.hnyp.ahp.core.models.ComparisonPair;
import com.hnyp.ahp.core.models.ComparisonTable;
import com.hnyp.ahp.core.services.ComparisonTableService;

public class ComparisonTableDataPairsPopulator<S extends ComparisonTable, T extends ComparisonTableData> implements Populator<S, T> {

    @Resource
    private ComparisonTableService comparisonTableService;
    
    @Resource
    private Converter<ComparisonPair, ComparisonPairData> comparisonPairDataConverter;
    
    @Override
    public void populate(S source, T target) {
        Map<String, Map<String, List<ComparisonPair>>> comparisonPairsGrouped = source.getComparisonPairs().stream()
                .collect(Collectors.groupingBy(cp -> cp.getItemA().getName(), 
                            Collectors.groupingBy(cp -> cp.getItemB().getName()))
                 );
        
        List<ComparableItem> comparableItems = comparisonTableService.getComparableItems(source);
        
        List<String> comparisonItemNames = comparableItems.stream().map(ComparableItem::getName).collect(Collectors.toList());
        if (isStructureValid(comparisonPairsGrouped, comparisonItemNames)) {
            Map<String, Map<String, ComparisonPairData>> convertedTable = convertComparisonTable(comparisonPairsGrouped, comparisonItemNames);
            target.setComparisonTable(convertedTable);
            target.setStructureValid(true);
        } else {
            target.setStructureValid(false);
        }
    }
    
    private Map<String, Map<String, ComparisonPairData>> convertComparisonTable(Map<String, Map<String, 
            List<ComparisonPair>>> comparisonPairsGrouped, List<String> comparisonItemNames) {
        Map<String, Map<String, ComparisonPairData>> convertedTable = new LinkedHashMap<>();
        comparisonItemNames.stream().forEach(rowName -> {
            Map<String, ComparisonPairData> convertedRow = new LinkedHashMap<>();
            comparisonItemNames.stream().forEach(columnName -> {
                ComparisonPair pair = comparisonPairsGrouped.get(rowName).get(columnName).get(0);
                convertedRow.put(columnName, comparisonPairDataConverter.convert(pair));
            });
            convertedTable.put(rowName, convertedRow);
        });
        
        return convertedTable;
    }
    
    // structure is valid when there is a row and column for given comparisonItemName
    private boolean isStructureValid(Map<String, Map<String, List<ComparisonPair>>> comparisonPairsGrouped, List<String> comparisonItemNames) {
        for (String name : comparisonItemNames) {
            Map<String, List<ComparisonPair>> row = comparisonPairsGrouped.get(name);
            if (row == null || row.get(name) == null || row.get(name).isEmpty()) {
                return false;
            }
        }
        return true;
    }


}
