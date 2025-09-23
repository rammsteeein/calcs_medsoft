package com.example.demo1.controls.NoSAS;

public class NoSASCalculator {

    public static NoSASResult calc(
            double neckCircumference, // в см
            double bmi,
            boolean hasSnoring,
            int age,
            boolean isMale
    ) {
        int score = 0;

        // Окружность шеи
        if (neckCircumference >= 40) score += 4;

        // BMI
        if (bmi >= 30) score += 5;
        else if (bmi >= 25) score += 3;

        // Храп
        if (hasSnoring) score += 2;

        // Возраст
        if (age > 55) score += 4;

        // Пол
        if (isMale) score += 2;

        // Определяем риск
        String risk = score >= 8 ? "Высокий риск нарушения дыхания во сне" : "Низкий риск";

        String resultStr = String.format("Сумма баллов: %d\n%s", score, risk);
        return new NoSASResult(resultStr);
    }
}