package com.squad.be.dto.ml;

public class UserEntry2 {
    private String ACCOUNT_ID;
    private int ACCOUNT_LEVEL;
    private String ACCOUNT_NAME;
    private double AGREEABLENESS;
    private double CONSCIENTIOUSNESS;
    private String EMAIL;
    private double EXTRAVERSION;
    private int LEAGUE_POINTS;
    private int LOSSES;
    private double NEUROTICISM;
    private double OPENNESS;
    private int OVERALL_SCORE;
    private String RANK;
    private String REQUEST_REGION;
    private String ROLE;
    private int SCHEDULE_TIME_END;
    private int SCHEDULE_TIME_START;
    private String TIER;
    private String USERNAME;
    private int WINS;
    private double WIN_RATE;

    public UserEntry2() {
    }

    public UserEntry2(String ACCOUNT_ID, int ACCOUNT_LEVEL, String ACCOUNT_NAME, double AGREEABLENESS, double CONSCIENTIOUSNESS, String EMAIL, double EXTRAVERSION, int LEAGUE_POINTS, int LOSSES, double NEUROTICISM, double OPENNESS, int OVERALL_SCORE, String RANK, String REQUEST_REGION, String ROLE, int SCHEDULE_TIME_END, int SCHEDULE_TIME_START, String TIER, String USERNAME, int WINS, double WIN_RATE) {
        this.ACCOUNT_ID = ACCOUNT_ID;
        this.ACCOUNT_LEVEL = ACCOUNT_LEVEL;
        this.ACCOUNT_NAME = ACCOUNT_NAME;
        this.AGREEABLENESS = AGREEABLENESS;
        this.CONSCIENTIOUSNESS = CONSCIENTIOUSNESS;
        this.EMAIL = EMAIL;
        this.EXTRAVERSION = EXTRAVERSION;
        this.LEAGUE_POINTS = LEAGUE_POINTS;
        this.LOSSES = LOSSES;
        this.NEUROTICISM = NEUROTICISM;
        this.OPENNESS = OPENNESS;
        this.OVERALL_SCORE = OVERALL_SCORE;
        this.RANK = RANK;
        this.REQUEST_REGION = REQUEST_REGION;
        this.ROLE = ROLE;
        this.SCHEDULE_TIME_END = SCHEDULE_TIME_END;
        this.SCHEDULE_TIME_START = SCHEDULE_TIME_START;
        this.TIER = TIER;
        this.USERNAME = USERNAME;
        this.WINS = WINS;
        this.WIN_RATE = WIN_RATE;
    }

    public String getACCOUNT_ID() {
        return ACCOUNT_ID;
    }

    public void setACCOUNT_ID(String ACCOUNT_ID) {
        this.ACCOUNT_ID = ACCOUNT_ID;
    }

    public int getACCOUNT_LEVEL() {
        return ACCOUNT_LEVEL;
    }

    public void setACCOUNT_LEVEL(int ACCOUNT_LEVEL) {
        this.ACCOUNT_LEVEL = ACCOUNT_LEVEL;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public double getAGREEABLENESS() {
        return AGREEABLENESS;
    }

    public void setAGREEABLENESS(double AGREEABLENESS) {
        this.AGREEABLENESS = AGREEABLENESS;
    }

    public double getCONSCIENTIOUSNESS() {
        return CONSCIENTIOUSNESS;
    }

    public void setCONSCIENTIOUSNESS(double CONSCIENTIOUSNESS) {
        this.CONSCIENTIOUSNESS = CONSCIENTIOUSNESS;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public double getEXTRAVERSION() {
        return EXTRAVERSION;
    }

    public void setEXTRAVERSION(double EXTRAVERSION) {
        this.EXTRAVERSION = EXTRAVERSION;
    }

    public int getLEAGUE_POINTS() {
        return LEAGUE_POINTS;
    }

    public void setLEAGUE_POINTS(int LEAGUE_POINTS) {
        this.LEAGUE_POINTS = LEAGUE_POINTS;
    }

    public int getLOSSES() {
        return LOSSES;
    }

    public void setLOSSES(int LOSSES) {
        this.LOSSES = LOSSES;
    }

    public double getNEUROTICISM() {
        return NEUROTICISM;
    }

    public void setNEUROTICISM(double NEUROTICISM) {
        this.NEUROTICISM = NEUROTICISM;
    }

    public double getOPENNESS() {
        return OPENNESS;
    }

    public void setOPENNESS(double OPENNESS) {
        this.OPENNESS = OPENNESS;
    }

    public int getOVERALL_SCORE() {
        return OVERALL_SCORE;
    }

    public void setOVERALL_SCORE(int OVERALL_SCORE) {
        this.OVERALL_SCORE = OVERALL_SCORE;
    }

    public String getRANK() {
        return RANK;
    }

    public void setRANK(String RANK) {
        this.RANK = RANK;
    }

    public String getREQUEST_REGION() {
        return REQUEST_REGION;
    }

    public void setREQUEST_REGION(String REQUEST_REGION) {
        this.REQUEST_REGION = REQUEST_REGION;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }

    public int getSCHEDULE_TIME_END() {
        return SCHEDULE_TIME_END;
    }

    public void setSCHEDULE_TIME_END(int SCHEDULE_TIME_END) {
        this.SCHEDULE_TIME_END = SCHEDULE_TIME_END;
    }

    public int getSCHEDULE_TIME_START() {
        return SCHEDULE_TIME_START;
    }

    public void setSCHEDULE_TIME_START(int SCHEDULE_TIME_START) {
        this.SCHEDULE_TIME_START = SCHEDULE_TIME_START;
    }

    public String getTIER() {
        return TIER;
    }

    public void setTIER(String TIER) {
        this.TIER = TIER;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public int getWINS() {
        return WINS;
    }

    public void setWINS(int WINS) {
        this.WINS = WINS;
    }

    public double getWIN_RATE() {
        return WIN_RATE;
    }

    public void setWIN_RATE(double WIN_RATE) {
        this.WIN_RATE = WIN_RATE;
    }

    @Override
    public String toString() {
        return "UserEntry2{" +
                "ACCOUNT_ID='" + ACCOUNT_ID + '\'' +
                ", ACCOUNT_LEVEL=" + ACCOUNT_LEVEL +
                ", ACCOUNT_NAME='" + ACCOUNT_NAME + '\'' +
                ", AGREEABLENESS=" + AGREEABLENESS +
                ", CONSCIENTIOUSNESS=" + CONSCIENTIOUSNESS +
                ", EMAIL='" + EMAIL + '\'' +
                ", EXTRAVERSION=" + EXTRAVERSION +
                ", LEAGUE_POINTS=" + LEAGUE_POINTS +
                ", LOSSES=" + LOSSES +
                ", NEUROTICISM=" + NEUROTICISM +
                ", OPENNESS=" + OPENNESS +
                ", OVERALL_SCORE=" + OVERALL_SCORE +
                ", RANK='" + RANK + '\'' +
                ", REQUEST_REGION='" + REQUEST_REGION + '\'' +
                ", ROLE='" + ROLE + '\'' +
                ", SCHEDULE_TIME_END=" + SCHEDULE_TIME_END +
                ", SCHEDULE_TIME_START=" + SCHEDULE_TIME_START +
                ", TIER='" + TIER + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", WINS=" + WINS +
                ", WIN_RATE=" + WIN_RATE +
                '}';
    }
}
