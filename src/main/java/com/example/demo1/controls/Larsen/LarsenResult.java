package com.example.demo1.controls.Larsen;

public class LarsenResult {
    private final int totalScore;
    private final String interpretation;
    private final String calculation;

    public LarsenResult(int totalScore, String interpretation, String calculation) {
        this.totalScore = totalScore;
        this.interpretation = interpretation;
        this.calculation = calculation;
    }

    public int getTotalScore() { return totalScore; }
    public String getInterpretation() { return interpretation; }
    public String getCalculation() { return calculation; }

    @Override
    public String toString() {
        return calculation;
    }
}
