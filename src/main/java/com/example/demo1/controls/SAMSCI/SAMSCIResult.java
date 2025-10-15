package com.example.demo1.controls.SAMSCI;

public class SAMSCIResult {
    private final int score;
    private final String interpretation;

    public SAMSCIResult(int score, String interpretation) {
        this.score = score;
        this.interpretation = interpretation;
    }

    public int getScore() {
        return score;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public String getBallWord(int score) {
        int lastDigit = score % 10;
        int lastTwoDigits = score % 100;
        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) return "баллов";
        switch (lastDigit) {
            case 1: return "балл";
            case 2:
            case 3:
            case 4: return "балла";
            default: return "баллов";
        }
    }

    @Override
    public String toString() {
        return "Сумма баллов: " + score + " " + getBallWord(score) + "\nЗаключение: " + interpretation;
    }
}
