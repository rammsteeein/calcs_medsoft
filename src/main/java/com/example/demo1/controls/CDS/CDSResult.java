package com.example.demo1.controls.CDS;

public class CDSResult {
    private final int value;
    private final String interpretation;

    public CDSResult(int value, String interpretation) {
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