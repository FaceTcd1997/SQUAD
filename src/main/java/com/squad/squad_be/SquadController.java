package com.squad.squad_be;

import com.squad.squad_be.dto.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SquadController {

    private static final Logger logger = LoggerFactory.getLogger(LolApiClient.class);
    private final LolApiClient lolApiClient;

    public SquadController(LolApiClient lolApiClient) {
        this.lolApiClient = lolApiClient;
    }

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    @GetMapping("/get-account/{username}")
    public String getAccount(@PathVariable String username) throws Exception {
        Account response = lolApiClient.getAccount(username);
        logger.info(response.toString());
        return response.toString();
    }
}
