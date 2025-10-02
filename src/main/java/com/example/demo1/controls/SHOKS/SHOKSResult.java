package com.example.demo1.controls.SHOKS;

public class SHOKSResult {
    private final int score;
    private final String interpretation;

    public SHOKSResult(int score, String interpretation) {
        this.score = score;
        this.interpretation = interpretation;
    }

    public int getScore() { return score; }
    public String getInterpretation() { return interpretation; }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\nФункциональный класс: %s", score, interpretation);
    }
}
