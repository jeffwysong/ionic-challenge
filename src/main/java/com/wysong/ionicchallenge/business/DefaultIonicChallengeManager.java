package com.wysong.ionicchallenge.business;

import com.wysong.ionicchallenge.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DefaultIonicChallengeManager implements IonicChallengeManager {

    private final IntegerMatrixValidator integerMatrixValidator;

    private final PointManager pointManager;

    private final MatrixTransformer matrixTransformer;

    private final CalculationManager calculationManager;

    @Autowired
    public DefaultIonicChallengeManager(IntegerMatrixValidator integerMatrixValidator, PointManager pointManager, MatrixTransformer matrixTransformer, CalculationManager calculationManager) {
        this.integerMatrixValidator = integerMatrixValidator;
        this.pointManager = pointManager;
        this.matrixTransformer = matrixTransformer;
        this.calculationManager = calculationManager;
    }

    @Override
    public Set<Set<Point>> getAdjacents(Integer[][] inputIntegers, int threshold) {
        int[][] ints = integerMatrixValidator.verify(inputIntegers);
        boolean[][] aboveThreshold = matrixTransformer.isAboveThreshold(ints, threshold);
        List<Point> points = pointManager.getPoints(aboveThreshold);
        return pointManager.getAdjacents(points);
    }

    @Override
    public Set<Point> getCenterOfMass(Integer[][] inputIntegers, int threshold) {
        int[][] ints = integerMatrixValidator.verify(inputIntegers);
        Set<Set<Point>> adjacents = getAdjacents(inputIntegers, threshold);
        Set<Point> centerOfMassPoints = new HashSet<>(adjacents.size());
        for (Set<Point> adjacentPoints : adjacents) {
            Point centerOfMass = calculationManager.getCenterOfMass(adjacentPoints, ints);
            centerOfMassPoints.add(centerOfMass);
        }
        return centerOfMassPoints;
    }
}
