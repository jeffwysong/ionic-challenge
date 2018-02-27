package com.wysong.ionicchallange.business;

import org.springframework.stereotype.Service;

@Service
public interface IntegerMatrixValidator {

    int[][] verify(Integer[][] toCheck);
}
