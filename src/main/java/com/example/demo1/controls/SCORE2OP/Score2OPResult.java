package com.example.demo1.controls.SCORE2OP;

public class Score2OPResult {

    private final int value;
    private final String interpretation;

    private final int age;
    private final int sysAd;
    private final int cholesterol;

    public Score2OPResult(int value,
                          String interpretation,
                          int age,
                          int sysAd,
                          int cholesterol) {
        this.value = value;
        this.interpretation = interpretation;
        this.age = age;
        this.sysAd = sysAd;
        this.cholesterol = cholesterol;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format(
                "Возраст: %d\n" +
                        "Систолическое АД: %d\n" +
                        "Холестерин: %d\n\n" +
                        "Оценка по шкале SCORE-2 OP: %d\n" +
                        "Интерпретация: %s",
                age, sysAd, cholesterol, value, interpretation
        );
    }
}