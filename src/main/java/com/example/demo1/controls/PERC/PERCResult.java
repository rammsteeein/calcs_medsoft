package com.example.demo1.controls.PERC;

public class PERCResult {
    private final int score;
    private final String interpretation;

    public PERCResult(int score, String interpretation) {
        this.score = score;
        this.interpretation = interpretation;
    }

    public int getScore() { return score; }
    public String getInterpretation() { return interpretation; }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\n%s", score, interpretation);
    }
}