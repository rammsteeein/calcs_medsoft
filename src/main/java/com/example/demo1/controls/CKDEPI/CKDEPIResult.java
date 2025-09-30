package com.example.demo1.controls.CKDEPI;

public class CKDEPIResult {
    private final double value;
    private final String formatted;
    private final String interpretation;

    public CKDEPIResult(double value, String formatted, String interpretation) {
        this.value = value;
        this.formatted = formatted;
        this.interpretation = interpretation;
    }

    public double getValue() {
        return value;
    }

    public String getFormatted() {
        return formatted;
    }

    public String getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return formatted + " (" + interpretation + ")";
    }
}
