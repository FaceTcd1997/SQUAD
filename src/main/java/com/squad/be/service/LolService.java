package com.squad.be.service;

import com.squad.be.api.LolApi;
import com.squad.be.dto.Account;
import com.squad.be.dto.GameStats;
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
