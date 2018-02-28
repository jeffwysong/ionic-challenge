package com.wysong.ionicchallenge.business;

import com.wysong.ionicchallenge.Point;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IonicChallengeManager {

    /**
     * Returns a collection of adjacent {@link Point}s as a collection of {@link Point}s.
     * Adjacent points are defined as {@link Point}s that are equal to or above the
     * provided {@code threshold} and are within one element in the array in any direction.
     *
     * @param inputIntegers the 2d array of weights.
     * @param threshold the lower limit of weight to consider.
     * @return a collection of a collection of {@link Point}s that are above or equal
     * to the provided {@code threshold} and are within one element of each other in
     * provided 2d array.
     */
    Set<Set<Point>> getAdjacents(Integer[][] inputIntegers, int threshold);

    /**
     * Returns a collection of {@link Point}s that are determined to be the center of mass
     * of adjacent {@link Point}s.
     * <p/>
     * Adjacent points are defined as {@link Point}s that are equal to or above the
     * provided {@code threshold} and are within one element in the 2d array in any direction.
     * <p/>
     * Center of Mass is defined as the {@link Point} in the list of adjacent {@link Point}s
     * that is closest to the average weight of adjacent points.  The weight for a {@link Point}
     * is determined by the provided {@code inputIntegers} 2d array.
     *
     * @param inputIntegers the 2d array of weights.
     * @param threshold the lower limit of weights to consider.
     * @return a collection of Center of Masses for each adjacent list of {@link Point}s.
     */
    Set<Point> getCenterOfMass(Integer[][] inputIntegers, int threshold);

}
