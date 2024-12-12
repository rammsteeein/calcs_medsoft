package com.example.demo1;

public class CKDEPICalculator {

    public static CKDEPIResult calc(String gender, double kreatinin, String creatininUnit, int age) {
        if ("мкмоль/л".equalsIgnoreCase(creatininUnit)) {
            kreatinin /= 88.4;
        } else if (!"мг/дл".equalsIgnoreCase(creatininUnit)) {
            return new CKDEPIResult("Не числовое значение");
        }


        double kappa = gender.equalsIgnoreCase("мужчина") ? 0.9 : 0.7;
        double alpha = gender.equalsIgnoreCase("мужчина") ? -0.302 : -0.241;
        double genderMultiplier = gender.equalsIgnoreCase("женщина") ? 1.012 : 1.0;

        double minValue = Math.min(kreatinin / kappa, 1.0);
        double maxValue = Math.max(kreatinin / kappa, 1.0);
        double eGFR = 142 * Math.pow(minValue, alpha) * Math.pow(maxValue, -1.2) * Math.pow(0.9938, age) * genderMultiplier;


        String result = String.format("%.2f мл/мин", eGFR);
        return new CKDEPIResult(result);
    }
}