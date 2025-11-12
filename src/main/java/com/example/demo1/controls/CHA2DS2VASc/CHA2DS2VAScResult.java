package com.example.demo1.controls.CHA2DS2VASc;

public class CHA2DS2VAScResult {
    private final int score;
    private final String interpretation;

    public CHA2DS2VAScResult(int score, String interpretation) {
        this.score = score;
        this.interpretation = interpretation;
    }

    public int getScore() {
        return score;
    }

    public String getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\n%s", score, interpretation);
    }
}
