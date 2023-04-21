package com.squad.squad_be.service;

import com.squad.squad_be.api.LolApi;
import com.squad.squad_be.dto.Account;
import com.squad.squad_be.dto.GameStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LolService {

    @Autowired
    private LolApi lolApi;

    public Account retrieveLolAccount(String summonerName){
        return lolApi.getAccount(summonerName);
    }

    public GameStats retrieveLolStats(String encryptedSummonerId){
        return lolApi.getStats(encryptedSummonerId);
    }

}
