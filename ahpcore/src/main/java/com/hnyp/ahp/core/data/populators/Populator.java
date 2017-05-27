package com.hnyp.ahp.core.data.populators;

public interface Populator<S, T> {

    void populate(S source, T target);
    
}
