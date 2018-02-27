package com.wysong.ionicchallange;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MatrixChecker {

    public boolean[][] aboveThreshold(Integer[][] toCheck, int threshold) {
        int rows = toCheck.length;
        int columns = toCheck[0].length;
        boolean[][] toReturn = new boolean[rows][columns];
        for (int row = 0; row < rows; row++) {
            for(int col = 0; col < columns; col++) {
                if (toCheck[row][col] >= threshold) {
                    toReturn[row][col] = true;
                } //else {
//                    toReturn[row][col] = false;
//                }
            }
        }
        return toReturn;
    }


    public List<Point> getPoints(boolean[][] toCheck) {
        List<Point> points = new ArrayList<>();
        for (int row = 0; row < toCheck.length; row++) {
            boolean[] rows = toCheck[row];
            for (int col = 0; col < rows.length; col++) {
                boolean isAboveThreshold = rows[col];
                if (isAboveThreshold) {
                    points.add(new Point(row, col));
                }
            }
        }
        return points;
    }

    private void printArray(Integer[][] toPrint) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toPrint.length; i++) {
            Integer[] integers = toPrint[i];
            for (int c = 0; c < integers.length; c++) {
                Integer integer = integers[c];
                sb.append(integer).append(", ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private void printArray(Boolean[][] toPrint) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < toPrint.length; i++) {
            Boolean[] booleans = toPrint[i];
            for (int c = 0; c < booleans.length; c++) {
                Boolean b = booleans[c];
                sb.append(b).append(", ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        Integer[][] table = {
                {5, 0, 25, 5, 145, 250},
                {0, 5, 95, 115, 165, 250},
                {15, 5, 175, 250, 185, 160},
                {5, 5, 145, 250, 245, 140},
                {115, 210, 60, 5, 230, 220},
                {55, 5, 175, 150, 170, 145}
        };

        MatrixChecker matrixChecker = new MatrixChecker();
        boolean[][] threshold = matrixChecker.aboveThreshold(table, 200);
        List<Point> points = matrixChecker.getPoints(threshold);

        System.out.println("***********");
        System.out.println("***********");
        System.out.println("***********");

        for (Point point : points) {
            System.out.print(point + ", ");
        }
        System.out.println();

        AdjacentPoint adjacentPoint = new AdjacentPoint();
        Set<Set<Point>> adjacents = adjacentPoint.getAdjacents(points);
        for (Set<Point> pointSet : adjacents) {
            StringBuilder sb = new StringBuilder();
            for (Point point : pointSet) {
                sb.append(point);
            }
            System.out.println(sb.toString());
        }

    }
}
