package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;

public class HSICalculator {

    /**
     * Индекс стеатоза печени (HSI, Hepatic Steatosis Index) — используется для оценки
     * вероятности жировой болезни печени (NAFLD) без проведения биопсии.
     *
     * Формула расчёта:
     * HSI = 8 × (АЛТ / АСТ) + ИМТ
     *       + 2, если пациент женщина
     *       + 2, если у пациента сахарный диабет (СД)
     *
     * Примечания:
     * - АЛТ и АСТ — уровень печёночных ферментов в крови.
     * - ИМТ — индекс массы тела (кг/м²).
     * - Значение HSI выше определённого порога указывает на высокую вероятность стеатоза печени.
     * - Простая и быстрая шкала для первичного скрининга пациентов.
     */

    public static HSIResult calc(
            double alt,
            double ast,
            double bmi,
            Gender gender,
            boolean hasDiabetes
    ) {
        double hsi = 8 * (alt / ast) + bmi;
        if (gender == Gender.FEMALE) hsi += 2;
        if (hasDiabetes) hsi += 2;

        String resultStr = String.format("HSI: %.2f", hsi);
        return new HSIResult(resultStr);
    }
}