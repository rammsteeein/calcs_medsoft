package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;

public class MifflinStJeorCalculator {

    /**
     * Формула Миффлина-Сан Жеора (Mifflin-St Jeor, 2005) для расчёта базального метаболизма (BMR).
     *
     * - Мужчины: BMR = 10 × вес(кг) + 6.25 × рост(см) - 5 × возраст(лет) + 5
     * - Женщины: BMR = 10 × вес(кг) + 6.25 × рост(см) - 5 × возраст(лет) - 161
     */
    public static MifflinStJeorResult calc(Gender gender, double weightKg, double heightCm, int age) {
        double bmr;
        if (gender == Gender.MALE) {
            bmr = 10 * weightKg + 6.25 * heightCm - 5 * age + 5;
        } else {
            bmr = 10 * weightKg + 6.25 * heightCm - 5 * age - 161;
        }

        String calculation = String.format("BMR: %.2f ккал/сутки", bmr);

        return new MifflinStJeorResult(bmr, Unit.KCAL_PER_DAY, calculation);
    }
}