package com.hnyp.ahp.core.data.populators;

import com.hnyp.ahp.core.data.ComparisonPairData;
import com.hnyp.ahp.core.models.ComparisonPair;

public class ComparisonPairDataPopulator implements Populator<ComparisonPair, ComparisonPairData> {

    @Override
    public void populate(ComparisonPair source, ComparisonPairData target) {
        target.setId(source.getId());
        target.setValue(source.getValue());
    }

}
