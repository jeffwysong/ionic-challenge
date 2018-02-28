package com.wysong.ionicchallenge.business;

import com.wysong.ionicchallenge.Point;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class implements the default way I chose to calculate the {@link Point} that
 * will be deemed the center of mass point.
 */
@Component
public class DefaultCalculationManager implements CalculationManager {
    @Override
    public Point getCenterOfMass(Set<Point> points, int[][] weightValues) {
        //creating a weight map in case the average weight is the exact value
        //of a point in the provided set of points.  If multiple points have the
        //same weight, the last one wins.  :)
        Map<Integer, Point> weightMap = new HashMap<>();
        int sum = 0;
        for (Point point : points) {
            sum += weightValues[point.x][point.y];
            weightMap.put(weightValues[point.x][point.y], point);
        }
        int average = sum / points.size();

//        System.out.println(sum);
//        System.out.println(average);

        if (weightMap.get(average) != null) {
            return weightMap.get(average);
        }

        return findClosestToAveragePoint(points, weightValues, average);
    }

    /**
     * Helper method to find the point that has the value closest to provided average weight.
     *
     * @param points the set of points used to find the {@link Point} closest to the provided average weight.
     * @param weightValues the int array of weight values.
     * @param average the average weight for the provided points.
     * @return the {@link Point} from the provided points that is closest weight to the provided average.
     */
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
