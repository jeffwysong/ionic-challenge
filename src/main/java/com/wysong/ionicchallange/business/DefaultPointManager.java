package com.wysong.ionicchallange.business;

import com.wysong.ionicchallange.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DefaultPointManager implements PointManager {

    @Override
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

    @Override
    public Set<Set<Point>> getAdjacents(List<Point> points) {
        Set<Set<Point>> adjacentPointsSet = new HashSet<>();
        Set<Point> checked = new HashSet<>(points.size());
        for (Point point : points) {
            if (!checked.contains(point)) {
                Set<Point> adjPoints = new HashSet<>();
                adjHelper(points, point, checked, adjPoints);
                adjacentPointsSet.add(adjPoints);
            }
        }
        return adjacentPointsSet;
    }

    private Set<Point> adjHelper(List<Point> points, Point point, Set<Point> checked, Set<Point> results) {
        if (!checked.contains(point)) {
            checked.add(point);
            results.add(point);
            List<Point> newPointList = listWithPointsRemoved(points, checked);
            for (Point checkPoint : newPointList) {
                if (isAdj(point, checkPoint)) {
                    results.add(checkPoint);
                    adjHelper(newPointList, checkPoint, checked, results);
                }
            }
        }
        return results;
    }

    private List<Point> listWithPointsRemoved(List<Point> points, Set<Point> toRemove) {
        List<Point> toReturn = new ArrayList<>(points);
        toReturn.removeAll(toRemove);
        return toReturn;
    }

    private boolean isAdj(Point p1, Point p2) {
        int diff = p1.x - p2.x;
        if (-1 <= diff && diff <= 1) {
            diff = p1.y - p2.y;
            return -1 <= diff && diff <= 1;
        }
        return false;
    }
}
