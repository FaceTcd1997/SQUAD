package com.squad.squad_be.component;

import com.squad.squad_be.dto.User;
import com.squad.squad_be.dto.ml.Teams;
import com.squad.squad_be.dto.ml.UserEntry;
import com.squad.squad_be.service.DatasetService;
import com.squad.squad_be.service.LolService;
import com.squad.squad_be.service.MLService;
import com.squad.squad_be.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FeaturesComponent {

    @Autowired private SessionService sessionService;
    @Autowired private DatasetService datasetService;
    @Autowired private LolService lolService;
    @Autowired private MLService mlService;

    public User registerUser(User user) throws Exception {
        //Check user already present//TODO

        //Retrieve account information from summoner name
        user.setAccount(lolService.retrieveLolAccount(user.getAccount().getName()));
        //Retrieve game stats from account id
        user.setStats(lolService.retrieveLolStats(user.getAccount().getId()));
        //Map user to dataset entry
        UserEntry userEntry = datasetService.mapUser(user);
        user.setWinrate(userEntry.getWIN_RATE());
        //Retrieve matching players and store in the dataset
        Teams matches = mlService.retrieveMatches(userEntry);
        //Map dataset entries to users
        //TODO
        //Update user matches
        user.setMatches(matches);
        //Register user
        datasetService.addUser(user);

        return  user;
    }

    public User retrieveNewMatches(User user) throws Exception {
        //Check user already present//TODO
        //Retrieve account information from summoner name
        user.setAccount(lolService.retrieveLolAccount(user.getAccount().getName()));
        //Retrieve game stats from account id
        user.setStats(lolService.retrieveLolStats(user.getAccount().getId()));
        //Map user to dataset entry
        UserEntry userEntry = datasetService.mapUser(user);
        //Retrieve matching players and store in the dataset
        Teams matches = mlService.retrieveNewMatches(userEntry);
        //Update user matches
        user.setMatches(matches);

        return  user;
    }

    public String updateUser(User user) throws Exception {
        //Check user already present//TODO
        //Retrieve account information from summoner name
        user.setAccount(lolService.retrieveLolAccount(user.getAccount().getName()));
        //Retrieve game stats from account id
        user.setStats(lolService.retrieveLolStats(user.getAccount().getId()));
        //Map user to dataset entry
        UserEntry userEntry = datasetService.mapUser(user);
        //Retrieve matching players and store in the dataset

        return mlService.updateEntry(userEntry);
    }

    public String removeUser(User user) throws Exception {
        //Check user already present//TODO
        //Retrieve account information from summoner name
        user.setAccount(lolService.retrieveLolAccount(user.getAccount().getName()));
        //Retrieve game stats from account id
        user.setStats(lolService.retrieveLolStats(user.getAccount().getId()));
        //Map user to dataset entry
        UserEntry userEntry = datasetService.mapUser(user);
        //Retrieve matching players and store in the dataset

        return mlService.removeEntry(userEntry);
    }

    public User loginUser(User user) throws Exception{
        return user;
    }

    public void createDataset() throws IOException {
        datasetService.createDataset();
    }

}
