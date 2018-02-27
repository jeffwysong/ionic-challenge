package com.wysong.ionicchallange.business;

import com.wysong.ionicchallange.Point;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IonicChallengeManager {

    Set<Set<Point>> getAdjacents(Integer[][] inputIntegers, int threshold);

}
