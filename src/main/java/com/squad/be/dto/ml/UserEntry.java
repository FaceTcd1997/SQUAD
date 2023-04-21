package com.squad.be.dto.ml;

import com.squad.be.dto.enums.Rank;
import com.squad.be.dto.enums.Role;
import com.squad.be.dto.enums.Tier;

public class UserEntry {
    private String USERNAME;
    private String EMAIL;
    private String ACCOUNT_NAME;
    private String ACCOUNT_ID;
    private Integer ACCOUNT_LEVEL;
    private Integer WINS;
    private Integer LOSSES;
    private Double WIN_RATE;
    private Integer LEAGUE_POINTS;
    private Rank RANK;
    private Integer OVERALL_SCORE;
    private Role ROLE;
    private Integer SCHEDULE_TIME_START;
    private Integer SCHEDULE_TIME_END;
    private Double AGREEABLENESS;
    private Double CONSCIENTIOUSNESS;
    private Double EXTRAVERSION;
    private Double NEUROTICISM;
    private Double OPENNESS;
    private String REQUEST_REGION;
    private Tier TIER;

    public UserEntry() {
    }

    public UserEntry(String USERNAME, String EMAIL, String ACCOUNT_NAME, String ACCOUNT_ID, Integer ACCOUNT_LEVEL, Integer WINS, Integer LOSSES, Double WIN_RATE, Integer LEAGUE_POINTS, Rank RANK, Integer OVERALL_SCORE, Role ROLE, Integer SCHEDULE_TIME_START, Integer SCHEDULE_TIME_END, Double AGREEABLENESS, Double CONSCIENTIOUSNESS, Double EXTRAVERSION, Double NEUROTICISM, Double OPENNESS, String REQUEST_REGION, Tier TIER) {
        this.USERNAME = USERNAME;
        this.EMAIL = EMAIL;
        this.ACCOUNT_NAME = ACCOUNT_NAME;
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.ACCOUNT_LEVEL = ACCOUNT_LEVEL;
        this.WINS = WINS;
        this.LOSSES = LOSSES;
        this.WIN_RATE = WIN_RATE;
        this.LEAGUE_POINTS = LEAGUE_POINTS;
        this.RANK = RANK;
        this.OVERALL_SCORE = OVERALL_SCORE;
        this.ROLE = ROLE;
        this.SCHEDULE_TIME_START = SCHEDULE_TIME_START;
        this.SCHEDULE_TIME_END = SCHEDULE_TIME_END;
        this.AGREEABLENESS = AGREEABLENESS;
        this.CONSCIENTIOUSNESS = CONSCIENTIOUSNESS;
        this.EXTRAVERSION = EXTRAVERSION;
        this.NEUROTICISM = NEUROTICISM;
        this.OPENNESS = OPENNESS;
        this.REQUEST_REGION = REQUEST_REGION;
        this.TIER = TIER;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public Integer getACCOUNT_LEVEL() {
        return ACCOUNT_LEVEL;
    }

    public void setACCOUNT_LEVEL(Integer ACCOUNT_LEVEL) {
        this.ACCOUNT_LEVEL = ACCOUNT_LEVEL;
    }

    public Integer getWINS() {
        return WINS;
    }

    public void setWINS(Integer WINS) {
        this.WINS = WINS;
    }

    public Integer getLOSSES() {
        return LOSSES;
    }

    public void setLOSSES(Integer LOSSES) {
        this.LOSSES = LOSSES;
    }

    public Double getWIN_RATE() {
        return WIN_RATE;
    }

    public void setWIN_RATE(Double WIN_RATE) {
        this.WIN_RATE = WIN_RATE;
    }

    public Integer getLEAGUE_POINTS() {
        return LEAGUE_POINTS;
    }

    public void setLEAGUE_POINTS(Integer LEAGUE_POINTS) {
        this.LEAGUE_POINTS = LEAGUE_POINTS;
    }

    public Rank getRANK() {
        return RANK;
    }

    public void setRANK(Rank RANK) {
        this.RANK = RANK;
    }

    public Integer getOVERALL_SCORE() {
        return OVERALL_SCORE;
    }

    public void setOVERALL_SCORE(Integer OVERALL_SCORE) {
        this.OVERALL_SCORE = OVERALL_SCORE;
    }

    public Role getROLE() {
        return ROLE;
    }

    public void setROLE(Role ROLE) {
        this.ROLE = ROLE;
    }

    public Integer getSCHEDULE_TIME_START() {
        return SCHEDULE_TIME_START;
    }

    public void setSCHEDULE_TIME_START(Integer SCHEDULE_TIME_START) {
        this.SCHEDULE_TIME_START = SCHEDULE_TIME_START;
    }

    public Integer getSCHEDULE_TIME_END() {
        return SCHEDULE_TIME_END;
    }

    public void setSCHEDULE_TIME_END(Integer SCHEDULE_TIME_END) {
        this.SCHEDULE_TIME_END = SCHEDULE_TIME_END;
    }

    public Double getAGREEABLENESS() {
        return AGREEABLENESS;
    }

    public void setAGREEABLENESS(Double AGREEABLENESS) {
        this.AGREEABLENESS = AGREEABLENESS;
    }

    public Double getCONSCIENTIOUSNESS() {
        return CONSCIENTIOUSNESS;
    }

    public void setCONSCIENTIOUSNESS(Double CONSCIENTIOUSNESS) {
        this.CONSCIENTIOUSNESS = CONSCIENTIOUSNESS;
    }

    public Double getEXTRAVERSION() {
        return EXTRAVERSION;
    }

    public void setEXTRAVERSION(Double EXTRAVERSION) {
        this.EXTRAVERSION = EXTRAVERSION;
    }

    public Double getNEUROTICISM() {
        return NEUROTICISM;
    }

    public void setNEUROTICISM(Double NEUROTICISM) {
        this.NEUROTICISM = NEUROTICISM;
    }

    public Double getOPENNESS() {
        return OPENNESS;
    }

    public void setOPENNESS(Double OPENNESS) {
        this.OPENNESS = OPENNESS;
    }

    public String getREQUEST_REGION() {
        return REQUEST_REGION;
    }

    public void setREQUEST_REGION(String REQUEST_REGION) {
        this.REQUEST_REGION = REQUEST_REGION;
    }

    public Tier getTIER() {
        return TIER;
    }

    public void setTIER(Tier TIER) {
        this.TIER = TIER;
    }


    @Override
    public String toString() {
        return "UserEntry{" +
                "USERNAME='" + USERNAME + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", ACCOUNT_NAME='" + ACCOUNT_NAME + '\'' +
                ", ACCOUNT_ID='" + ACCOUNT_ID + '\'' +
                ", ACCOUNT_LEVEL=" + ACCOUNT_LEVEL +
                ", WINS=" + WINS +
                ", LOSSES=" + LOSSES +
                ", WIN_RATE=" + WIN_RATE +
                ", LEAGUE_POINTS=" + LEAGUE_POINTS +
                ", RANK=" + RANK +
                ", OVERALL_SCORE=" + OVERALL_SCORE +
                ", ROLE=" + ROLE +
                ", SCHEDULE_TIME_START=" + SCHEDULE_TIME_START +
                ", SCHEDULE_TIME_END=" + SCHEDULE_TIME_END +
                ", AGREEABLENESS=" + AGREEABLENESS +
                ", CONSCIENTIOUSNESS=" + CONSCIENTIOUSNESS +
                ", EXTRAVERSION=" + EXTRAVERSION +
                ", NEUROTICISM=" + NEUROTICISM +
                ", OPENNESS=" + OPENNESS +
                ", REQUEST_REGION='" + REQUEST_REGION + '\'' +
                ", TIER=" + TIER +
                '}';
    }
}
