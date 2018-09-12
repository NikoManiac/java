package com.cultivation.javaBasicExtended.matrixMultiplication;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import sun.security.krb5.internal.crypto.Aes128;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings({"WeakerAccess", "unused"})
class Matrix {
    private final int[][] storage;

    public int rows() {return storage.length;}

    public int columns() {return storage[0].length;}

    public Matrix(int[][] matrixArray) {
        // TODO: please implement the constructor of a matrix.
        // <--start
        if (matrixArray == null) {
            throw new IllegalArgumentException("Raw matrix is null");
        }
        if (matrixArray.length == 0) {
            throw new IllegalArgumentException("Raw matrix contains 0 row");
        }

        boolean isNullRow = Arrays.stream(matrixArray).filter(item -> {
            return item == null;}).count() > 0;
        if (isNullRow) {
            throw new IllegalArgumentException("Raw matrix contains null row");
        }

        boolean isNullColumn = Arrays.stream(matrixArray).filter(item -> item.length == 0).count() > 0;
        if (isNullColumn) {
            throw new IllegalArgumentException("At least one row of raw matrix contains 0 column");
        }
        int firstRowLength = matrixArray[0].length;
        long notMatch = Arrays.stream(matrixArray).filter(item -> item.length != firstRowLength).count();

        if (notMatch > 0) {
            throw new IllegalArgumentException("Raw matrix is not rectangle");
        }

        this.storage = matrixArray;
        // --end-->
    }

    public static Matrix multiply(Matrix left, Matrix right) {
        // TODO: please implement the method to pass the tests.
        // <--start
        if (left == null || right == null) {
            throw new IllegalArgumentException();
        }

        if (left.columns() != right.rows()) {
            throw new IllegalArgumentException();
        }
        int resultStorage[][] = new int[left.rows()][right.columns()];
        for (int leftRowIndex = 0; leftRowIndex < left.rows(); leftRowIndex++) {
            for (int rightColimnIndex = 0; rightColimnIndex < right.columns(); rightColimnIndex++) {
                int temp = 0;
                for (int rightRowIndex = 0; rightRowIndex < right.rows(); rightRowIndex++) {
                    temp += left.getRow(leftRowIndex)[rightRowIndex] * right.getRow(rightRowIndex)[rightColimnIndex];
                }
                resultStorage[leftRowIndex][rightColimnIndex] = temp;
            }
        }

        return new Matrix(resultStorage);
        // --end-->
    }

    // TODO: you can add some helper method if you like.
    // <--start

    // --end->

    public int[] getRow(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= rows()) { throw new IllegalArgumentException(); }
        return storage[rowIndex];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (this == obj) { return true; }
        if (Matrix.class != obj.getClass()) { return false; }

        Matrix matrix = (Matrix) obj;
        if (rows() != matrix.rows() || columns() != matrix.columns()) {
            return false;
        }

        int rows = rows();
        for (int rowIndex = 0; rowIndex < rows; ++rowIndex) {
            if (!Arrays.equals(getRow(rowIndex), matrix.getRow(rowIndex))) { return false; }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = Arrays.hashCode(getRow(0));
        int rows = rows();
        for (int rowIndex = 1; rowIndex < rows; ++rowIndex) {
            hash ^= Arrays.hashCode(getRow(rowIndex));
        }

        return hash;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(storage)
            .forEach(row -> formatRow(builder, row));
        return builder.toString();
    }

    private void formatRow(StringBuilder builder, int[] row) {
        for (int item : row) {
            builder.append(String.format("%-10s", Integer.toString(item)));
        }
        builder.append("\n");
    }
}
