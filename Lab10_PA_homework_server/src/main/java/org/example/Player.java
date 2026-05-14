package org.example;

public class Player {
    private String name;
    private int score;
    private long totalResponseTime;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.totalResponseTime = 0;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
    public long getTotalResponseTime() { return totalResponseTime; }

    public void addScore(int points) { this.score += points; }
    public void addTime(long time) { this.totalResponseTime += time; }
}
