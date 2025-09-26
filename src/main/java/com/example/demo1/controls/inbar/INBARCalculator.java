package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;

public class INBARCalculator {

    /**
     * Расчет максимальной частоты сердечных сокращений (ЧССmax)
     *
     * Формула Inbar:
     * 1. Для мужчин:
     *    ЧССmax = 210 - возраст - (0.11 * вес_в_кг) + 4
     * 2. Для женщин:
     *    ЧССmax = 210 - возраст - (0.11 * вес_в_кг)
     *
     * Альтернативная простая формула:
     *    ЧССmax = 220 - возраст
     *
     * Где:
     * - возраст: возраст пациента в годах
     * - вес_в_кг: вес пациента в килограммах
     *
     * Примечания:
     * - Формула Inbar учитывает пол и вес пациента для более точной оценки ЧССmax.
     * - Простая формула (220 - возраст) используется для грубой оценки без учета веса и пола.
     */
    public static INBARResult calc(double age, double weight, Gender gender) {
        if (age <= 0 || weight <= 0) {
            return new INBARResult("Некорректные входные данные");
        }

        double hr = 210 - age - (0.11 * weight);
        if (gender == Gender.MALE) {
            hr += 4;
        }

        String result = String.format("%.0f уд/мин", hr);
        return new INBARResult(result);
    }
}
