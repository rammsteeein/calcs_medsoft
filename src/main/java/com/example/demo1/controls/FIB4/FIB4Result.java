package com.example.demo1.controls.FIB4;

public class FIB4Result {
    private final double value;
    private final String interpretation;

    public FIB4Result(double value, String interpretation) {
        this.value = value;
        this.interpretation = interpretation;
    }

    public double getValue() {
        return value;
    }

    public String getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return String.format("FIB-4 = %.3f (%s)", value, interpretation);
    }
}
