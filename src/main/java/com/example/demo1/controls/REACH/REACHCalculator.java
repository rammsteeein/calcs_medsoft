package com.example.demo1.controls.REACH;

public class REACHCalculator {

    /**
     * Шкала REACH для оценки риска кровотечения у пациентов с атеросклерозом
     * без фибрилляции предсердий, ОКС и стентирования.
     *
     * Факторы риска и баллы:
     * 1. Возраст (лет):
     *    - 45–54: 0
     *    - 55–64: 2
     *    - 65–74: 4
     *    - 75+: 6
     *
     * 2. Периферический атеросклероз:
     *    - Нет: 0
     *    - Есть: 2
     *
     * 3. Сердечная недостаточность:
     *    - Нет: 0
     *    - Есть: 2
     *
     * 4. Диабет:
     *    - Нет: 0
     *    - Есть: 1
     *
     * 5. Гиперхолестеринемия:
     *    - Нет: 0
     *    - Есть: 1
     *
     * 6. Артериальная гипертензия:
     *    - Нет: 0
     *    - Есть: 1
     *
     * 7. Курение:
     *    - Никогда: 0
     *    - Раньше: 1
     *    - Продолжает: 2
     *
     * 8. Прием антиагрегантов:
     *    - Нет: 0
     *    - Аспирин: 1
     *    - Другие: 2
     *    - Комбинация: 4
     *
     * 9. Прием оральных антикоагулянтов:
     *    - Нет: 0
     *    - Да: 2
     *
     * Интерпретация суммарного балла (степень риска кровотечения):
     * - 0–6: 0,46%
     * - 7–8: 0,95%
     * - 9–10: 1,25%
     * - ≥10: 2,76%
     *
     * Примечания:
     * - Баллы суммируются по всем факторам риска для получения общего риска.
     * - Используется для прогнозирования вероятности кровотечения у пациентов с атеросклерозом.
     * - Помогает принимать решения о необходимости корректировки терапии и профилактических мероприятий.
     */

    public static REACHResult calc(
            int age,
            boolean peripheralAtherosclerosis,
            boolean heartFailure,
            boolean diabetes,
            boolean hypercholesterolemia,
            boolean hypertension,
            int smokingStatus,
            int antiplatelet,
            boolean oralAnticoagulant
    ) {
        int score = 0;

        if (age >= 45 && age <= 54) score += 0;
        else if (age >= 55 && age <= 64) score += 2;
        else if (age >= 65 && age <= 74) score += 4;
        else if (age >= 75) score += 6;

        if (peripheralAtherosclerosis) score += 2;
        if (heartFailure) score += 2;
        if (diabetes) score += 1;
        if (hypercholesterolemia) score += 1;
        if (hypertension) score += 1;
        score += smokingStatus;
        score += antiplatelet;
        if (oralAnticoagulant) score += 2;

        String riskCategory;
        double riskPercent;

        if (score <= 6) { riskCategory = "низкий"; riskPercent = 0.46; }
        else if (score <= 8) { riskCategory = "умеренный"; riskPercent = 0.95; }
        else if (score <= 10) { riskCategory = "высокий"; riskPercent = 1.25; }
        else { riskCategory = "очень высокий"; riskPercent = 2.76; }

        String interpretation = String.format(
                "Сумма баллов: %d\nСтепень риска: %s\nВероятность кровотечения: %.2f%%",
                score, riskCategory, riskPercent
        );

        return new REACHResult(score, riskCategory, riskPercent, interpretation);
    }
}