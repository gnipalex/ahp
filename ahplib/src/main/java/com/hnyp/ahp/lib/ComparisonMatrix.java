package com.hnyp.ahp.lib;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;


public class ComparisonMatrix {

    private Comparison[][] comparisonsMatrix;
    private List<String> alternativeNames;

    public ComparisonMatrix(Set<String> alternatives) {
        if (alternatives.size() < 2) {
            throw new IllegalArgumentException("alternatives count " + alternatives.size() + " is incorrect, should be >=2");
        }
        this.alternativeNames = new ArrayList<>(alternatives);
        this.comparisonsMatrix = createComparisonsMatrix();
    }

    private Comparison[][] createComparisonsMatrix() {
        Comparison[][] matrix = new Comparison[alternativeNames.size()][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new Comparison[alternativeNames.size()];
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Comparison()
                        .setAlternativeA(alternativeNames.get(i))
                        .setAlternativeB(alternativeNames.get(j))
                        .setValue(ComparisonScale.EQUAL);
            }
        }
        return matrix;
    }

    public void update(int row, int column, ComparisonScale value) {
        Comparison comparison = comparisonsMatrix[row][column];
        comparison.setValue(value);
        update(comparison);
    }

    public void update(Comparison comparison) {
        assertComparisonIsValid(comparison);
        updateInternal(comparison);
        updateInternal(invertAlternative(comparison));
    }

    private Comparison invertAlternative(Comparison comparison) {
        Comparison invertedComparison = new Comparison();
        invertedComparison.setAlternativeA(comparison.getAlternativeB());
        invertedComparison.setAlternativeB(comparison.getAlternativeA());
        invertedComparison.setValue(comparison.getValue().invert());
        return invertedComparison;
    }

    private void updateInternal(Comparison comparison) {
        int row = alternativeNames.indexOf(comparison.getAlternativeA());
        int column = alternativeNames.indexOf(comparison.getAlternativeB());
        comparisonsMatrix[row][column] = new Comparison(comparison);
    }

    private void assertComparisonIsValid(Comparison comparison) {
        if(!alternativeNames.contains(comparison.getAlternativeA())
                || !alternativeNames.contains(comparison.getAlternativeB())) {
            throw new IllegalArgumentException("illegal comparison, current matrix is set up to work with [" + alternativeNames
                    + "] alternatives, while provided comparison is [" + comparison + "]");
        }
        if (Objects.equals(comparison.getAlternativeA(), comparison.getAlternativeB())
                && !Objects.equals(comparison.getValue(), ComparisonScale.EQUAL)) {
            throw new IllegalArgumentException("illegal comparison [" + comparison +
                    "],comparison value should be " + ComparisonScale.EQUAL);
        }
    }

    public Comparison[][] getComparisons() {
        Comparison[][] clone = new Comparison[comparisonsMatrix.length][comparisonsMatrix[0].length];
        for (int i = 0; i < clone.length; i++) {
            clone[i] = ArrayUtils.clone(comparisonsMatrix[i]);
        }
        return clone;
    }

    public List<String> getAlternativeNames() {
        return new ArrayList<>(alternativeNames);
    }

    public PriorityMatrix createPriorityMatrix() {
        double[][] comparisonValues = createComparisonValuesMatrix();
        return new PriorityMatrix(comparisonValues);
    }

    private double[][] createComparisonValuesMatrix() {
        double[][] comparisonValues = new double[comparisonsMatrix.length][comparisonsMatrix[0].length];
        for (int i = 0; i < comparisonValues.length; i++) {
            for (int j = 0; j < comparisonValues[i].length; j++) {
                comparisonValues[i][j] = comparisonsMatrix[i][j].getValue().value();
            }
        }
        return comparisonValues;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\t\t");
        alternativeNames.forEach(it -> {
            out.append(it);
            out.append("\t");
        });
        out.append("\n");
        for (int i = 0; i < alternativeNames.size(); i++) {
            out.append(alternativeNames.get(i));
            out.append("\t");
            Arrays.stream(comparisonsMatrix[i]).forEach(it -> {
                out.append(it.getValue().value());
                out.append("\t");
            });
            out.append("\n");
        }
        return out.toString();
    }

    public static void main(String[] args) {
        HashSet<String> alternativeNames = new HashSet<>(Arrays.asList("book", "music", "walk"));
        ComparisonMatrix comparisonMatrix = new ComparisonMatrix(alternativeNames);
        System.out.println("Comparison matrix created :");
        System.out.println(comparisonMatrix);

        comparisonMatrix.update(new Comparison()
                    .setAlternativeA("book")
                    .setAlternativeB("music")
                    .setValue(ComparisonScale.STRONGLY_CONCEDE));

        System.out.println("Book - music comparison changed");
        System.out.println(comparisonMatrix);

//        comparisonMatrix.update(new Comparison()
//                .setAlternativeA("book")
//                .setAlternativeB("book")
//                .setValue(ComparisonScale.STRONGLY_CONCEDE));
//
//        System.out.println("Book - book comparison changed");
//        System.out.println(comparisonMatrix);

        comparisonMatrix.update(new Comparison()
                .setAlternativeA("book")
                .setAlternativeB("walk")
                .setValue(ComparisonScale.EXTREME_CONCEDE));

        System.out.println("Book - walk comparison changed");
        System.out.println(comparisonMatrix);

        comparisonMatrix.update(new Comparison()
                .setAlternativeA("music")
                .setAlternativeB("walk")
                .setValue(ComparisonScale.VERY_STRONGLY_FAVORS));

        System.out.println("music - walk comparison changed");
        System.out.println(comparisonMatrix);

        double[][] comparisonValuesMatrix = comparisonMatrix.createComparisonValuesMatrix();

        System.out.println("Comparison values matrix: ");
        System.out.println(Arrays.toString(comparisonValuesMatrix));

        PriorityMatrix priorityMatrix = comparisonMatrix.createPriorityMatrix();
        System.out.println("Priorities: ");
        System.out.println(Arrays.toString(priorityMatrix.getPriorities()));

    }

}
