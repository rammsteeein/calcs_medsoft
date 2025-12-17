package com.example.demo1.controls.Karnovsky;

public class KarnovskyResult {
    private final int value;
    private final String interpretation;

    public KarnovskyResult(int value, String interpretation) {
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
        return String.format("Индекс Карновского: %d%%%nИнтерпретация: %s", value, interpretation);
    }
}



