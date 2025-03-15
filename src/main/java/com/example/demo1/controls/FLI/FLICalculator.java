package com.example.demo1.controls.FLI;

public class FLICalculator {

    public static FLIResult calc(double triglycerides, double bmi, double ggt, double waistCircumference) {
        if (triglycerides <= 0 || bmi <= 0 || ggt <= 0 || waistCircumference <= 0) {
            return new FLIResult("Некорректные входные данные");
        }

        double x = 0.953 * Math.log(triglycerides) +
                0.139 * bmi +
                0.718 * Math.log(ggt) +
                0.053 * waistCircumference - 15.745;

        double fli = (Math.exp(x) / (1 + Math.exp(x))) * 100;

        String result = String.format("%.2f", x);
        return new FLIResult(result);
    }
}