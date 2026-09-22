package com.example.demo1.controls.CTSI;

public class CTSIResult {

    private final int value;
    private final String interpretation;

    public CTSIResult(int value, String interpretation) {
        this.value = value;
        this.interpretation = interpretation;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format(
                "КТ-индекс тяжести острого панкреатита (CTSI): %d из 10%n" +
                        "Интерпретация: %s",
                value,
                interpretation
        );
    }
}