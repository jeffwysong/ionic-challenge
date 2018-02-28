package com.wysong.ionicchallenge.business;

import org.springframework.stereotype.Service;

@Service
public interface IntegerMatrixValidator {

    /**
     * Validates and converts the provided {@link Integer} 2d array to a 2d int array.
     * Currently if a value of {@code null} is found in the provided toCheck array, the value will
     * be translated into Integer.MIN_VALUE
     *
     * @param toCheck the input 2d array to verify.
     * @return a 2d array of ints.
     */
    int[][] verify(Integer[][] toCheck);
}
