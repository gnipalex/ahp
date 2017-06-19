package com.hnyp.ahp.lib;


import org.apache.commons.math3.linear.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class PriorityMatrix {

    private static final double[] RANDOM_CONSISTENCY_INDEX = {0, 0, 0.58, 0.9, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49};

    private EigenDecomposition eigenDecomposition;
    private RealMatrix matrix;
    private double consistencyIndex;
    private double maxEigenValue;
    private RealVector eigenValues;

    public PriorityMatrix(double[][] priorities) {
        assertInputMatrixIsCorrect(priorities);
        assertMatrixSizeIsSupported(priorities);
        matrix = new Array2DRowRealMatrix(priorities);
        eigenDecomposition = new EigenDecomposition(matrix);
        maxEigenValue = getMaxEigenValue();
        eigenValues = getEigenVector();
        consistencyIndex = getConsistencyIndex(maxEigenValue, matrix.getColumnDimension());
    }

    private void assertMatrixSizeIsSupported(double[][] priorities) {
        if (priorities.length > RANDOM_CONSISTENCY_INDEX.length) {
            throw new IllegalArgumentException("matrix size " + priorities.length + " is not supported");
        }
    }

    private void assertInputMatrixIsCorrect(double[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("input matrix is null");
        }
        assertIsSquareMatrix(matrix);
        assertMatrixHasPositiveValues(matrix);
    }

    private void assertIsSquareMatrix(double[][] matrix) {
        int firstRowLength = matrix[0].length;
        if (matrix.length != firstRowLength) {
            throw new IllegalArgumentException("matrix is not square matrix, first row length is " + firstRowLength + ", rows count " + matrix.length);
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == null) {
                throw new IllegalArgumentException("row " + i + " is not set (null)");
            }
            if (firstRowLength != matrix[i].length) {
                throw new IllegalArgumentException("matrix row " + i + " has length " + matrix[i].length + " while first row has length " + firstRowLength);
            }
        }
    }

    private void assertMatrixHasPositiveValues(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] < 0) {
                    throw new IllegalArgumentException("element [" + i + "," + j + "] " + matrix[i][j] + " should have positive value");
                }
            }
        }
    }

    private int getMaxEigenValueIndex() {
        double[] allEigenValues = eigenDecomposition.getRealEigenvalues();
        int index = 0;
        double max = allEigenValues[0];
        for (int i = 0; i < eigenDecomposition.getRealEigenvalues().length; i++) {
            if (allEigenValues[i] > max) {
                max = allEigenValues[i];
                index = i;
            }
        }
        return index;
    }

    // max eigen value is also called principal eigen value
    private double getMaxEigenValue() {
        int maxEigenValueIndex = getMaxEigenValueIndex();
        return eigenDecomposition.getRealEigenvalues()[maxEigenValueIndex];
    }

    // is based on max eigen value
    private RealVector getEigenVector() {
        int maxEigenValueIndex = getMaxEigenValueIndex();
        return eigenDecomposition.getEigenvector(maxEigenValueIndex);
    }

    private RealVector normalize(RealVector realVector) {
        double[] values = realVector.toArray();
        double sum = Arrays.stream(values).sum();
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] / sum;
        }
        return new ArrayRealVector(values);
    }

    private double getConsistencyIndex(double eigenValue, int matrixDimentsion) {
        double consistencyIndex = (eigenValue - matrixDimentsion) / (matrixDimentsion - 1);
        return BigDecimal.valueOf(consistencyIndex).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double getConsistencyIndex() {
        return consistencyIndex;
    }

    public double[] getPriorities() {
        return normalize(eigenValues).toArray();
    }

    public double getEigenValue() {
        return maxEigenValue;
    }

    private double getRandomIndex() {
        int dimension = matrix.getColumnDimension();
        return RANDOM_CONSISTENCY_INDEX[dimension - 1];
    }

    public double getConsistencyRatio() {
        if (consistencyIndex > 0) {
            return consistencyIndex / getRandomIndex();
        } else {
            return 0D;
        }
    }

    public static void main(String[] args) {
        PriorityMatrix priorityMatrix1 = new PriorityMatrix(new double[][] {
                {1, 0.33, 3, 5, 5},
                {3, 1, 3, 3, 1},
                {0.33, 0.33, 1, 5, 7},
                {0.2, 0.33, 0.2, 1, 0.33},
                {0.2, 1, 0.14, 3, 1}
        });

        PriorityMatrix priorityMatrix = new PriorityMatrix(new double[][]{
                {1, 0.33, 5},
                {3, 1, 7},
                {0.2, 0.14, 1}
        });
        System.out.println("Priorities : " + Arrays.toString(priorityMatrix.getPriorities()));
        System.out.println("Lmax :" + priorityMatrix.maxEigenValue);
        System.out.println("Consistency index : " + priorityMatrix.getConsistencyIndex());
        System.out.println("Consistency ratio : " + priorityMatrix.getConsistencyRatio());
    }

}
