package com.example.demo1.controls.Cognitive;

public class CognitiveResult {
    private final int value;
    private final String interpretation;

    public CognitiveResult(int value, String interpretation) {
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
        return String.format("Баллы: %d из 30%nИнтерпретация: %s", value, interpretation);
    }
}

