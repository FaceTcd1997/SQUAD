package com.squad.squad_be;

import com.squad.squad_be.component.FeaturesComponent;
import com.squad.squad_be.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SquadController {

    private static final Logger logger = LoggerFactory.getLogger(SquadController.class);

    @Autowired
    private FeaturesComponent features;

    @GetMapping("/")
    public String sayHello() throws Exception {
        features.createDataset();
        return "Hello World!";
    }

    @PostMapping("/register-user")
    public ResponseEntity<User> registerUser(@RequestBody User user) throws Exception {
        logger.info("SQUAD - Received registration request for: " + user.getName());
        User response = features.registerUser(user);
        logger.info("SQUAD - Sending response");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/find-new-matches")
    public ResponseEntity<User> retrieveNewMatches(@RequestBody User user) throws Exception {
        logger.info("SQUAD - Received registration request for: " + user.getName());
        User response = features.retrieveNewMatches(user);
        logger.info("SQUAD - Sending response");
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestBody User user) throws Exception {
        logger.info("SQUAD - Received registration request for: " + user.getName());
        String response = features.updateUser(user);
        logger.info("SQUAD - Sending response");
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/remove-user")
    public ResponseEntity<String> removeUser(@RequestBody User user) throws Exception {
        logger.info("SQUAD - Received registration request for: " + user.getName());
        String response = features.removeUser(user);
        logger.info("SQUAD - Sending response");
        return ResponseEntity.ok().body(response);
    }

}
