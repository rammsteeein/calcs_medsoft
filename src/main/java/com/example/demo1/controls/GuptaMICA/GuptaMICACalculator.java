package com.example.demo1.controls.GuptaMICA;

import java.util.Map;

public class GuptaMICACalculator {

    /**
     * Расчет риска MICA (Myocardial Infarction or Cardiac Arrest) по шкале Gupta
     *
     * Формула:
     * x = -5,25 + сумма значений выбранных переменных
     * MICA Риск (%) = e^x / (1 + e^x)
     *
     * Факторы и их баллы:
     * 1. Возраст пациента:
     *    - Балл = число лет * 0,02
     * 2. Функциональное состояние пациента:
     *    - Полностью независимый: 0
     *    - Частично зависимый: 0,65
     *    - Полностью зависимый: 1,03
     * 3. Статус по ASA:
     *    - ASA I: -5,17
     *    - ASA II: -3,29
     *    - ASA III: -1,92
     *    - ASA IV: -0,95
     *    - ASA V: 0
     * 4. Креатинин:
     *    - Нормальный (≤1,5 мг/дл, ≤133 мкмоль/л): 0
     *    - Повышенный (>1,5 мг/дл, >133 мкмоль/л): 0,61
     *    - Неизвестный: -0,1
     * 5. Тип хирургического вмешательства (выбор из множества вариантов):
     *    - Примеры: грыжи: 0, операции на аорте: 1,6, операции на голове: 1,4 и т.д.
     *
     * Интерпретация риска:
     * - <0,05% (ниже 25-го перцентиля): низкий
     * - 0,05 - 0,14% (26-50-й перцентиль): средний
     * - 0,14 - 1,47% (51-90-й перцентиль): выше среднего
     * - 1,47 - 2,60% (91-95-й перцентиль): высокий
     * - 2,60 - 7,69% (96-97-й перцентиль) и выше: очень высокий
     *
     * Примечания:
     * - Баллы каждого параметра суммируются, затем рассчитывается экспоненциальная вероятность.
     * - Формула используется для оценки риска сердечного инфаркта или остановки сердца в период периоперационного периода.
     * - Для хирургического вмешательства с неизвестным значением балла рекомендуется уточнить классификацию.
     */

    public static GuptaMICAResult calc(double ageYears,
                                       String functionalStatus,
                                       String asaStatus,
                                       String creatinine,
                                       String surgeryType) {

        Map<String, Double> funcMap = GuptaMICAModel.functionalStatusMap;
        Map<String, Double> asaMap = GuptaMICAModel.asaStatusMap;
        Map<String, Double> creatMap = GuptaMICAModel.creatinineMap;
        Map<String, Double> surgMap = GuptaMICAModel.surgeryTypeMap;

        double funcVal = functionalStatus != null ? funcMap.getOrDefault(functionalStatus, 0.0) : 0.0;
        double asaVal = asaStatus != null ? asaMap.getOrDefault(asaStatus, 0.0) : 0.0;
        double creatVal = creatinine != null ? creatMap.getOrDefault(creatinine, 0.0) : 0.0;
        double surgVal = surgeryType != null ? surgMap.getOrDefault(surgeryType, 0.0) : 0.0;

        double x = -5.25
                + ageYears * 0.02
                + funcVal
                + asaVal
                + creatVal
                + surgVal;

        double risk = Math.exp(x) / (1 + Math.exp(x)) * 100;

        String riskCategory;
        if (risk < 0.05) riskCategory = "Низкий риск (<0.05%)";
        else if (risk < 0.14) riskCategory = "Средний риск (0.05–0.14%)";
        else if (risk < 1.47) riskCategory = "Выше среднего (0.14–1.47%)";
        else if (risk < 2.60) riskCategory = "Высокий риск (1.47–2.60%)";
        else riskCategory = "Очень высокий риск (≥2.60%)";

        String resultText = String.format(
                "x = %.2f\nMICA риск = %.4f%%\nСтепень риска: %s",
                x, risk, riskCategory
        );

        return new GuptaMICAResult(resultText, risk);
    }
}