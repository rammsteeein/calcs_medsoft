package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;

public class HSICalculator {

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
