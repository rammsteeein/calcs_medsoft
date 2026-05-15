package com.example.demo1.controls.UAS7;

public class UAS7Result {

    private final int score;
    private final String interpretation;

    public UAS7Result(int score, String interpretation) {
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
        return String.format(
                "Сумма баллов: %d из 42%nИнтерпретация: %s",
                score,
                interpretation
        );
    }
}