package com.wysong.ionicchallange.rest;

import com.wysong.ionicchallange.Point;
import com.wysong.ionicchallange.business.IonicChallengeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * REST controller that provides endpoints to get adjacent points or the center of mass.
 */
//TODO: Create better exception handling.
@RestController()
public class IonicRestController {

    private final IonicChallengeManager ionicChallengeManager;

    @Autowired
    public IonicRestController(IonicChallengeManager ionicChallengeManager) {
        this.ionicChallengeManager = ionicChallengeManager;
    }

    @RequestMapping(value = "/adjacents", method = RequestMethod.POST)
    ResponseEntity getAdjacents(@RequestBody InputBody inputBody) {
        Set<Set<Point>> adjacents = ionicChallengeManager.getAdjacents(inputBody.getInputMatrix(), inputBody.getThreshold());
        return new ResponseEntity(adjacents, HttpStatus.OK);
    }

    @RequestMapping(value = "/centermass", method = RequestMethod.POST)
    ResponseEntity getCenterOfMass(@RequestBody InputBody inputBody) {
        Set<Point> centerOfMassPoints = ionicChallengeManager.getCenterOfMass(inputBody.getInputMatrix(), inputBody.getThreshold());
        return new ResponseEntity(centerOfMassPoints, HttpStatus.OK);
    }

    @RequestMapping(value = "/centermass/adjacents", method = RequestMethod.POST)
    ResponseEntity getCenterOfMassWithAdjacents(@RequestBody InputBody inputBody) {
        Set<Point> centerOfMassPoints = ionicChallengeManager.getCenterOfMass(inputBody.getInputMatrix(), inputBody.getThreshold());
        Set<Set<Point>> adjacents = ionicChallengeManager.getAdjacents(inputBody.getInputMatrix(), inputBody.getThreshold());
        List<CombinedReturnBody> combinedReturnBodies = new ArrayList<>(centerOfMassPoints.size());
        for (Point centerOfMassPoint : centerOfMassPoints) {
            for (Set<Point> adjacent : adjacents) {
                if (adjacent.contains(centerOfMassPoint)) {
                    CombinedReturnBody combinedReturnBody = new CombinedReturnBody(adjacent, centerOfMassPoint);
                    combinedReturnBodies.add(combinedReturnBody);
                    break;
                }
            }
        }
        return new ResponseEntity(combinedReturnBodies, HttpStatus.OK);
    }
}
