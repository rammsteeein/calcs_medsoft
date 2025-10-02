package com.example.demo1.controls.Mehran2;

public class Mehran2Result {
    private final String interpretation;
    private final int score;
    private final double riskPercent;

    public Mehran2Result(String interpretation, int score, double riskPercent) {
        this.score = score;
        this.interpretation = interpretation;
        this.riskPercent = riskPercent;
    }

    public int getScore() { return score; }
    public String getInterpretation() { return interpretation; }
    public double getRiskPercent() { return riskPercent; }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\nИнтерпретация: %s\nОжидаемый риск КИН: %.1f%%",
                score, interpretation, riskPercent);
    }
}
