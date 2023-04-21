package com.squad.be.dto;

import java.util.Arrays;

public class GameStats {

    private QueueStat[] queueStats;

    public GameStats() {
    }

    public GameStats(QueueStat[] queueStats) {
        this.queueStats = queueStats;
    }

    public QueueStat[] getQueueStats() {
        return queueStats;
    }

    public void setQueueStats(QueueStat[] queueStats) {
        this.queueStats = queueStats;
    }

    @Override
    public String toString() {
        return "GameStats{" +
                "queueStats=" + Arrays.toString(queueStats) +
                '}';
    }
}
