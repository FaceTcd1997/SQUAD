package com.squad.squad_be.component;

import com.squad.squad_be.dto.User;
import com.squad.squad_be.service.DatasetService;
import com.squad.squad_be.service.LolService;
import com.squad.squad_be.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FeaturesComponent {

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private LolService lolService;

    @Autowired
    private MLService mlService;

    public User registerUserFeature(User user) throws IOException {
        //Retrieve account information from summoner name
        user.setAccount(lolService.retrieveLolAccount(user.getAccount().getName()));
        //Retrieve game stats from account id
        user.setStats(lolService.retrieveLolStats(user.getAccount().getId()));
        //Retrieve matching players
        //TODO
        //Store the user in the dataset
        datasetService.write(user, "./src/dataset.csv");
        return  user;
    }
}
