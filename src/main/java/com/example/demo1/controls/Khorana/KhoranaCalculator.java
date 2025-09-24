package com.example.demo1.controls.Khorana;

import java.util.List;

public class KhoranaCalculator {

    /**
     * Шкала Khorana для оценки предрасположенности к венозной тромбоэмболии (ВТЭО) у онкологических пациентов
     *
     * Факторы риска и баллы:
     * 1. Локализация злокачественного новообразования (ЗНО):
     *    - Желудок: 2 балла
     *    - Поджелудочная железа: 2 балла
     *    - Легкие, лимфома, кровь, яички, яичники, матка: 1 балл
     *
     * 2. Лабораторные показатели:
     *    - Тромбоциты > 350 x 10^9/л: 1 балл
     *    - Гемоглобин < 100 г/л или использование эритропоэтинов: 1 балл
     *    - Лейкоциты > 11 x 10^9/л: 1 балл
     *
     * 3. Масса тела:
     *    - ИМТ ≥ 35 кг/м²: 1 балл
     *
     * Интерпретация суммарного балла (группы риска):
     * - 0 баллов: низкий риск ВТЭО (0,3 – 0,8%)
     * - 1–2 балла: умеренный риск ВТЭО (1,8 – 2,0%)
     * - ≥3 баллов: высокий риск ВТЭО (6,7 – 7,1%)
     *
     * Примечания:
     * - Баллы суммируются по всем категориям (локализация, лабораторные показатели, масса тела).
     * - Используется для прогнозирования риска тромбоэмболических осложнений в течение 2,5 месяцев после постановки диагноза или начала терапии.
     * - Позволяет принимать решения о профилактике ВТЭО у пациентов с онкологическими заболеваниями.
     */

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
