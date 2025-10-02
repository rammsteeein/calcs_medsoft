package com.example.demo1.controls.GRACE;

import java.util.ArrayList;
import java.util.List;

public class GRACECalculator {
    /**
     * Шкала GRACE (Global Registry of Acute Coronary Events).
     * Используется для оценки прогноза у пациентов с острым коронарным синдромом (ОКС).
     * Позволяет предсказать внутригоспитальную и 6-месячную смертность на основании
     * клинических и лабораторных показателей (возраст, ЧСС, АД, креатинин, Killip,
     * изменения на ЭКГ, остановка сердца при поступлении, ферменты миокарда).
     */

    public static class Factor {
        private final String name;
        private final int points;

        public Factor(String name, int points) {
            this.name = name;
            this.points = points;
        }

        public String getName() { return name; }
        public int getPoints() { return points; }
    }

    public static GRACEResult calc(int age, int hr, int sbp, String killip, double creatinine, String other) {
        List<Factor> factors = new ArrayList<>();

        int agePoints = getAgePoints(age);
        int hrPoints = getHRPoints(hr);
        int sbpPoints = getSBPPoints(sbp);
        int killipPoints = mapKillipToPoints(killip);
        int creatPoints = getCreatininePoints(creatinine);
        int otherPoints = mapOtherPoints(other);

        factors.add(new Factor("Возраст", agePoints));
        factors.add(new Factor("ЧСС", hrPoints));
        factors.add(new Factor("САД", sbpPoints));
        factors.add(new Factor("Killip", killipPoints));
        factors.add(new Factor("Креатинин", creatPoints));
        factors.add(new Factor("Другие факторы", otherPoints));

        int total = agePoints + hrPoints + sbpPoints + killipPoints + creatPoints + otherPoints;

        String risk;
        if (total <= 108) risk = "Низкий риск (<1%)";
        else if (total <= 140) risk = "Умеренный риск (1–3%)";
        else risk = "Высокий риск (>3%)";

        return new GRACEResult(total, risk, factors);
    }

    public static int getAgePoints(int age) {
        if (age <= 30) return 0;
        else if (age <= 39) return 8;
        else if (age <= 49) return 25;
        else if (age <= 59) return 41;
        else if (age <= 69) return 58;
        else if (age <= 79) return 75;
        else if (age <= 89) return 91;
        else return 100;
    }

    public static int getHRPoints(int hr) {
        if (hr <= 50) return 0;
        else if (hr <= 69) return 3;
        else if (hr <= 89) return 9;
        else if (hr <= 109) return 15;
        else if (hr <= 149) return 24;
        else if (hr <= 199) return 38;
        else return 46;
    }

    public static int getSBPPoints(int sbp) {
        if (sbp <= 80) return 58;
        else if (sbp <= 99) return 53;
        else if (sbp <= 119) return 43;
        else if (sbp <= 139) return 34;
        else if (sbp <= 159) return 24;
        else if (sbp <= 199) return 10;
        else return 0;
    }

    public static int getCreatininePoints(double creat) {
        if (creat < 0.4) return 1;
        else if (creat < 0.8) return 4;
        else if (creat < 1.2) return 7;
        else if (creat < 1.6) return 10;
        else if (creat < 2.0) return 13;
        else if (creat < 4.0) return 21;
        else return 28;
    }

    public static int mapKillipToPoints(String killip) {
        if (killip == null) return 0;
        switch (killip) {
            case "I": return 0;
            case "II": return 20;
            case "III": return 39;
            case "IV": return 59;
            default: return 0;
        }
    }

    public static int mapOtherPoints(String other) {
        if (other == null) return 0;
        switch (other) {
            case "Остановка сердца при поступлении": return 39;
            case "Смещение ST / инверсия T": return 28;
            case "Повышенные маркеры некроза": return 14;
            default: return 0;
        }
    }
}
