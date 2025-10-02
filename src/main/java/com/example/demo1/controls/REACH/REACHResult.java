package com.example.demo1.controls.REACH;

public class REACHResult {
    private final int score;
    private final String riskCategory;
    private final double riskPercent;
    private final String interpretation;

    public REACHResult(int score, String riskCategory, double riskPercent, String interpretation) {
        this.score = score;
        this.riskCategory = riskCategory;
        this.riskPercent = riskPercent;
        this.interpretation = interpretation;
    }

    public int getScore() { return score; }
    public String getRiskCategory() { return riskCategory; }
    public double getRiskPercent() { return riskPercent; }
    public String getInterpretation() { return interpretation; }

    @Override
    public String toString() { return interpretation; }
}
