package com.hnyp.ahp.lib;

import java.util.*;

public class ConsistencyVerifier {

    private ComparisonMatrix comparisonMatrix;
    private Comparison[][] comparisons;
    private List<String> alternatives;

    public ConsistencyVerifier(ComparisonMatrix comparisonMatrix) {
        this.comparisonMatrix = comparisonMatrix;
        this.comparisons = comparisonMatrix.getComparisons();
        this.alternatives = comparisonMatrix.getAlternativeNames();
    }

    // not finished
    public List<InconsistencyPath> getInconsistentPaths() {
        List<InconsistencyPath> paths = new ArrayList<>();
        for (int r = 0; r < comparisons.length; r++) {
            for (int c = 0; c < comparisons[r].length; c++) {
                Comparison comparison = comparisons[r][c];
                List<InconsistencyPath> allInconsistentPathsForComparison = getPath(comparison);
                paths.addAll(allInconsistentPathsForComparison);
            }
        }
        return paths;
    }

    private List<InconsistencyPath> getPath(Comparison startComparison) {
        InconsistencyPath path = new InconsistencyPath();
        return continuePathForAlternative(path, startComparison.getAlternativeA());
    }

    private List<InconsistencyPath> continuePathForAlternative(InconsistencyPath path, String nextAlternative) {
        List<InconsistencyPath> inconsistencyPaths = new ArrayList<>();
        int indexOfAlternative = alternatives.indexOf(nextAlternative);
        for (int c = 0; c < comparisons[indexOfAlternative].length; c++) {
            Comparison comparison = comparisons[indexOfAlternative][c];
            double comparisonsValue = comparison.getValue().value();
            if (comparisonsValue > 1) {
                if (path.contains(comparison)) {
                    inconsistencyPaths.add(path);
                } else {
                    InconsistencyPath clonedPath = new InconsistencyPath(path);
                    clonedPath.add(comparison);
                    List<InconsistencyPath> foundPaths = continuePathForAlternative(clonedPath, comparison.getAlternativeB());
                    inconsistencyPaths.addAll(foundPaths);
                }
            }
        }
        return inconsistencyPaths;
    }

    public static void main(String[] args) {
        HashSet<String> alternativeNames = new LinkedHashSet<>(Arrays.asList("telephone", "mouse", "watch", "computer", "earphones"));
        ComparisonMatrix comparisonMatrix = new ComparisonMatrix(alternativeNames);
        comparisonMatrix.update(0, 1, ComparisonScale.SLIGHTLY_FAVORS);
        comparisonMatrix.update(0, 2, ComparisonScale.STRONGLY_FAVORS);
        comparisonMatrix.update(0, 3, ComparisonScale.SLIGHTLY_CONCEDE);
        comparisonMatrix.update(0, 4, ComparisonScale.VERY_STRONGLY_FAVORS);
        comparisonMatrix.update(1, 2, ComparisonScale.STRONGLY_CONCEDE);
        comparisonMatrix.update(1, 3, ComparisonScale.EXTREME_CONCEDE);
        comparisonMatrix.update(1, 4, ComparisonScale.SLIGHTLY_FAVORS);
        comparisonMatrix.update(2, 3, ComparisonScale.STRONGLY_CONCEDE);
        comparisonMatrix.update(2, 4, ComparisonScale.SLIGHTLY_CONCEDE);
        comparisonMatrix.update(3, 4, ComparisonScale.VERY_STRONGLY_FAVORS);

        System.out.println(comparisonMatrix);

        ConsistencyVerifier consistencyVerifier = new ConsistencyVerifier(comparisonMatrix);

        List<InconsistencyPath> inconsistencyPaths = consistencyVerifier.getInconsistentPaths();

        inconsistencyPaths.forEach(it -> System.out.println(it.getPath()));

    }

}
