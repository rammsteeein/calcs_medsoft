package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Gender;

public class CockroftCalculator {

    public static double calc(Gender gender, double kreatinin, double weight, int age) {
        double genderFactor = (gender == Gender.FEMALE) ? 0.85 : 1.0;
        return ((140 - age) * weight * genderFactor) / (72 * kreatinin);
    }
}