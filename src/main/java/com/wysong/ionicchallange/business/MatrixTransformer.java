package com.wysong.ionicchallange.business;

import org.springframework.stereotype.Service;

@Service
public interface MatrixTransformer {

    boolean[][] isAboveThreshold(int[][] toCheck, int threshold);

}
