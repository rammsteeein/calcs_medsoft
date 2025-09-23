package com.example.demo1.controls.inbar;

public class INBARCalculator {

    public static INBARResult calc(double age, double weight) {
        if (age <= 0 || weight <= 0) {
            return new INBARResult("Некорректные входные данные");
        }

        double x = 210 - age - (0.11 * weight) + 4;


        String result = String.format("%.2f", x);
        return new INBARResult(result);
    }
}