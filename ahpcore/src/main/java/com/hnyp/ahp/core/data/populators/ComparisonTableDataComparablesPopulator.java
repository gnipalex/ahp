package com.hnyp.ahp.core.data.populators;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.ComparableItemData;
import com.hnyp.ahp.core.data.ComparisonTableData;
import com.hnyp.ahp.core.models.ComparableItem;
import com.hnyp.ahp.core.models.ComparisonTable;
import com.hnyp.ahp.core.models.Criteria;

public class ComparisonTableDataComparablesPopulator<S extends ComparisonTable, T extends ComparisonTableData> implements Populator<S, T> {
    
    @Resource
    private Converter<ComparableItem, ComparableItemData> comparableItemDataConverter;
    
    @Override
    public void populate(S source, T target) {
        List<ComparableItemData> convertedComparableItemsDataList = source.getComparables().stream()
//                .map(Criteria.class::cast)
                .map(comparableItemDataConverter::convert)
                .collect(Collectors.toList());
        target.setComparableItems(convertedComparableItemsDataList);
    }

}
