package com.example.demo1.controls.AKI;

public class AKICalculator {

    public static AKIResult calc(double baselineCreatinine, double currentCreatinine,
                                 double urineVolumeMl, double weightKg, double hours) {
        double creatRatio = currentCreatinine / baselineCreatinine;
        double creatIncrease = currentCreatinine - baselineCreatinine;

        double urineRate = urineVolumeMl / (weightKg * hours);

        int stageByCreat = 0;
        int stageByUrine = 0;

        if (creatRatio >= 3 || currentCreatinine >= 4.0) stageByCreat = 3;
        else if (creatRatio >= 2) stageByCreat = 2;
        else if (creatRatio >= 1.5 || creatIncrease >= 0.3) stageByCreat = 1;

        if (urineRate < 0.3 && hours >= 24) stageByUrine = 3; // анурия >12 ч
        else if (urineRate < 0.5 && hours >= 12) stageByUrine = 2;
        else if (urineRate < 0.5 && hours >= 6) stageByUrine = 1;

        int finalStage = Math.max(stageByCreat, stageByUrine);

        String stageStr;
        switch (finalStage) {
            case 1: stageStr = "Степень 1"; break;
            case 2: stageStr = "Степень 2"; break;
            case 3: stageStr = "Степень 3"; break;
            default: stageStr = "Не выявлено"; break;
        }

        String resultStr = String.format("AKI: %s\nКреатинин: %.2f / %.2f мг/дл\nДиурез: %.2f мл/кг/ч за %.1f ч",
                stageStr, baselineCreatinine, currentCreatinine, urineRate, hours);

        return new AKIResult(resultStr);
    }
}