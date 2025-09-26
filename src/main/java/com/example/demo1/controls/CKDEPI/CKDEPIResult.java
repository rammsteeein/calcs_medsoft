package com.example.demo1.controls.CKDEPI;

public class CKDEPIResult {
    private final double value;
    private final String formatted;

    public CKDEPIResult(double value, String formatted) {
        this.value = value;
        this.formatted = formatted;
    }

    public double getValue() {
        return value;
    }

    public String getFormatted() {
        return formatted;
    }

    @Override
    public String toString() {
        return formatted;
    }
}
