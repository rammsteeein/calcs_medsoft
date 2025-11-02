package com.example.demo1.controls.HASBLED;

public class HASBLEDResult {
    private final int value;
    private final String interpretation;

    public HASBLEDResult(int value, String interpretation) {
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
        return String.format("Баллы: %d%nИнтерпретация: %s", value, interpretation);
    }
}
