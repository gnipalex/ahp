package com.hnyp.ahp.core.data.populators;

import com.hnyp.ahp.core.data.ComparisonTableData;
import com.hnyp.ahp.core.models.ComparisonTable;

public class ComparisonTableBasicPopulator implements Populator<ComparisonTable, ComparisonTableData> {

    @Override
    public void populate(ComparisonTable source, ComparisonTableData target) {
        target.setProjectDecisionId(source.getProjectDecision().getId());
        target.setConsistencyRatio(source.getConsistencyRatio());
        target.setFinished(source.isFinished());
        // TODO this logic should be extracted
        target.setConsistent(source.getConsistencyRatio() <= 0.1D);
    }

}
