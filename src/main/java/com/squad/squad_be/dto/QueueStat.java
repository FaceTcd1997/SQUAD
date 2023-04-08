package com.squad.squad_be.dto;

import com.squad.squad_be.dto.enums.Queue;
import com.squad.squad_be.dto.enums.Rank;
import com.squad.squad_be.dto.enums.Tier;

public class QueueStat {
    private String leagueId;
    private Queue queueType;
    private Tier tier;
    private Rank rank;
    private String summonerId;
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;

    public QueueStat() {
    }

    public QueueStat(String leagueId, Queue queueType, Tier tier, Rank rank, String summonerId, String summonerName, int leaguePoints, int wins, int losses, boolean veteran, boolean inactive, boolean freshBlood, boolean hotStreak) {
        this.leagueId = leagueId;
        this.queueType = queueType;
        this.tier = tier;
        this.rank = rank;
        this.summonerId = summonerId;
        this.summonerName = summonerName;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
        this.veteran = veteran;
        this.inactive = inactive;
        this.freshBlood = freshBlood;
        this.hotStreak = hotStreak;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public Queue getQueueType() {
        return queueType;
    }

    public void setQueueType(Queue queueType) {
        this.queueType = queueType;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public boolean isFreshBlood() {
        return freshBlood;
    }

    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public boolean isHotStreak() {
        return hotStreak;
    }

    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    @Override
    public String toString() {
        return "QueueStat{" +
                "leagueId='" + leagueId + '\'' +
                ", queueType='" + queueType + '\'' +
                ", tier='" + tier + '\'' +
                ", rank='" + rank + '\'' +
                ", summonerId='" + summonerId + '\'' +
                ", summonerName='" + summonerName + '\'' +
                ", leaguePoints=" + leaguePoints +
                ", wins=" + wins +
                ", losses=" + losses +
                ", veteran=" + veteran +
                ", inactive=" + inactive +
                ", freshBlood=" + freshBlood +
                ", hotStreak=" + hotStreak +
                '}';
    }
}
