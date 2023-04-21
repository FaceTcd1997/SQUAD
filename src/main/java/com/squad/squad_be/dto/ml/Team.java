package com.squad.squad_be.dto.ml;

import java.util.Arrays;

public class Team {
    private UserEntry[] entries;

    public Team() {
    }

    public Team(UserEntry[] entries) {
        this.entries = entries;
    }

    public UserEntry[] getEntries() {
        return entries;
    }

    public void setEntries(UserEntry[] entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "Team{" +
                "entries=" + Arrays.toString(entries) +
                '}';
    }
}
