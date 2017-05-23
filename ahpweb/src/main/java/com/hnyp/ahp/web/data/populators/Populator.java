package com.hnyp.ahp.web.data.populators;

public interface Populator<S, T> {

    void populate(S source, T target);
    
}
