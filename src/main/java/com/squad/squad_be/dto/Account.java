package com.squad.squad_be.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {
    public String id;
    public String accountId;
    public String puuid;
    public String name;
    public String profileIconId;
    public String revisionDate;
    public String summonerLevel;

    public Account(String id, String accountId, String puuid, String name, String profileIconId, String revisionDate, String summonerLevel) {
        this.id = id;
        this.accountId = accountId;
        this.puuid = puuid;
        this.name = name;
        this.profileIconId = profileIconId;
        this.revisionDate = revisionDate;
        this.summonerLevel = summonerLevel;
    }

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileIconId() {
        return profileIconId;
    }

    public void setProfileIconId(String profileIconId) {
        this.profileIconId = profileIconId;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(String summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", puuid='" + puuid + '\'' +
                ", name='" + name + '\'' +
                ", profileIconId='" + profileIconId + '\'' +
                ", revisionDate='" + revisionDate + '\'' +
                ", summonerLevel='" + summonerLevel + '\'' +
                '}';
    }
}