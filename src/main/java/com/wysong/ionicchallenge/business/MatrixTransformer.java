package com.wysong.ionicchallenge.business;

import org.springframework.stereotype.Service;

@Service
public interface MatrixTransformer {

    /**
     * Returns a truth 2d array that if the element in the provided toCheck array is
     * above or equal to the provided threshold, then that element value will be true.
     * Otherwise the value will be false.
     * <p/>
     * For example:
     * <pre>
     *     [[5, 25],[3, 95]]
     * </pre>
     * with a threshold of 5 will return a 2d array of:
     * <pre>
     *     [[1,1],[0,1]]
     * </pre>
     * <p/>
     *
     * @param toCheck the 2d array of ints to transform into a 2d boolean array.
     * @param threshold the lower bound to check against.
     * @return a 2d array that is the same size as the provided toCheck array, with values
     * that are true if the threshold was equal to or less than the element in the toCheck array.
     * If the provided threshold is above the element value, then a false will be assigned.
     */
    boolean[][] isAboveThreshold(int[][] toCheck, int threshold);

}
