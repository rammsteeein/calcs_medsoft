package com.example.demo1.common.resources;

public class TraumaInjury {

    private final String name;
    private final int score;

    public TraumaInjury(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name;
    }
}