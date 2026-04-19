package com.example.demo1.controls.SCORE2OP;

import com.example.demo1.common.resources.*;

import java.util.Map;

public class Score2OPCalculator {

    /**
     * Шкала SCORE2-OP (Systematic COronary Risk Evaluation 2 — Older Persons)
     * для оценки 10-летнего риска фатальных и нефатальных сердечно-сосудистых событий
     * у пациентов старше 70 лет.
     * Модель разработана Европейским обществом кардиологов (ESC)
     * и основана на популяционных данных европейских когорт.
     * Учитываемые факторы риска:
     * 1. Возраст:
     *    - Основной независимый фактор риска
     *    - С увеличением возраста риск экспоненциально возрастает
     * 2. Пол:
     *    - Мужчины имеют более высокий базовый риск по сравнению с женщинами
     * 3. Курение:
     *    - Активное курение значительно увеличивает риск сосудистых событий
     * 4. Систолическое артериальное давление:
     *    - Отражает нагрузку на сосудистую систему
     *    - Более высокие значения ассоциированы с ростом риска инсульта и инфаркта
     * 5. Общий холестерин:
     *    - Маркер атерогенного риска
     *    - Повышение уровня увеличивает вероятность сосудистых осложнений
     * Метод расчёта:
     * - Используется табличная модель риска (risk charts)
     * - Значения интерполируются по возрасту, полу, статусу курения,
     *   уровню артериального давления и холестерина
     * Интерпретация 10-летнего риска:
     * - < 5%: низкий риск
     * - 5–10%: умеренный риск
     * - 10–20%: высокий риск
     * - > 20%: очень высокий риск
     * Особенности:
     * - Применяется только для возрастной группы 70+ лет (в версии OP)
     * - Используется для первичной профилактики сердечно-сосудистых заболеваний
     */

    public static Score2OPResult calc(
            boolean absolute,
            int age,
            String gender,
            boolean smoking,
            int sysAd,
            int cholesterol
    ) {
        try {
            Map<ScoreKey, Integer> table =
                    ScoreTableContext.getScoreTable(absolute, age, gender, smoking);

            Integer score = table.entrySet().stream()
                    .filter(e ->
                            e.getKey().getSysAd() == sysAd &&
                                    e.getKey().getCholesterol().intValue() == cholesterol
                    )
                    .map(Map.Entry::getValue)
                    .findFirst()
                    .orElse(null);

            if (score == null) {
                return new Score2OPResult(
                        0,
                        "Нет данных для выбранных параметров",
                        age,
                        sysAd,
                        cholesterol
                );
            }

            return new Score2OPResult(
                    score,
                    interpret(score),
                    age,
                    sysAd,
                    cholesterol
            );

        } catch (Exception e) {
            return new Score2OPResult(
                    0,
                    "Ошибка расчёта",
                    age,
                    sysAd,
                    cholesterol
            );
        }
    }

    private static String interpret(int score) {
        if (score < 5) return "Низкий риск";
        if (score < 10) return "Умеренный риск";
        if (score < 20) return "Высокий риск";
        return "Очень высокий риск";
    }
}