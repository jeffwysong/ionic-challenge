package com.wysong.ionicchallange.business;

import com.wysong.ionicchallange.Point;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DefaultCalculationManagerTest {

    private final DefaultCalculationManager defaultCalculationManager = new DefaultCalculationManager();


    @Test
    public void getCenterOfMass() {
        int[][] ints = {{250,185,160},{250, 245, 140},{5, 230, 220}};
        Point point00 = new Point(0, 0);
        Point point10 = new Point(1, 0);
        Point point11 = new Point(1, 1);
        Point point21 = new Point(2, 1);
        Point point22 = new Point(2, 2);
        Set<Point> points = new HashSet<>(5);
        points.add(point00);
        points.add(point10);
        points.add(point11);
        points.add(point21);
        points.add(point22);

        Point centerOfMassPoint = defaultCalculationManager.getCenterOfMass(points, ints);
        System.out.println(centerOfMassPoint);
    }
}