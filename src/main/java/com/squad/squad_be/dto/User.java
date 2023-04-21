package com.squad.squad_be.dto;

import com.squad.squad_be.dto.enums.Role;
import com.squad.squad_be.dto.ml.Teams;

public class User {
    private String email;
    private String name;
    private Integer schedule_start;
    private Integer schedule_end;
    private Traits traits;
    private Account account;
    private GameStats stats;

    private Double winrate;
    private Role role;
    private Teams matches;

    public User() {
    }

    public User(String email, String name, Integer schedule_start, Integer schedule_end, Traits traits, Account account, GameStats stats, Double winrate, Role role, Teams group) {
        this.email = email;
        this.name = name;
        this.schedule_start = schedule_start;
        this.schedule_end = schedule_end;
        this.traits = traits;
        this.account = account;
        this.stats = stats;
        this.winrate = winrate;
        this.role = role;
        this.matches = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSchedule_start() {
        return schedule_start;
    }

    public void setSchedule_start(Integer schedule_start) {
        this.schedule_start = schedule_start;
    }

    public Integer getSchedule_end() {
        return schedule_end;
    }

    public void setSchedule_end(Integer schedule_end) {
        this.schedule_end = schedule_end;
    }

    public Traits getTraits() {
        return traits;
    }

    public void setTraits(Traits traits) {
        this.traits = traits;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public GameStats getStats() {
        return stats;
    }

    public void setStats(GameStats stats) {
        this.stats = stats;
    }

    public Double getWinrate() {
        return winrate;
    }

    public void setWinrate(Double winrate) {
        this.winrate = winrate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Teams getMatches() {
        return matches;
    }

    public void setMatches(Teams matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", schedule_start=" + schedule_start +
                ", schedule_end=" + schedule_end +
                ", traits=" + traits +
                ", account=" + account +
                ", stats=" + stats +
                ", winrate=" + winrate +
                ", role=" + role +
                ", matches=" + matches +
                '}';
    }
}
