package com.hnyp.ahp.core.data.populators;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;

import com.hnyp.ahp.core.data.AlternativeComparisonTableData;
import com.hnyp.ahp.core.data.CriteriaData;
import com.hnyp.ahp.core.models.AlternativeComparisonTable;
import com.hnyp.ahp.core.models.Criteria;

public class AlternativeComparisonTableDataPopulator implements Populator<AlternativeComparisonTable, AlternativeComparisonTableData> {

    @Resource
    private Converter<Criteria, CriteriaData> criteriaDataConverter;
    
    @Override
    public void populate(AlternativeComparisonTable source, AlternativeComparisonTableData target) {
        target.setCriteria(criteriaDataConverter.convert(source.getCriteria()));
    }

}
