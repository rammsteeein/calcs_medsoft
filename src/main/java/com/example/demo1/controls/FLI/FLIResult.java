package com.example.demo1.controls.FLI;

public class FLIResult {
    private final double fli;
    private final String interpretation;
    private final String formatted;

    public FLIResult(double fli, String interpretation) {
        this.fli = fli;
        this.interpretation = interpretation;
        if (fli >= 0) {
            this.formatted = String.format("FLI: %.2f\nИнтерпретация: %s", fli, interpretation);
        } else {
            this.formatted = interpretation;
        }
    }

    public double getFli() {
        return fli;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public String getFormatted() {
        return formatted;
    }

    @Override
    public String toString() {
        return formatted;
    }
}
