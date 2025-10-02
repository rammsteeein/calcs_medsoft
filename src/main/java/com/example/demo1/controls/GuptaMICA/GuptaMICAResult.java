package com.example.demo1.controls.GuptaMICA;

public class GuptaMICAResult {

    private final String result;
    private final double riskPercent;

    public GuptaMICAResult(String result, double riskPercent) {
        this.result = result;
        this.riskPercent = riskPercent;
    }

    public String getResult() {
        return result;
    }

    public double getRiskPercent() {
        return riskPercent;
    }

    @Override
    public String toString() {
        return result;
    }
}
