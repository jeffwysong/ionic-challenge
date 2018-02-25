package com.wysong.ionicchallange;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdjacentPointTest {

    private final AdjacentPoint adjacentPoint = new AdjacentPoint();

    @Before
    public void setUp() {

    }

    @Test
    public void isAdj() {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        assertTrue(adjacentPoint.isAdj(p1, p2));
        Point p3 = new Point(3, 3);
        assertFalse(adjacentPoint.isAdj(p1, p3));
        assertTrue(adjacentPoint.isAdj(p2, p3));
    }
}