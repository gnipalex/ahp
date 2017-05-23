package com.hnyp.ahp.lib;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class InconsistencyPath {

    private List<Comparison> path = new ArrayList<>();

    public InconsistencyPath() { }

    public InconsistencyPath(InconsistencyPath original) {
        this.path.addAll(original.path);
    }

    public List<Comparison> getPath() {
        return path;
    }

    public void add(Comparison comparison) {
        if (!contains(comparison)) {
            path.add(comparison);
        }
    }

    public boolean contains(Comparison comparison) {
        return CollectionUtils.exists(path, comparison::haveEqualAlternatives);
    }

    public Comparison getInconsistentComparison() {
        return path.size() > 0 ? path.get(path.size() - 1) : null;
    }
}
