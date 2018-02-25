package com.wysong.ionicchallange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdjacentPoint {

    private int counter = 0;
    private int counter2 = 0;
    private int counter3 = 0;

    public Set<Set<Point>> getAdjacentPointsSet(Boolean[][] toCheck) {
        Set<Set<Point>> adjacentPointsSet = new HashSet<>();
        for (int row = 0; row < toCheck.length; row++) {
            Boolean[] booleans = toCheck[row];
            for (int col = 0; col < booleans.length; col++) {
                Boolean b = booleans[col];
                if (b) {
                    Set<Point> points = new HashSet<>();
                    points.add(new Point(row, col));
                    points.addAll(getAdjacts(toCheck, row, col));
                    adjacentPointsSet.add(points);
                }
            }
        }
        return adjacentPointsSet;
    }


    public Set<Point> getAdjacts(Boolean[][] toCheck, int row, int col) {
        int maxRows = toCheck.length;
        int maxColumns = toCheck[0].length;
        if (row > maxRows || col > maxColumns) {
            return null;
        }

        Set<Point> points = new HashSet<>();

        if (toCheck[row][col]) {
            toCheck[row][col] = false;
            points.add(new Point(row, col));

            helper(toCheck, row + 1, col, points);
            helper(toCheck, row, col + 1, points);
            helper(toCheck, row - 1, col + 1, points);
            helper(toCheck, row, col + 1, points);
            helper(toCheck, row + 1, col + 1, points);

        }


        return points;
    }

    private void helper(Boolean[][] toCheck, int row, int col, Set<Point> points) {
        if (helper2(toCheck, row, col, points)) {
            toCheck[row][col] = false;
        }

    }


    private boolean helper2(Boolean[][] toCheck, int row, int col, Set<Point> points) {
        int maxRows = toCheck.length;
        int maxColumns = toCheck[0].length;
//        System.out.println("helper2() called with: maxRows = [" + maxRows + "],  maxColumns = [" + maxColumns + "], row = [" + row + "], col = [" + col + "], points = [" + points + "]");
        if (row >= maxRows || col >= maxColumns) {
            return false;
        }
        if (toCheck[row][col]) {
            return points.add(new Point(row, col));
        }
        return false;
    }


    public Set<Set<Point>> getAdjacents(List<Point> points) {
        Set<Set<Point>> adjacentPointsSet = new HashSet<>();
        Set<Point> checked = new HashSet<>(points.size());
        for (Point point : points) {
            counter2++;
            if (!checked.contains(point)) {
                counter3++;
                Set<Point> adjPoints = new HashSet<>();
                adjHelper(points, point, checked, adjPoints);
                adjacentPointsSet.add(adjPoints);
            }
        }
        System.out.println(counter);
        System.out.println(counter2);
        System.out.println(counter3);
        return adjacentPointsSet;
    }

    protected Set<Point> adjHelper(List<Point> points, Point point, Set<Point> checked, Set<Point> results) {
        if (!checked.contains(point)) {
            checked.add(point);
            results.add(point);
            List<Point> newPointList = listWithPointsRemoved(points, checked);
            for (Point checkPoint : newPointList) {
                counter++;
                if (isAdj(point, checkPoint)) {
                    results.add(checkPoint);
                    return adjHelper(newPointList, checkPoint, checked, results);
                }
            }
        }
        System.out.print(counter3);
        System.out.print(" -> ");
        System.out.print(counter);
        System.out.println("");

        return results;
    }

    private List<Point> listWithPointRemoved(List<Point> points, Point point) {
        List<Point> toReturn = new ArrayList<>(points);
        toReturn.remove(point);
        return toReturn;
    }

    private List<Point> listWithPointsRemoved(List<Point> points, Set<Point> toRemove) {
        List<Point> toReturn = new ArrayList<>(points);
        toReturn.removeAll(toRemove);
        return toReturn;
    }

    protected boolean isAdj(Point p1, Point p2) {
        int diff = p1.x - p2.x;
        if (-1 <= diff && diff <= 1) {
            diff = p1.y - p2.y;
            if (-1 <= diff && diff <= 1) {
                return true;
            }
        }
        return false;
    }

}
