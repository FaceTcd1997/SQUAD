package com.squad.squad_be;

import com.squad.squad_be.dto.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LolApiClient {

    private static final String API_BASE_URL = "https://euw1.api.riotgames.com/lol";

    @Value("${LOL_API_KEY}")
    private String lolApiKey;

    private final RestTemplate restTemplate;

    public LolApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Account getAccount(String username) {
        String apiUrl = API_BASE_URL + "/summoner/v4/summoners/by-name/{username}?api_key={key}";
        ResponseEntity<Account> response = restTemplate.getForEntity(apiUrl, Account.class, username, lolApiKey);
        return response.getBody();
    }
}

