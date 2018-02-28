package com.wysong.ionicchallenge.business;

import com.wysong.ionicchallenge.Point;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This manager will be responsible for the Center of Mass calculations.
 */
@Service
public interface CalculationManager {

    /**
     * Returns the center of mass {@link Point} for the provided set of points.
     *
     * @param points the collection of points to use for calculating the center of mass.
     * @param weightValues the 2d array of weights that is used to find the weights of the provided points.
     * @return a {@link Point} that is determined to be the center of mass according to the average weight
     * of the provided collection of points.
     */
    Point getCenterOfMass(Set<Point> points, int[][] weightValues);
}
