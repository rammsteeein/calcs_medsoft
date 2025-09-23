package com.example.demo1.controls.REACH;

public class REACHCalculator {

    public static REACHResult calc(
            int age,
            boolean peripheralAtherosclerosis,
            boolean heartFailure,
            boolean diabetes,
            boolean hypercholesterolemia,
            boolean hypertension,
            int smokingStatus, // 0 - никогда, 1 - раньше, 2 - продолжается
            int antiplatelet,  // 0 - нет, 1 - аспирин, 2 - другие, 4 - комбинация
            boolean oralAnticoagulant
    ) {
        int score = 0;

        // Возраст
        if (age >= 45 && age <= 54) score += 0;
        else if (age >= 55 && age <= 64) score += 2;
        else if (age >= 65 && age <= 74) score += 4;
        else if (age >= 75) score += 6;

        // Другие факторы
        if (peripheralAtherosclerosis) score += 2;
        if (heartFailure) score += 2;
        if (diabetes) score += 1;
        if (hypercholesterolemia) score += 1;
        if (hypertension) score += 1;
        score += smokingStatus;
        score += antiplatelet;
        if (oralAnticoagulant) score += 2;

        // Определяем степень риска по таблице
        String riskCategory = "";
        double riskPercent = 0.0;

        if (score >= 0 && score <= 6) { riskCategory = "низкий"; riskPercent = 0.46; }
        else if (score >= 7 && score <= 8) { riskCategory = "умеренный"; riskPercent = 0.95; }
        else if (score >= 9 && score <= 10) { riskCategory = "высокий"; riskPercent = 1.25; }
        else if (score >= 11) { riskCategory = "очень высокий"; riskPercent = 2.76; }

        String resultStr = String.format("Сумма баллов: %d\nСтепень риска: %s\nВероятность кровотечения: %.2f%%",
                score, riskCategory, riskPercent);

        return new REACHResult(resultStr);
    }
}