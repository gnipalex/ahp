package com.hnyp.ahp.core.data.populators;

import com.hnyp.ahp.core.data.ComparableItemData;
import com.hnyp.ahp.core.models.ComparableItem;

public class ComparableItemPopulator implements Populator<ComparableItem, ComparableItemData> {

    @Override
    public void populate(ComparableItem source, ComparableItemData target) {
        target.setId(source.getId());
        target.setDescription(source.getDescription());
        target.setName(source.getName());
    }

}
