package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;

public class INBARCalculator {

    public static INBARResult calc(double age, double weight, Gender gender) {
        if (age <= 0 || weight <= 0) {
            return new INBARResult("Некорректные входные данные");
        }

        double x = 210 - age - (0.11 * weight);
        if (gender == Gender.MALE) {
            x += 4;
        }

        String result = String.format("%.2f уд/мин", x);
        return new INBARResult(result);
    }
}
