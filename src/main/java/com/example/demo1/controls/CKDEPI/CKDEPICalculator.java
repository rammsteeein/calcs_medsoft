package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.CreatininUnit;
import com.example.demo1.common.enums.Gender;

public class CKDEPICalculator {

    /**
     * Расчет eGFR по формуле CKD-EPI (креатинин сыворотки)
     *
     * Формула:
     * eGFRcr = 142 * min(Scr / kappa, 1)^alpha * max(Scr / kappa, 1)^(-1.200)
     *          * 0.9938^Возраст
     *          * 1.012 (если пациентка женщина)
     *
     * Где:
     * Scr     - уровень креатинина в сыворотке (мг/дл)
     * kappa   - 0.7 для женщин, 0.9 для мужчин
     * alpha   - -0.241 для женщин, -0.302 для мужчин
     * min()   - минимальное значение между Scr/kappa и 1
     * max()   - максимальное значение между Scr/kappa и 1
     * Возраст - возраст пациента в годах
     *
     * Примечания:
     * - Возрастающая степень 0.9938^Возраст учитывает снижение функции почек с возрастом.
     * - Множитель 1.012 применяется только для женщин.
     */

    public static CKDEPIResult calc(Gender gender, double kreatinin, CreatininUnit creatininUnit, int age) {
        if (creatininUnit == CreatininUnit.MKMOL) {
            kreatinin /= 88.4;
        } else if (creatininUnit != CreatininUnit.MGDL) {
            return new CKDEPIResult("Некорректная единица измерения");
        }

        double kappa = (gender == Gender.MALE) ? 0.9 : 0.7;
        double alpha = (gender == Gender.MALE) ? -0.302 : -0.241;
        double genderMultiplier = (gender == Gender.FEMALE) ? 1.012 : 1.0;

        double minValue = Math.min(kreatinin / kappa, 1.0);
        double maxValue = Math.max(kreatinin / kappa, 1.0);
        double eGFR = 142 * Math.pow(minValue, alpha) * Math.pow(maxValue, -1.2) * Math.pow(0.9938, age) * genderMultiplier;

        String result = String.format("%.2f мл/мин", eGFR);
        return new CKDEPIResult(result);
    }
}