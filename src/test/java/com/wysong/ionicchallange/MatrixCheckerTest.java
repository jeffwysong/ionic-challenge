package com.wysong.ionicchallange;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MatrixCheckerTest {
    private final MatrixChecker matrixChecker = new MatrixChecker();

    @Test
    public void aboveThreshold() {
        Integer[][] table = {
                {5, 0, 25, 5, 145, 250},
                {0, 5, 95, 115, 165, 250},
                {15, 5, 175, 250, 185, 160},
                {5, 5, 145, 250, 245, 140},
                {115, 210, 60, 5, 230, 220},
                {55, 5, 175, 150, 170, 145}
        };

        List<Point> points = new ArrayList<>(8);
        points.add(new Point(0, 5));
        points.add(new Point(1, 5));
        points.add(new Point(2, 3));
        points.add(new Point(3, 3));
        points.add(new Point(3, 4));
        points.add(new Point(4, 4));
        points.add(new Point(4, 5));
        points.add(new Point(4, 1));

        boolean[][] booleans = matrixChecker.aboveThreshold(table, 200);
        for (Point point : points) {
            assertTrue(booleans[point.x][point.y]);
        }
        assertFalse(booleans[0][0]);
    }

    @Test
    public void testAboveThreshold() {
        Integer[][] table = {
                {5, 0, 25, 5, 145, 250},
                {0, 5, 95, 115, 165, 250},
                {-15, 5, 175, 250, 185, 160},
                {5, 5, 145, 250, -255, 140},
                {115, 210, 60, 5, 230, 220},
                {55, 5, 175, 150, 170, 145}
        };

        List<Point> points = new ArrayList<>(8);
        points.add(new Point(0, 5));
        points.add(new Point(1, 5));
        points.add(new Point(2, 3));
        points.add(new Point(3, 3));
        points.add(new Point(4, 4));
        points.add(new Point(4, 5));
        points.add(new Point(4, 1));

        boolean[][] booleans = matrixChecker.aboveThreshold(table, 200);
        for (Point point : points) {
            assertTrue(booleans[point.x][point.y]);
        }
        assertFalse(booleans[3][4]);
    }

    @Test
    public void testAboveThreshold3() {
        Integer[][] table = {
                {5, 0, 25, 5, 145, 250},
                {0, 5, 95, 115, 165, 250},
                {-15, 5, 175, 250, 185, 160},
                {5, 5, 145, 250, -255, 140},
                {115, 210, 60, 5, 230, 220},
                {55, 5, 175, 150, 170, 145}
        };

        final int threshold = 4;

        List<Point> points = new ArrayList<>();
        for (int x = 0; x < table.length; x++) {
            Integer[] xs = table[x];
            for (int y = 0; y < xs.length; y++) {
                Integer ys = xs[y];
                if (ys >= threshold) {
                    points.add(new Point(x, y));
                }
            }
        }

        boolean[][] booleans = matrixChecker.aboveThreshold(table, threshold);
        List<Point> pointList = matrixChecker.getPoints(booleans);

        AdjacentPoint adjacentPoint = new AdjacentPoint();
        Set<Set<Point>> adjacents = adjacentPoint.getAdjacents(pointList);
        for (Set<Point> pointSet : adjacents) {
            StringBuilder sb = new StringBuilder();
            for (Point point : pointSet) {
                sb.append(point);
            }
            System.out.println(sb.toString());
        }
        assertEquals(1, adjacents.size());


        for (Point point : points) {
            assertTrue(booleans[point.x][point.y]);
        }
        assertFalse(booleans[2][0]);
    }

    @Test
    public void testAboveThreshold4() {
        Integer[][] table = {
                {5, 0, 25},
                {0, 5, 95},
                {-15, 5, 175},
                {5, 5, 145}
        };

        final int threshold = 4;

        List<Point> points = new ArrayList<>();
        for (int x = 0; x < table.length; x++) {
            Integer[] xs = table[x];
            for (int y = 0; y < xs.length; y++) {
                Integer ys = xs[y];
                if (ys >= threshold) {
                    points.add(new Point(x, y));
                }
            }
        }

        boolean[][] booleans = matrixChecker.aboveThreshold(table, threshold);
        List<Point> pointList = matrixChecker.getPoints(booleans);

        AdjacentPoint adjacentPoint = new AdjacentPoint();
        Set<Set<Point>> adjacents = adjacentPoint.getAdjacents(pointList);
        for (Set<Point> pointSet : adjacents) {
            StringBuilder sb = new StringBuilder();
            for (Point point : pointSet) {
                sb.append(point);
            }
            System.out.println(sb.toString());
        }
        assertEquals(1, adjacents.size());


        for (Point point : points) {
            assertTrue(booleans[point.x][point.y]);
        }
        assertFalse(booleans[2][0]);
    }
}