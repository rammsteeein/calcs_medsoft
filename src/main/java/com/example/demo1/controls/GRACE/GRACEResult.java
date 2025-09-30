package com.example.demo1.controls.GRACE;

import java.util.List;

public class GRACEResult {
    private final int totalPoints;
    private final String interpretation;
    private final List<GRACECalculator.Factor> factors;

    public GRACEResult(int totalPoints, String interpretation, List<GRACECalculator.Factor> factors) {
        this.totalPoints = totalPoints;
        this.interpretation = interpretation;
        this.factors = factors;
    }

    public int getTotalPoints() { return totalPoints; }
    public String getInterpretation() { return interpretation; }
    public List<GRACECalculator.Factor> getFactors() { return factors; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GRACECalculator.Factor f : factors) {
            sb.append(f.getName()).append(": ").append(f.getPoints()).append(" баллов\n");
        }
        sb.append("Суммарно: ").append(totalPoints).append(" баллов").append("\n");
        sb.append("Риск: ").append(interpretation);
        return sb.toString();
    }
}
