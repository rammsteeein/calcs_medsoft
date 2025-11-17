package com.example.demo1.controls.Caprini;

public class CapriniResult {

    private final int score;
    private final String risk;

    public CapriniResult(int score, String risk) {
        this.score = score;
        this.risk = risk;
    }

    public int getScore() { return score; }
    public String getRisk() { return risk; }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\nОценочный риск ВТЭО: %s", score, risk);
    }
}
