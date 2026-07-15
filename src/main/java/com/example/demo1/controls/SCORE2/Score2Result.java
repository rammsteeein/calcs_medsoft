package com.example.demo1.controls.SCORE2;

public class Score2Result {

    private final int value;
    private final String interpretation;

    private final int age;
    private final int sysAd;
    private final int cholesterol;

    public Score2Result(int value,
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
                        "Оценка по шкале SCORE2: %d\n" +
                        "Интерпретация: %s",
                age, sysAd, cholesterol, value, interpretation
        );
    }
}
