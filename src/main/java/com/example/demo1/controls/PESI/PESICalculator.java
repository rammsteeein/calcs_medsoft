package com.example.demo1.controls.PESI;

import com.example.demo1.common.enums.Gender;

public class PESICalculator {

    /**
     * Шкала PESI (Pulmonary Embolism Severity Index) —
     * прогностическая система для оценки 30-дневной летальности при тромбоэмболии лёгочной артерии (ТЭЛА).
     *
     * Критерии и баллы:
     * - Возраст: число лет (баллы = возраст в годах)
     * - Мужской пол: +10
     * - Наличие рака: +30
     * - Хроническая сердечная недостаточность: +10
     * - Хроническое заболевание лёгких: +10
     * - ЧСС ≥110 уд/мин: +20
     * - Систолическое АД <100 мм рт. ст.: +30
     * - Частота дыхания >30 в минуту: +20
     * - Температура <36 °C: +20
     * - Нарушение сознания: +60
     * - Сатурация SpO₂ <90%: +20
     *
     * Стратификация риска (классы PESI):
     * - Класс I: ≤65 баллов → Очень низкий риск (летальность 0–1.6%)
     * - Класс II: 66–85 баллов → Низкий риск (1.7–3.5%)
     * - Класс III: 86–105 баллов → Умеренный риск (3.2–7.1%)
     * - Класс IV: 106–125 баллов → Высокий риск (4.0–11.4%)
     * - Класс V: >125 баллов → Очень высокий риск (10.0–24.5%)
     *
     * Применение:
     * - Позволяет стратифицировать пациентов по риску смертности.
     * - В клинике используется для выбора тактики лечения (амбулаторно vs. госпитализация, интенсивная терапия).
     * - Чем выше класс, тем агрессивнее ведение пациента.
     */

    public static PESIResult calc(
            int age,
            Gender gender,
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

        score += age;
        if (gender == Gender.MALE) score += 10;
        if (hasCancer) score += 30;
        if (hasCHF) score += 10;
        if (hasChronicLungDisease) score += 10;
        if (heartRate >= 110) score += 20;
        if (systolicBP < 100) score += 30;
        if (respiratoryRate > 30) score += 20;
        if (temperature < 36.0) score += 20;
        if (alteredMentalStatus) score += 60;
        if (oxygenSaturation < 90.0) score += 20;

        String riskClass;
        if (score <= 65) riskClass = "Класс I — Очень низкий риск (0–1.6%)";
        else if (score <= 85) riskClass = "Класс II — Низкий риск (1.7–3.5%)";
        else if (score <= 105) riskClass = "Класс III — Умеренный риск (3.2–7.1%)";
        else if (score <= 125) riskClass = "Класс IV — Высокий риск (4.0–11.4%)";
        else riskClass = "Класс V — Очень высокий риск (10.0–24.5%)";

        return new PESIResult(score, riskClass);
    }
}