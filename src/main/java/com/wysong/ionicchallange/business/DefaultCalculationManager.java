package com.wysong.ionicchallange.business;

import com.wysong.ionicchallange.Point;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DefaultCalculationManager implements CalculationManager {
    @Override
    public Point getCenterOfMass(Set<Point> points, int[][] weightValues) {
        Map<Integer, Point> weightMap = new HashMap<>();
        int sum = 0;
        for (Point point : points) {
            sum += weightValues[point.x][point.y];
            weightMap.put(weightValues[point.x][point.y], point);
        }
        int average = sum / points.size();

        System.out.println(sum);
        System.out.println(average);

        if (weightMap.get(average) != null) {
            return weightMap.get(average);
        }

        return findClosestToAveragePoint(points, weightValues, average);
    }

    private Point findClosestToAveragePoint(Set<Point> points, int[][] weightValues, int average) {
        Point minAveragePoint = null;
        int minDiff = Integer.MAX_VALUE;
        for (Point point : points) {
            int abs = Math.abs(weightValues[point.x][point.y] - average);
            if (abs < minDiff) {
                minDiff = abs;
                minAveragePoint = point;
            }
        }
        return minAveragePoint;
    }



}
