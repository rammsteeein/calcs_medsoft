package com.example.demo1.controls.COPD;

public class COPDResult {
    private final int value;
    private final String interpretation;

    public COPDResult(int value, String interpretation) {
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
        return String.format("Суммарный балл: %d%nИнтерпретация: %s", value, interpretation);
    }
}