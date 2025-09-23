package com.example.demo1.controls.Khorana;

import java.util.List;

public class KhoranaCalculator {

    public static KhoranaResult calc(String tumorLocation, List<String> patientFactors) {
        if (tumorLocation == null || patientFactors == null) {
            return new KhoranaResult("Ошибка: выберите локализацию опухоли и факторы риска");
        }

        int tumorScore = mapTumorLocation(tumorLocation);
        int factorsScore = patientFactors.size(); // каждый выбранный фактор = 1 балл

        int totalScore = tumorScore + factorsScore;

        String riskGroup;
        String riskPercent;

        if (totalScore == 0) {
            riskGroup = "Низкий риск";
            riskPercent = "0,3 – 0,8%";
        } else if (totalScore == 2) {
            riskGroup = "Умеренный риск";
            riskPercent = "1,8 – 2,0%";
        } else if (totalScore >= 3) {
            riskGroup = "Высокий риск";
            riskPercent = "6,7 – 7,1%";
        } else {
            riskGroup = "Промежуточный риск";
            riskPercent = "?";
        }

        StringBuilder details = new StringBuilder();
        details.append("Локализация опухоли: ").append(tumorLocation).append(" (").append(tumorScore).append(" балла)\n");
        details.append("Факторы пациента:\n");
        for (String f : patientFactors) {
            details.append(" - ").append(f).append(" (1 балл)\n");
        }
        details.append("Суммарный балл: ").append(totalScore).append("\n");
        details.append("Группа риска: ").append(riskGroup).append("\n");
        details.append("Риск ВТЭО в течение 2,5 месяцев: ").append(riskPercent);

        return new KhoranaResult(details.toString());
    }

    private static int mapTumorLocation(String tumor) {
        switch (tumor) {
            case "Желудок":
            case "Поджелудочная железа":
                return 2;
            case "Легкие":
            case "Лимфома":
            case "Кровь":
            case "Яички":
            case "Яичники":
            case "Матка":
                return 1;
            default:
                return 0;
        }
    }
}
