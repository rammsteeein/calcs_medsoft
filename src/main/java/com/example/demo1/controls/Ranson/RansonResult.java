package com.example.demo1.controls.Ranson;

public class RansonResult {
    private final int value; private final String interpretation;
    public RansonResult(int value, String interpretation) {
        this.value = value; this.interpretation = interpretation;
    }
    public int getValue() {
        return value;
    }
    @Override public String toString() {
        return String.format("Баллы: %d%nИнтерпретация: %s", value, interpretation);
    }
}
