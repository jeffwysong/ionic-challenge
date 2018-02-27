package com.wysong.ionicchallange.business;

import com.wysong.ionicchallange.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface PointManager {

    List<Point> getPoints(boolean[][] toCheck);

    Set<Set<Point>> getAdjacents(List<Point> points);
}
