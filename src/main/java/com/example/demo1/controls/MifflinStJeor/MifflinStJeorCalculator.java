package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Gender;

public class MifflinStJeorCalculator {

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