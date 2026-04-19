package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;

public class CockroftCalculator {

    /**
     * Расчет клиренса креатинина по формуле Cockcroft-Gault
     *
     * Формула:
     * CrCl = ((140 - возраст) * вес) * (0.85 если женщина) / (72 * Scr)
     *
     * Где:
     * возраст - возраст пациента в годах
     * вес     - масса пациента в килограммах
     * Scr     - уровень креатинина в сыворотке (мг/дл)
     * 0.85    - коэффициент для женщин (для мужчин не применяется)
     * 72      - стандартный коэффициент в формуле
     *
     * Примечания:
     * - Формула используется для оценки скорости клубочковой фильтрации у взрослых.
     * - Корректировка для пола учитывает меньшую мышечную массу у женщин.
     * - Для точного расчета желательно использовать фактический вес пациента.
     */

    public static double calc(Gender gender, double kreatinin, double weight, int age) {
        double genderFactor = (gender == Gender.FEMALE) ? 0.85 : 1.0;
        return ((140 - age) * weight * genderFactor) / (72 * kreatinin);
    }

    public static CockroftResult calcWithInterpretation(Gender gender, double kreatinin, double weight, int age) {
        double value = calc(gender, kreatinin, weight, age);

        String interpretation;

        if (value >= 90) {
            interpretation = "Норма или высокая функция почек (С1, N18.1)";
        } else if (value >= 60) {
            interpretation = "Незначительно сниженная функция (С2, N18.2)";
        } else if (value >= 45) {
            interpretation = "Умеренно сниженная функция (С3a, N18.3)";
        } else if (value >= 30) {
            interpretation = "Существенно сниженная функция (С3b)";
        } else if (value >= 15) {
            interpretation = "Резко сниженная функция (С4, N18.4)";
        } else {
            interpretation = "Терминальная почечная недостаточность (С5, N18.5)";
        }

        return new CockroftResult(value, Unit.MGDL, interpretation);
    }
}