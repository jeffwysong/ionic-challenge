package com.wysong.ionicchallenge.business;

import org.springframework.stereotype.Component;

@Component
public class DefaultIntegerMatrixValidator implements IntegerMatrixValidator {

    @Override
    public int[][] verify(Integer[][] toCheck) {
        if (toCheck == null) {
            throw new IllegalStateException("Input must not be null.");
        }

        int numRows = toCheck.length;
        int columns = toCheck[0].length;
        int[][] toReturn = new int[numRows][columns];

        for (int row = 0; row < toCheck.length; row++) {
            Integer[] rows = toCheck[row];
            for (int col = 0; col < rows.length; col++) {
                Integer integer = rows[col];
                if (integer == null) {
                    toReturn[row][col] = Integer.MIN_VALUE;
                } else {
                    toReturn[row][col] = integer;
                }
            }
        }
        return toReturn;
    }
}
