package com.example.demo1.controls.AKI;

public class AKICalculator {

    public static AKIResult calc(double baselineCreatinine, double currentCreatinine, double urineOutput, double weightKg, double hours) {
        String stage = "Не выявлено";

        double creatRatio = currentCreatinine / baselineCreatinine;

        // Степень по креатинину
        if (creatRatio >= 3 || currentCreatinine >= 4.0) {
            // Степень 3
            if (urineOutput < 0.5 && hours > 12) stage = "Степень 3: анурия/диализ";
            else stage = "Степень 3";
        } else if (creatRatio >= 2) {
            // Степень 2
            if (urineOutput < 0.5 && hours > 12) stage = "Степень 2";
        } else if (creatRatio >= 1.5 || (currentCreatinine - baselineCreatinine) >= 0.3) {
            // Степень 1
            if (urineOutput < 0.5 && hours >= 6) stage = "Степень 1";
        }

        String resultStr = String.format("AKI: %s\nКреатинин: %.2f / %.2f мг/дл\nДиурез: %.2f мл/кг/ч за %.1f ч",
                stage, baselineCreatinine, currentCreatinine, urineOutput, hours);

        return new AKIResult(resultStr);
    }
}
