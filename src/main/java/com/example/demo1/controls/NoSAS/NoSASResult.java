package com.example.demo1.controls.NoSAS;

public class NoSASResult {
    private final int score;
    private final String riskLevel;

    public NoSASResult(int score, String riskLevel) {
        this.score = score;
        this.riskLevel = riskLevel;
    }

    public int getScore() { return score; }
    public String getRiskLevel() { return riskLevel; }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\nРиск: %s", score, riskLevel);
    }
}
