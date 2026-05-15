package com.example.demo1.controls.MoCA;

public class MoCAResult {

    private final int value;
    private final String interpretation;

    public MoCAResult(int value, String interpretation) {
        this.value = value;
        this.interpretation = interpretation;
    }

    public int getValue() {
        return value;
    }

    public String getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return String.format(
                "Баллы: %d из 30%nИнтерпретация: %s",
                value,
                interpretation
        );
    }
}