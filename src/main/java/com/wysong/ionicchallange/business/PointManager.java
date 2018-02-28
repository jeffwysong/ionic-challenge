package com.wysong.ionicchallange.business;

import com.wysong.ionicchallange.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PointManager {

    /**
     * Returns a list of {@link Point}s that correspond to all the values of true in the provided 2d toCheck array.
     *
     * @param toCheck the 2d boolean array to create the list of points from.
     * @return a list of {@link Point}s that correspond to all the values of true in the provided 2d toCheck array.
     */
    List<Point> getPoints(boolean[][] toCheck);

    /**
     * Returns a Set of {@link Point} sets that contain all the adjacent points from the provided points collection.
     * For example:
     * <pre>
     *     {(1,1), (2,3), (3,4)}
     * </pre>
     * will return:
     * <pre>
     *     { {(1,1)},
     *       {(2,3), (3,4)}
     *     }
     * </pre>
     *
     * @param points the collection of points to determine what points are adjacent.
     * @return a set of set of points that represent the different collections of adjacent points.
     */
    Set<Set<Point>> getAdjacents(List<Point> points);
}
