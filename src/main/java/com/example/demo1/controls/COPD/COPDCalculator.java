package com.example.demo1.controls.COPD;

public class COPDCalculator {

    public static COPDResult calc(
            String age,
            int packYears,
            double bmi,
            String weatherCough,
            String coughWithPhlegm,
            String morningCough,
            String dyspnea,
            String allergy
    ) {

        int total = 0;

        total += mapAge(age);
        total += mapPackYears(packYears);
        total += mapBMI(bmi);
        total += mapWeatherCough(weatherCough);
        total += mapCoughWithPhlegm(coughWithPhlegm);
        total += mapMorningCough(morningCough);
        total += mapDyspnea(dyspnea);
        total += mapAllergy(allergy);

        String interpretation;

        if (total >= 17) {
            interpretation = "Диагноз ХОБЛ вероятен. Рекомендуется спирометрия для подтверждения.";
        } else {
            interpretation = "Рассмотрите другие заболевания, включая бронхиальную астму, или направьте к специалисту.";
        }

        return new COPDResult(total, interpretation);
    }

    private static int mapAge(String age) {
        if (age == null) return 0;

        switch (age) {
            case "40 – 49 лет": return 0;
            case "50 – 59 лет": return 4;
            case "60 – 69 лет": return 8;
            case "70 лет и старше": return 10;
            default: return 0;
        }
    }

    private static int mapPackYears(int packYears) {
        if (packYears < 15) return 0;
        if (packYears < 25) return 2;
        if (packYears < 50) return 3;
        return 7;
    }

    private static int mapBMI(double bmi) {
        if (bmi < 25.4) return 5;
        if (bmi <= 29.7) return 1;
        return 0;
    }

    private static int mapWeatherCough(String value) {
        if (value == null) return 0;
        return value.equals("Да") ? 3 : 0;
    }

    private static int mapCoughWithPhlegm(String value) {
        if (value == null) return 0;
        return value.equals("Да") ? 3 : 0;
    }

    private static int mapMorningCough(String value) {
        if (value == null) return 0;
        return value.equals("Да") ? 0 : 3;
    }

    private static int mapDyspnea(String value) {
        if (value == null) return 0;
        return value.equals("Иногда или чаще") ? 4 : 0;
    }

    private static int mapAllergy(String value) {
        if (value == null) return 0;
        return value.equals("Нет") ? 3 : 0;
    }
}