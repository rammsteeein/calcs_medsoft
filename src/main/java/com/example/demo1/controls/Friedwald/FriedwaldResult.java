package com.example.demo1.controls.Friedwald;

public class FriedwaldResult {
    private final double ldl;
    private final String interpretation;
    private final String formatted;

    public FriedwaldResult(double ldl, String interpretation) {
        this.ldl = ldl;
        this.interpretation = interpretation;

        if (ldl >= 0)
            this.formatted = String.format("ХС ЛПНП: %.2f ммоль/л\nИнтерпретация: %s", ldl, interpretation);
        else
            this.formatted = interpretation;
    }

    public double getLdl() { return ldl; }
    public String getInterpretation() { return interpretation; }
    public String getFormatted() { return formatted; }

    @Override
    public String toString() { return formatted; }
}
