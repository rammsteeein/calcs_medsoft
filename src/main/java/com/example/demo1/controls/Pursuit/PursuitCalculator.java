package com.example.demo1.controls.Pursuit;

import com.example.demo1.common.enums.Gender;

public class PursuitCalculator {

    /**
     * Расчет баллов по шкале Pursuit для оценки риска смертности при ОКС без подъема ST
     *
     * Критерии начисления баллов:
     * 1. Возраст:
     *    - 50-59 лет: 8 баллов
     *    - 60-69 лет: 9 баллов
     *    - 70-79 лет: 11 баллов
     *    - >80 лет: 12 баллов
     * 2. Пол:
     *    - Мужчина: 1 балл
     * 3. Стенокардия III-IV ФК за последние 6 недель: 2 балла
     * 4. Сердечная недостаточность (хрипы >1/3 легочных полей): 2 балла
     * 5. ST-депрессия на ЭКГ: 1 балл
     *
     * Интерпретация суммарного балла:
     * - Низкий риск: <=12 баллов, смертность <10% за 30 дней
     * - Средний риск: 13-14 баллов, смертность 10-19% за 30 дней
     * - Высокий риск: >14 баллов, смертность >19% за 30 дней
     *
     * Примечания:
     * - Баллы суммируются по всем критериям, итоговое значение определяет категорию риска.
     */

    public static PursuitResult calc(int age, Gender gender, boolean hasAngina, boolean hasHeartFailure) {
        int score = 0;

        if (age >= 50 && age <= 59) score += 8;
        else if (age >= 60 && age <= 69) score += 9;
        else if (age >= 70 && age <= 79) score += 11;
        else if (age >= 80) score += 12;

        if (gender == Gender.MALE) score += 1;
        if (hasAngina) score += 2;
        if (hasHeartFailure) score += 2;

        String riskCategory;
        if (score <= 12) riskCategory = "Низкий риск (<10%)";
        else if (score <= 14) riskCategory = "Средний риск (10–19%)";
        else riskCategory = "Высокий риск (>19%)";

        String result = String.format("Сумма баллов: %d, Категория: %s", score, riskCategory);
        return new PursuitResult(result);
    }
}
