package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Gender;

public class MifflinStJeorCalculator {

    /**
     * Формула Миффлина-Сан Жеора (Mifflin-St Jeor, 2005) для расчёта базального метаболизма (BMR).
     *
     * Расчёт:
     * - Для мужчин:
     *      BMR = 10 × вес(кг) + 6.25 × рост(см) - 5 × возраст(лет) + 5
     * - Для женщин:
     *      BMR = 10 × вес(кг) + 6.25 × рост(см) - 5 × возраст(лет) - 161
     *
     * Примечания:
     * - BMR (Basal Metabolic Rate) — это количество калорий, которое организм расходует в состоянии покоя.
     * - Формула учитывает пол, возраст, вес и рост.
     * - Используется для расчёта дневной потребности в калориях при планировании диеты или контроля веса.
     */

    public static MifflinStJeorResult calc(Gender gender, double weightKg, double heightCm, int age) {
        double bmr;
        if (gender == Gender.MALE) {
            bmr = 10 * weightKg + 6.25 * heightCm - 5 * age + 5;
        } else {
            bmr = 10 * weightKg + 6.25 * heightCm - 5 * age - 161;
        }

        String resultStr = String.format("BMR: %.2f ккал/сутки", bmr);
        return new MifflinStJeorResult(resultStr);
    }
}