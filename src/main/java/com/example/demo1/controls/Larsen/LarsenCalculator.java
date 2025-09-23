package com.example.demo1.controls.Larsen;

import java.util.List;

public class LarsenCalculator {

    public static LarsenResult calc(String drug, List<String> patientFactors) {
        if (drug == null || patientFactors == null) {
            return new LarsenResult("Ошибка: выберите препарат и факторы пациента");
        }

        int drugScore = mapDrug(drug);
        int patientScore = patientFactors.size(); // каждый выбранный фактор = 1 балл

        int totalScore = drugScore + patientScore;

        String interpretation;
        if (totalScore >= 6) {
            interpretation = "Высокий риск кардиотоксичности";
        } else if (totalScore >= 3) {
            interpretation = "Промежуточный риск";
        } else if (totalScore >= 1) {
            interpretation = "Низкий риск";
        } else {
            interpretation = "Отсутствие риска";
        }

        StringBuilder details = new StringBuilder();
        details.append("Препарат: ").append(drug).append(" (").append(drugScore).append(" балла)\n");
        details.append("Факторы пациента:\n");
        for (String f : patientFactors) {
            details.append(" - ").append(f).append(" (1 балл)\n");
        }
        details.append("Суммарный балл: ").append(totalScore).append("\n");
        details.append("Интерпретация: ").append(interpretation);

        return new LarsenResult(details.toString());
    }

    private static int mapDrug(String drug) {
        switch (drug) {
            case "Антрациклины":
            case "Циклофосфан":
            case "Ифосфамид":
            case "Клофарабин":
            case "Герцептин":
                return 4; // высокий риск
            case "Доцетаксел":
            case "Пертузумаб":
            case "Сунитиниб":
            case "Сорафениб":
                return 2; // промежуточный риск
            case "Бевацизумаб":
            case "Дазатиниб":
            case "Иматиниб":
            case "Лапатиниб":
                return 1; // низкий риск
            case "Этопозид":
            case "Ритуксимаб":
            case "Талидомид":
                return 0; // отсутствие риска
            default:
                throw new IllegalArgumentException("Неизвестный препарат: " + drug);
        }
    }
}