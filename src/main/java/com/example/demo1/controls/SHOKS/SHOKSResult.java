package com.example.demo1.controls.SHOKS;

public class SHOKSResult {
    private final int score;
    private final String interpritation;

    public SHOKSResult(String interpretation, int score) {
        this.score = score;
        this.interpritation = interpretation;
    }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\nИнтерпритация: %s",
                score, interpritation);
    }
}
