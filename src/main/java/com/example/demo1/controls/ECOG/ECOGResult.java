package com.example.demo1.controls.ECOG;

public class ECOGResult {
    private final int value;
    private final String interpretation;

    public ECOGResult(int value, String interpretation) {
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
        return String.format("Шкала ECOG: %d балл(ов)%nИнтерпретация: %s", value, interpretation);
    }
}


