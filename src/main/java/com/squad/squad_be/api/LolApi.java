package com.squad.squad_be.api;

import com.squad.squad_be.dto.Account;
import com.squad.squad_be.dto.GameStats;
import com.squad.squad_be.dto.QueueStat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LolApi {

    private static final String API_BASE_URL = "https://euw1.api.riotgames.com/lol";

    @Value("${LOL_API_KEY}")
    private String lolApiKey;

    private final RestTemplate restTemplate;

    public LolApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Account getAccount(String summonerName) {
        String apiUrl = API_BASE_URL + "/summoner/v4/summoners/by-name/{summonerName}?api_key={key}";
        ResponseEntity<Account> response = restTemplate.getForEntity(apiUrl, Account.class, summonerName, lolApiKey);
        return response.getBody();
    }

    public GameStats getStats(String encryptedSummonerId) {
        String apiUrl = API_BASE_URL + "/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key={key}";
        ResponseEntity<QueueStat[]> response = restTemplate.getForEntity(apiUrl, QueueStat[].class, encryptedSummonerId, lolApiKey);
        GameStats gameStats = new GameStats();
        gameStats.setQueueStats(response.getBody());
        return gameStats;
    }

}

