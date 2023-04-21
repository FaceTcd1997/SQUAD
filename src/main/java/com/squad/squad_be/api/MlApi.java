package com.squad.squad_be.api;

import com.google.gson.Gson;
import com.squad.squad_be.dto.ml.Teams;
import com.squad.squad_be.dto.ml.UserEntry;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MlApi {
    private static final String API_BASE_URL = "http://b7c6-34-125-250-180.ngrok.io";

    private final RestTemplate restTemplate;

    public MlApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Teams retrieveMatches(UserEntry entry) {
        String apiUrl = API_BASE_URL + "/suggest_teams";
        return sendFullRequest(entry, apiUrl);
    }

    public Teams retrieveNewMatches(UserEntry entry) {
        String apiUrl = API_BASE_URL + "/suggest_second_teams";
        return sendFullRequest(entry, apiUrl);
    }

    public String updateEntry(UserEntry entry) {
        String apiUrl = API_BASE_URL + "/update_player";
        return sendSimpleRequest(entry, apiUrl);
    }

    public String removeEntry(UserEntry entry) {
        String apiUrl = API_BASE_URL + "/remove_player";
        return sendSimpleRequest(entry, apiUrl);
    }

    private String sendSimpleRequest(UserEntry entry, String apiUrl) {
        Gson gson = new Gson();
        String Json = gson.toJson(entry);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(Json, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
        return response.getBody();
    }

    private Teams sendFullRequest(UserEntry entry, String apiUrl) {
        Gson gson = new Gson();
        String Json = gson.toJson(entry);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(Json, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);
        return gson.fromJson(response.getBody(), Teams.class);
    }

}
