package com.example.demo1.controls.PESI;

public class PESICalculator {

    public static PESIResult calc(
            int age,
            boolean isMale,
            boolean hasCancer,
            boolean hasCHF,
            boolean hasChronicLungDisease,
            int heartRate,
            int systolicBP,
            int respiratoryRate,
            double temperature,
            boolean alteredMentalStatus,
            double oxygenSaturation
    ) {
        int score = 0;

        // Баллы по показателям
        score += age; // возраст = баллы
        if (isMale) score += 10;
        if (hasCancer) score += 30;
        if (hasCHF) score += 10;
        if (hasChronicLungDisease) score += 10;
        if (heartRate >= 110) score += 20;
        if (systolicBP < 100) score += 30;
        if (respiratoryRate > 30) score += 20;
        if (temperature < 36.0) score += 20;
        if (alteredMentalStatus) score += 60;
        if (oxygenSaturation < 90.0) score += 20;

        // Стратификация риска
        String riskClass;
        if (score <= 65) riskClass = "Класс I — Очень низкий риск (0–1.6%)";
        else if (score <= 85) riskClass = "Класс II — Низкий риск (1.7–3.5%)";
        else if (score <= 105) riskClass = "Класс III — Умеренный риск (3.2–7.1%)";
        else if (score <= 125) riskClass = "Класс IV — Высокий риск (4.0–11.4%)";
        else riskClass = "Класс V — Очень высокий риск (10.0–24.5%)";

        String resultStr = String.format("Сумма баллов: %d\n%s", score, riskClass);

        return new PESIResult(resultStr);
    }
}
