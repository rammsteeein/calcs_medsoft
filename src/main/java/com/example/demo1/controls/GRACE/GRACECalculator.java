package com.example.demo1.controls.GRACE;

public class GRACECalculator {

    public static GRACEResult calc(int agePoints, int hrPoints, int sbpPoints, int killipPoints,
                                   int creatininePoints, int otherPoints) {
        int total = agePoints + hrPoints + sbpPoints + killipPoints + creatininePoints + otherPoints;

        String risk;
        if (total <= 108) {
            risk = "Низкий (<1%)";
        } else if (total <= 140) {
            risk = "Умеренный (1–3%)";
        } else {
            risk = "Высокий (>3%)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Баллы:\n");
        sb.append("Возраст: ").append(agePoints).append("\n");
        sb.append("ЧСС: ").append(hrPoints).append("\n");
        sb.append("Систолическое АД: ").append(sbpPoints).append("\n");
        sb.append("Класс по Киллип: ").append(killipPoints).append("\n");
        sb.append("Креатинин: ").append(creatininePoints).append("\n");
        sb.append("Другие факторы: ").append(otherPoints).append("\n");
        sb.append("Сумма баллов: ").append(total).append("\n");
        sb.append("Риск смерти в стационаре: ").append(risk);

        return new GRACEResult(sb.toString());
    }
}