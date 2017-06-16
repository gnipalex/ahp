package com.hnyp.ahp.lib;


import java.util.Objects;

public class Comparison {

    private ComparisonScale value;
    private String alternativeA;
    private String alternativeB;

    public Comparison() {}

    public Comparison(String alternativeA, String alternativeB, ComparisonScale value) {
        this.alternativeA = alternativeA;
        this.alternativeB = alternativeB;
        this.value = value;
    }
    
    public Comparison(Comparison original) {
        this.alternativeA = original.alternativeA;
        this.alternativeB = original.alternativeB;
        this.value = original.value;
    }

    public ComparisonScale getValue() {
        return value;
    }

    public Comparison setValue(ComparisonScale value) {
        this.value = value;
        return this;
    }

    public String getAlternativeA() {
        return alternativeA;
    }

    public Comparison setAlternativeA(String alternativeA) {
        this.alternativeA = alternativeA;
        return this;
    }

    public String getAlternativeB() {
        return alternativeB;
    }

    public Comparison setAlternativeB(String alternativeB) {
        this.alternativeB = alternativeB;
        return this;
    }

    public boolean haveEqualAlternatives(Object otherComparison) {
        if (otherComparison == null) return false;
        Comparison that = (Comparison) otherComparison;
        return Objects.equals(alternativeA, that.alternativeA)
                && Objects.equals(alternativeB, that.alternativeB);
    }

    @Override
    public String toString() {
        return alternativeA + " - " + value + " - " + alternativeB;
    }
}
