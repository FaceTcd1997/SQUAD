package com.squad.squad_be;

import com.squad.squad_be.component.FeaturesComponent;
import com.squad.squad_be.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SquadController {

    private static final Logger logger = LoggerFactory.getLogger(SquadController.class);

    @Autowired
    private FeaturesComponent features;

    @GetMapping("/")
    public String sayHello() throws Exception {
        //features.fillDataset();
        //features.clusterize();
        return "Hello World!";
    }

    @PostMapping("/register-user")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws Exception {
        logger.info("SQUAD - Received registration request for: " + user.toString());
        User response = features.registerUserFeature(user);
        logger.info("SQUAD - Sending response: " + response.toString());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/retrieve-new-matches")
    public String retrieveNewMatches(@PathVariable User user) throws Exception {
        return null;
    }

}
