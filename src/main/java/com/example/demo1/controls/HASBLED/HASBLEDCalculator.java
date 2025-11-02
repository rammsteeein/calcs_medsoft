package com.example.demo1.controls.HASBLED;

/**
 * Шкала HAS-BLED оценивает риск кровотечений у пациентов, получающих антикоагулянтную терапию.
 *
 * Компоненты:
 * H — Артериальная гипертензия (>160 мм рт. ст.) — 1 балл
 * A — Нарушение функции почек и/или печени — 1 балла
 * S — Перенесённый инсульт — 1 балл
 * B — История кровотечений — 1 балл
 * L — Лабильное МНО — 1 балл
 * E — Возраст ≥65 лет — 1 балл
 * D — Препараты/алкоголь, повышающие риск кровотечений — 1 балл
 *
 * Интерпретация:
 * 0–2 балла — низкий или умеренный риск
 * ≥3 баллов — высокий риск кровотечений
 */
public class HASBLEDCalculator {

    public static HASBLEDResult calc(
            String hypertension,
            String renalLiver,
            String stroke,
            String bleeding,
            String inr,
            String age,
            String drugsAlcohol
    ) {
        int total = 0;
        int count = 0;

        if (hypertension != null) { total += mapYesNo(hypertension); count++; }
        if (renalLiver != null) { total += mapYesNo(renalLiver); count++; }
        if (stroke != null) { total += mapYesNo(stroke); count++; }
        if (bleeding != null) { total += mapYesNo(bleeding); count++; }
        if (inr != null) { total += mapYesNo(inr); count++; }
        if (age != null) { total += mapYesNo(age); count++; }
        if (drugsAlcohol != null) { total += mapYesNo(drugsAlcohol); count++; }

        if (count == 0) {
            return new HASBLEDResult(0, "Выберите хотя бы один параметр");
        }

        String interpretation;
        if (total <= 2) {
            interpretation = "Низкий или умеренный риск кровотечений";
        } else {
            interpretation = "Высокий риск кровотечений. Требуется осторожность и контроль факторов риска.";
        }

        return new HASBLEDResult(total, interpretation);
    }

    private static int mapYesNo(String value) {
        switch (value) {
            case "Да": return 1;
            case "Нет": return 0;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }
}