package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.ResultStyler;

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

        String category;
        ResultStyler.Zone zone;
        if (hsi <= 30) {
            category = "HSI ниже 30 указывает на то, что НАЖБП можно исключить (с отрицательным коэффициентом вероятности до 0,186).";
            zone = ResultStyler.Zone.LOW;
        } else {
            category = "показатель HSI, равный 36 или выше, указывает на наличие неалкогольной жировой болезни печени " +
                    "(с коэффициентом вероятности положительного результата от 6,069)";
            zone = ResultStyler.Zone.HIGH;
        }

        String resultStr = String.format("HSI: %.2f\nКатегория: %s", hsi, category);
        return new HSIResult(resultStr, hsi, zone);
    }
}