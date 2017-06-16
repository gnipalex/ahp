package com.hnyp.ahp.core.data.populators;

import com.hnyp.ahp.core.data.ComparisonTableData;
import com.hnyp.ahp.core.models.ComparisonTable;

public class ComparisonTableDataBasicPopulator implements Populator<ComparisonTable, ComparisonTableData> {

    @Override
    public void populate(ComparisonTable source, ComparisonTableData target) {
        target.setId(source.getId());
        target.setProjectDecisionId(source.getProjectDecision().getId());
        target.setConsistencyRatio(source.getConsistencyRatio());
        target.setFinished(source.isFinished());
        target.setConsistent(source.isConsistent());
    }

}
