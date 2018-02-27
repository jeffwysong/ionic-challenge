package com.wysong.ionicchallange.business;

import com.wysong.ionicchallange.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DefaultIonicChallengeManager implements IonicChallengeManager {

    private final IntegerMatrixValidator integerMatrixValidator;

    private final PointManager pointManager;

    private final MatrixTransformer matrixTransformer;

    @Autowired
    public DefaultIonicChallengeManager(IntegerMatrixValidator integerMatrixValidator, PointManager pointManager, MatrixTransformer matrixTransformer) {
        this.integerMatrixValidator = integerMatrixValidator;
        this.pointManager = pointManager;
        this.matrixTransformer = matrixTransformer;
    }

    @Override
    public Set<Set<Point>> getAdjacents(Integer[][] inputIntegers, int threshold) {
        int[][] ints = integerMatrixValidator.verify(inputIntegers);
        boolean[][] aboveThreshold = matrixTransformer.isAboveThreshold(ints, threshold);
        List<Point> points = pointManager.getPoints(aboveThreshold);
        return pointManager.getAdjacents(points);
    }
}
