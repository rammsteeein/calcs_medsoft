package com.example.demo1.controls.Wells;

public class WellsResult {
    private final double score;
    private final String threeLevel;
    private final String twoLevel;

    public WellsResult(double score, String threeLevel, String twoLevel) {
        this.score = score;
        this.threeLevel = threeLevel;
        this.twoLevel = twoLevel;
    }

    public double getScore() { return score; }
    public String getThreeLevel() { return threeLevel; }
    public String getTwoLevel() { return twoLevel; }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %.1f\nТрёхуровневая шкала: %s\nДвухуровневая шкала: %s",
                score, threeLevel, twoLevel);
    }
}
