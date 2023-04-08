package com.squad.squad_be.component;

import com.squad.squad_be.dto.User;
import com.squad.squad_be.service.DatasetService;
import com.squad.squad_be.service.LolService;
import com.squad.squad_be.service.MLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeaturesComponent {

    @Autowired
    private DatasetService datasetService;

    @Autowired
    private LolService lolService;

    @Autowired
    private MLService mlService;

    public User registerUserFeature(User user) throws Exception {
        //Retrieve account information from summoner name
        user.setAccount(lolService.retrieveLolAccount(user.getAccount().getName()));
        //Retrieve game stats from account id
        user.setStats(lolService.retrieveLolStats(user.getAccount().getId()));
        //Retrieve matching players
        //TODO
        //Store the user in the dataset
        //Check user //TODO
        datasetService.addUser(user);
        return  user;
    }

    public void clusterize() throws Exception{
        mlService.clusterize();
    }

    public void fillDataset() throws Exception {
        datasetService.fillValues();
    }

}
