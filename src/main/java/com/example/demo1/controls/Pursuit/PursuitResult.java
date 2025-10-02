package com.example.demo1.controls.Pursuit;

public class PursuitResult {
    private final int score;
    private final String riskCategory;
    private final double mortality;
    private final String interpretation;

    public PursuitResult(int score, String riskCategory, double mortality) {
        this.score = score;
        this.riskCategory = riskCategory;
        this.mortality = mortality;
        this.interpretation = String.format("Сумма баллов: %d, Категория: %s (%.1f%% смертность)",
                score, riskCategory, mortality);
    }

    public int getScore() { return score; }
    public String getRiskCategory() { return riskCategory; }
    public double getMortality() { return mortality; }
    public String getInterpretation() { return interpretation; }

    @Override
    public String toString() { return interpretation; }
}
