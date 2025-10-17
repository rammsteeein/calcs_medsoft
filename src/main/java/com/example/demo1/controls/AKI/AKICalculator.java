package com.example.demo1.controls.AKI;

public class AKICalculator {

    /**
     * Алгоритм оценки острого повреждения почек (Acute Kidney Injury, AKI).
     *
     * Классификация по степеням:
     *
     * Степень 1:
     * - Увеличение креатинина в 1.5–1.9 раза от исходного уровня
     *   или повышение на ≥0.3 мг/дл;
     * - Диурез <0.5 мл/кг/ч в течение 6–12 часов.
     *
     * Степень 2:
     * - Увеличение креатинина в 2–2.9 раза от исходного уровня;
     * - Диурез <0.5 мл/кг/ч более 12 часов.
     *
     * Степень 3:
     * - Увеличение креатинина в ≥3 раза от исходного уровня
     *   или креатинин ≥4 мг/дл (≥353.6 мкмоль/л);
     * - Анурия более 12 часов или необходимость диализа.
     */

    public static AKIResult calc(double baselineCreatinine, double currentCreatinine,
                                 double urineVolumeMl, double weightKg, double hours) {
        double creatRatio = currentCreatinine / baselineCreatinine;
        double creatIncrease = currentCreatinine - baselineCreatinine;
        double urineRate = urineVolumeMl / (weightKg * hours);

        int stageByCreat = 0;
        int stageByUrine = 0;

        if (creatRatio >= 3 || currentCreatinine >= 4.0) {
            stageByCreat = 3;
        } else if (creatRatio >= 2) {
            stageByCreat = 2;
        } else if (creatRatio >= 1.5 || creatIncrease >= 0.3) {
            stageByCreat = 1;
        }


        if (urineRate < 0.3 && hours >= 24) {
            stageByUrine = 3;
        } else if (urineRate == 0 && hours >= 12) {
            stageByUrine = 3;
        } else if (urineRate < 0.5 && hours > 12) {
            stageByUrine = 2;
        } else if (urineRate < 0.5 && hours >= 6) {
            stageByUrine = 1;
        }

        int finalStage = Math.max(stageByCreat, stageByUrine);

        return new AKIResult(finalStage, baselineCreatinine, currentCreatinine, urineRate, hours);
    }
}