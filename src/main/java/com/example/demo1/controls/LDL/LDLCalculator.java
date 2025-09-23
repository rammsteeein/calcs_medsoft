package com.example.demo1.controls.LDL;

public class LDLCalculator {
    public static LDLResult calc(double nonHDL, double tg) {
        double ldl = nonHDL - (tg / 3.0 - 0.14);
        String result = String.format("%.2f", ldl);
        return new LDLResult(result);
    }
}