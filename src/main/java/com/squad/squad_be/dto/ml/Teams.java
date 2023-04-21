package com.squad.squad_be.dto.ml;

import java.util.List;

public class Teams {
    private List<UserEntry> four_player_team;
    private List<UserEntry> three_player_team;
    private List<UserEntry> two_player_team;

    public Teams() {
    }

    public Teams(List<UserEntry> four_player_team, List<UserEntry> three_player_team, List<UserEntry> two_player_team) {
        this.four_player_team = four_player_team;
        this.three_player_team = three_player_team;
        this.two_player_team = two_player_team;
    }

    public List<UserEntry> getFour_player_team() {
        return four_player_team;
    }

    public void setFour_player_team(List<UserEntry> four_player_team) {
        this.four_player_team = four_player_team;
    }

    public List<UserEntry> getThree_player_team() {
        return three_player_team;
    }

    public void setThree_player_team(List<UserEntry> three_player_team) {
        this.three_player_team = three_player_team;
    }

    public List<UserEntry> getTwo_player_team() {
        return two_player_team;
    }

    public void setTwo_player_team(List<UserEntry> two_player_team) {
        this.two_player_team = two_player_team;
    }

    @Override
    public String toString() {
        return "Teams{" +
                "four_player_team=" + four_player_team +
                ", three_player_team=" + three_player_team +
                ", two_player_team=" + two_player_team +
                '}';
    }
}
