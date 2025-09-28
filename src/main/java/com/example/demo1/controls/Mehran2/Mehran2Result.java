package com.example.demo1.controls.Mehran2;

public class Mehran2Result {
    private final String interpretation;
    private final int score;

    public Mehran2Result(String interpretation, int score) {
        this.score = score;
        this.interpretation = interpretation;
    }


    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\nИнтерпритация: %s",
                score, interpretation);
    }
}
