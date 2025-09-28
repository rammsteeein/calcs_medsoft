package com.example.demo1.controls.RCRI;

public class RCRIResult {
    private final int score;
    private final String interpretation;

    public RCRIResult(int score, String interpretation) {
        this.score = score;
        this.interpretation = interpretation;
    }

    public int getScore() { return score; }
    public String getInterpretation() { return interpretation; }

    @Override
    public String toString() {
        return String.format("Баллы: %d\n%s", score, interpretation);
    }
}
