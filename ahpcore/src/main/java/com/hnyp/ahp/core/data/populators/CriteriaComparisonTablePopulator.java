package com.hnyp.ahp.core.data.populators;

import com.hnyp.ahp.core.data.CriteriaComparisonTableData;
import com.hnyp.ahp.core.models.CriteriaComparisonTable;

public class CriteriaComparisonTablePopulator implements Populator<CriteriaComparisonTable, CriteriaComparisonTableData> {

    @Override
    public void populate(CriteriaComparisonTable source, CriteriaComparisonTableData target) {
        target.setName(source.getName());
        target.setDescription(source.getDescription());
    }
   
}
