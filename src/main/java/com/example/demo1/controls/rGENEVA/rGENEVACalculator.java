package com.example.demo1.controls.rGENEVA;

public class rGENEVACalculator {

    public static rGENEVAResult calc(
            boolean prevPEorDVT,
            int heartRate,
            boolean surgeryOrFracture,
            boolean hemoptysis,
            boolean activeCancer,
            boolean legPain,
            boolean painAndSwelling,
            int age
    ) {
        int score = 0;

        if (prevPEorDVT) score += 3;

        if (heartRate >= 75 && heartRate <= 94) score += 3;
        else if (heartRate >= 95) score += 5;

        if (surgeryOrFracture) score += 2;
        if (hemoptysis) score += 2;
        if (activeCancer) score += 3;
        if (legPain) score += 3;
        if (painAndSwelling) score += 4;
        if (age > 65) score += 1;

        // Трехуровневая шкала
        String threeLevel;
        if (score >= 0 && score <= 1) threeLevel = "низкая";
        else if (score >= 2 && score <= 6) threeLevel = "средняя";
        else threeLevel = "высокая";

        // Двухуровневая шкала
        String twoLevel;
        if (score >= 0 && score <= 4) twoLevel = "ТЭЛА маловероятна";
        else twoLevel = "ТЭЛА вероятна";

        String resultStr = String.format("Сумма баллов: %d\nТрехуровневая шкала: %s\nДвухуровневая шкала: %s",
                score, threeLevel, twoLevel);

        return new rGENEVAResult(resultStr);
    }
}