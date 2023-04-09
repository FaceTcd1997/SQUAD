package com.squad.squad_be.component;

import com.squad.squad_be.dto.User;
import com.squad.squad_be.service.DatasetService;
import com.squad.squad_be.service.LolService;
import com.squad.squad_be.service.MLService;
import com.squad.squad_be.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FeaturesComponent {

    @Autowired private SessionService sessionService;
    @Autowired private DatasetService datasetService;
    @Autowired private LolService lolService;
    @Autowired private MLService mlService;

    public User registerUser(User user) throws Exception {
        //Retrieve account information from summoner name
        user.setAccount(lolService.retrieveLolAccount(user.getAccount().getName()));
        //Retrieve game stats from account id
        user.setStats(lolService.retrieveLolStats(user.getAccount().getId()));
        //Store the user in the dataset
        //Check user //TODO
        datasetService.addUser(user);
        //Clusterize players
        mlService.clusterize();
        //Retrieve matching players
        datasetService.retrieveMatches(user);
        sessionService.getMap().put(user.getEmail(), user);

        return  user;
    }

    public User loginUser(User user) throws Exception{
        //Check user
        //
        return user;
    }
    public User findNewMatches(User user) throws Exception {
        return user;
    }

    public User findMatches(User user) throws Exception {
        return datasetService.retrieveMatches(user);
    }

    public void clusterize() throws Exception{
        mlService.clusterize();
    }

    public void fillDataset() throws Exception {
        datasetService.fillValues();
    }

}
