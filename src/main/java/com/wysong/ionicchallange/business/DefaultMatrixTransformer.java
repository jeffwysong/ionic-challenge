package com.wysong.ionicchallange.business;

import org.springframework.stereotype.Component;

@Component
public class DefaultMatrixTransformer implements MatrixTransformer {

    @Override
    public boolean[][] isAboveThreshold(int[][] toCheck, int threshold) {
        int rows = toCheck.length;
        int columns = toCheck[0].length;
        boolean[][] toReturn = new boolean[rows][columns];
        for (int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                if (toCheck[row][col] >= threshold) {
                    toReturn[row][col] = true;
                }
            }
        }
        return toReturn;
    }
}
