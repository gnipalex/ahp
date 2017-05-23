package com.hnyp.ahp.lib;

import java.util.HashMap;
import java.util.Map;

public enum ComparisonScale {

    EQUAL(1),
    SLIGHTLY_FAVORS(3),
    STRONGLY_FAVORS(5),
    VERY_STRONGLY_FAVORS(7),
    EXTREME_FAVORS(9),

    SLIGHTLY_CONCEDE(1D / 3),
    STRONGLY_CONCEDE(1D / 5),
    VERY_STRONGLY_CONCEDE(1D / 7),
    EXTREME_CONCEDE(1D / 9);

    private static final Map<ComparisonScale, ComparisonScale> STRONGLY_TO_CONCEDE_MAPPING = new HashMap<>();
    private static final Map<ComparisonScale, ComparisonScale> CONCEDE_TO_STRONGLY_MAPPING = new HashMap<>();

    static {
        STRONGLY_TO_CONCEDE_MAPPING.put(SLIGHTLY_FAVORS, SLIGHTLY_CONCEDE);
        STRONGLY_TO_CONCEDE_MAPPING.put(STRONGLY_FAVORS, STRONGLY_CONCEDE);
        STRONGLY_TO_CONCEDE_MAPPING.put(VERY_STRONGLY_FAVORS, VERY_STRONGLY_CONCEDE);
        STRONGLY_TO_CONCEDE_MAPPING.put(EXTREME_FAVORS, EXTREME_CONCEDE);
    }

    static {
        CONCEDE_TO_STRONGLY_MAPPING.put(SLIGHTLY_CONCEDE, SLIGHTLY_FAVORS);
        CONCEDE_TO_STRONGLY_MAPPING.put(STRONGLY_CONCEDE, STRONGLY_FAVORS);
        CONCEDE_TO_STRONGLY_MAPPING.put(VERY_STRONGLY_CONCEDE, VERY_STRONGLY_FAVORS);
        CONCEDE_TO_STRONGLY_MAPPING.put(EXTREME_CONCEDE, EXTREME_FAVORS);
    }

    private double value;

    ComparisonScale(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }

    public ComparisonScale invert() {
        if (this == EQUAL) {
            return this;
        }
        ComparisonScale invertedComparisonScale = STRONGLY_TO_CONCEDE_MAPPING.get(this);
        if (invertedComparisonScale == null) {
            invertedComparisonScale = CONCEDE_TO_STRONGLY_MAPPING.get(this);
        }
        if (invertedComparisonScale == null) {
            throw new IllegalStateException("error, there is no opposite scale value configured for " + this);
        }
        return invertedComparisonScale;
    }
}
