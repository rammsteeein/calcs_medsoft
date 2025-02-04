package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.CreatininUnit;
import com.example.demo1.common.enums.Gender;

public class CKDEPICalculator {

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