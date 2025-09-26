package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Gender;

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
    }
