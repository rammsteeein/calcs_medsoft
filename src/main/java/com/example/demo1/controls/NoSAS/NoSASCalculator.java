package com.example.demo1.controls.NoSAS;

import com.example.demo1.common.enums.Gender;

public class NoSASCalculator {

    public static NoSASResult calc(
            double neckCircumference,
            double bmi,
            boolean hasSnoring,
            int age,
            Gender gender
    ) {
        int score = 0;

        if (neckCircumference >= 40) score += 4;

        if (bmi >= 30) score += 5;
        else if (bmi >= 25) score += 3;

        if (hasSnoring) score += 2;

        if (age > 55) score += 4;

        if (gender == Gender.MALE) score += 2;

        String risk = score >= 8 ? "Высокий риск нарушения дыхания во сне" : "Низкий риск";

        return new NoSASResult(String.format("Сумма баллов: %d\n%s", score, risk));
    }
}
