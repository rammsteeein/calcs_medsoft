package com.example.demo1.controls.rGENEVA;

public class rGENEVAResult {
    private final int score;
    private final String threeLevel;
    private final String twoLevel;

    public rGENEVAResult(int score, String threeLevel, String twoLevel) {
        this.score = score;
        this.threeLevel = threeLevel;
        this.twoLevel = twoLevel;
    }

    public int getScore() { return score; }
    public String getThreeLevel() { return threeLevel; }
    public String getTwoLevel() { return twoLevel; }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d\nТрёхуровневая шкала: %s\nДвухуровневая шкала: %s",
                score, threeLevel, twoLevel);
    }
}
