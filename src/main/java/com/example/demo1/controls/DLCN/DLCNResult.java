package com.example.demo1.controls.DLCN;

/**
 * Результат расчёта по шкале DLCN.
 */
public class DLCNResult {
    private final int score;
    private final String diagnosis;

    public DLCNResult(int score, String diagnosis) {
        this.score = score;
        this.diagnosis = diagnosis;
    }

    public int getScore() {
        return score;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    @Override
    public String toString() {
        return String.format("Сумма баллов: %d%n%s", score, diagnosis);
    }
}
