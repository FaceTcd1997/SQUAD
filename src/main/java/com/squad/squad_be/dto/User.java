package com.squad.squad_be.dto;

import java.util.Arrays;

public class User {
    private String email;
    private String name;
    private String schedule;
    private Traits traits;
    private Account account;
    private GameStats stats;
    private Role role;

    private User[] matching;

    public User() {
    }

    public User(String email, String name, String schedule, Traits traits, Account account, GameStats stats, Role role, User[] matching) {
        this.email = email;
        this.name = name;
        this.schedule = schedule;
        this.traits = traits;
        this.account = account;
        this.stats = stats;
        this.role = role;
        this.matching = matching;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User[] getMatching() {
        return matching;
    }

    public void setMatching(User[] matching) {
        this.matching = matching;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", schedule='" + schedule + '\'' +
                ", traits=" + traits +
                ", account=" + account +
                ", stats=" + stats +
                ", role=" + role +
                ", matching=" + Arrays.toString(matching) +
                '}';
    }
}
