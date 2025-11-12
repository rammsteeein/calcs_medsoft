package com.example.demo1.controls.PERC;

public class PERCCalculator {

    public static PERCResult calc(
            int age,
            int heartRate,
            int spo2,
            boolean unilateralLegEdema,
            boolean hemoptysis,
            boolean recentTraumaOrSurgery,
            boolean previousVte,
            boolean hormoneUse
    ) {
        int score = 0;

        // Проверяем только если значение задано (>0)
        if (age > 0 && age >= 50) score++;
        if (heartRate > 0 && heartRate >= 100) score++;
        if (spo2 > 0 && spo2 <= 95) score++;

        if (unilateralLegEdema) score++;
        if (hemoptysis) score++;
        if (recentTraumaOrSurgery) score++;
        if (previousVte) score++;
        if (hormoneUse) score++;

        String interpretation;
        boolean passed = score == 0;

        if (passed) {
            interpretation = "Все критерии выполнены — риск ТЭЛА низкий (<2%)";
        } else {
            interpretation = "Обнаружено " + score + " нарушений — PERC не выполнено (не исключает ТЭЛА)";
        }

        return new PERCResult(score, interpretation, passed);
    }
}
