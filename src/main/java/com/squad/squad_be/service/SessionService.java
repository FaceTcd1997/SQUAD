package com.squad.squad_be.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squad.squad_be.dto.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class SessionService {
    private static final String MAP_FILE_PATH = "src/main/session/login_session.ser";
    private Map<String, User> map;
    private final ObjectMapper objectMapper;

    public SessionService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        map = restoreMap();
        if (map == null) {
            map = new HashMap<>();
        }
    }

    @PreDestroy
    public void destroy() {
        saveMap(map);
    }

    public void saveMap(Map<String, User> map) {
        try {
            File file = new File(MAP_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // Convert the User objects to JSON strings and store them in the map
            Map<String, String> mapWithJsonValues = new HashMap<>();
            for (Map.Entry<String, User> entry : map.entrySet()) {
                String json = objectMapper.writeValueAsString(entry.getValue());
                mapWithJsonValues.put(entry.getKey(), json);
            }

            out.writeObject(mapWithJsonValues);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, User> restoreMap() {
        Map<String, User> map = new HashMap<>();
        try {
            File file = new File(MAP_FILE_PATH);
            if (file.exists() && file.length() > 0) {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Map<String, String> mapWithJsonValues = (Map<String, String>) in.readObject();
                in.close();
                fileIn.close();

                // Convert the JSON strings back to User objects and store them in the map
                for (Map.Entry<String, String> entry : mapWithJsonValues.entrySet()) {
                    String json = entry.getValue();
                    User user = objectMapper.readValue(json, User.class);
                    map.put(entry.getKey(), user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public Map<String, User> getMap() {
        return map;
    }
}



