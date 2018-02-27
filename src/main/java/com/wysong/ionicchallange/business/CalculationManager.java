package com.wysong.ionicchallange.business;

import com.wysong.ionicchallange.Point;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * This manager will be responsible for the Center of Mass calculations.
 */
@Service
public interface CalculationManager {

    Point getCenterOfMass(Set<Point> points, int[][] weightValues);
}
