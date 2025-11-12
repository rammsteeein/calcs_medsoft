package com.example.demo1.controls.PERC;

public class PERCResult {
    private final int score;
    private final String interpretation;
    private final boolean passed;

    public PERCResult(int score, String interpretation, boolean passed) {
        this.score = score;
        this.interpretation = interpretation;
        this.passed = passed;
    }

    public int getScore() { return score; }
    public String getInterpretation() { return interpretation; }
    public boolean isPassed() { return passed; }

    @Override
    public String toString() {
        return String.format("Нарушено критериев: %d\n%s", score, interpretation);
    }
}
