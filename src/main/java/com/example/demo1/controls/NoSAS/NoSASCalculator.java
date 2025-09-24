package com.example.demo1.controls.NoSAS;

import com.example.demo1.common.enums.Gender;

public class NoSASCalculator {

    /**
     * Шкала NoSAS (Nod-off, Snoring, Apnea, Sleepiness) —
     * используется для оценки риска нарушения дыхания во сне (например, обструктивного апноэ сна).
     *
     * Критерии и начисление баллов:
     * 1. Окружность шеи ≥ 40 см: 4 балла
     * 2. Индекс массы тела (ИМТ):
     *    - ≥30: 5 баллов
     *    - 25–29.9: 3 балла
     * 3. Наличие храпа: 2 балла
     * 4. Возраст >55 лет: 4 балла
     * 5. Мужской пол: 2 балла
     *
     * Интерпретация:
     * - ≥8 баллов: высокий риск нарушения дыхания во сне
     * - <8 баллов: низкий риск
     *
     * Примечания:
     * - Простая и быстрая шкала для первичного скрининга пациентов.
     * - Может использоваться врачами общей практики до направления пациента на полисомнографию.
     */

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
