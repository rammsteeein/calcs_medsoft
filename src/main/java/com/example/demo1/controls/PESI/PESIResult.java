package com.example.demo1.controls.PESI;

public class PESIResult {
    private final int score;
    private final String riskClass;

    public PESIResult(int score, String riskClass) {
        this.score = score;
        this.riskClass = riskClass;
    }

    public int getScore() { return score; }
    public String getRiskClass() { return riskClass; }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\n%s", score, riskClass);
    }
}
