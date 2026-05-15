package com.example.demo1.controls.UCT;

public class UCTResult {

    private final int value;
    private final String interpretation;

    public UCTResult(int value, String interpretation) {
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
                "Баллы: %d из 16%nИнтерпретация: %s",
                value,
                interpretation
        );
    }
}