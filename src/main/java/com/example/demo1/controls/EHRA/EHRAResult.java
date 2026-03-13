package com.example.demo1.controls.EHRA;

public class EHRAResult {

    private final String value;
    private final String interpretation;

    public EHRAResult(String value, String interpretation) {
        this.value = value;
        this.interpretation = interpretation;
    }

    public String getValue() {
        return value;
    }

    public String getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return String.format("Класс: %s%nИнтерпретация: %s", value, interpretation);
    }
}