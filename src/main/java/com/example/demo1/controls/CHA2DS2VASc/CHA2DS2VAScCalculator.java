package com.example.demo1.controls.CHA2DS2VASc;

import com.example.demo1.common.enums.Gender;

public class CHA2DS2VAScCalculator {

    public static CHA2DS2VAScResult calc(
            boolean congestiveheartfailure,
            boolean hypertension,
            int age,
            boolean diabetes,
            boolean stroke,
            boolean cardiovascular,
            Gender gender
    ) {
        int score = 0;

        if (congestiveheartfailure) score += 1;
        if (hypertension) score += 1;
        if (age >= 75) score += 2;
        else if (age >= 65) score += 1;
        if (diabetes) score += 1;
        if (stroke) score += 2;
        if (cardiovascular) score += 1;
        if (gender == Gender.FEMALE) score += 1;

        String diagnosis;
        if (score >= 2) {
            diagnosis = "Риск высокий (необходимо назначение непрямых антикоагулянтов)";
        } else if (score == 1) {
            diagnosis = "Риск промежуточный (антикоагулянты или антитагреганты)";
        } else {
            diagnosis = "Риск низкий (антитагреганты)";
        }

        return new CHA2DS2VAScResult(score, diagnosis);
    }
}