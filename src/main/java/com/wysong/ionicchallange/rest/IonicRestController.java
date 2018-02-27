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

import java.util.Set;

@RestController("/")
public class IonicRestController {

    private final IonicChallengeManager ionicChallengeManager;

    @Autowired
    public IonicRestController(IonicChallengeManager ionicChallengeManager) {
        this.ionicChallengeManager = ionicChallengeManager;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity getAdjacents(@RequestBody InputBody inputBody) {
        Set<Set<Point>> adjacents = ionicChallengeManager.getAdjacents(inputBody.getInputMatrix(), inputBody.getThreshold());
        return new ResponseEntity(adjacents, HttpStatus.OK);
    }
}
