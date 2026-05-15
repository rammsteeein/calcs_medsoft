package com.example.demo1.controls.UAS7;

public class UAS7Calculator {

    /**
     * UAS7 — Urticaria Activity Score
     * Оценка активности хронической крапивницы за 7 дней.
     *
     * Каждый день оцениваются 2 параметра:
     *
     * 1. Количество волдырей:
     *    0 — отсутствуют
     *    1 — <20 волдырей
     *    2 — 20–50 волдырей
     *    3 — >50 волдырей / сливные элементы
     *
     * 2. Кожный зуд:
     *    0 — отсутствует
     *    1 — легкий
     *    2 — умеренный
     *    3 — выраженный
     *
     * За сутки:
     *    0–6 баллов
     *
     * За 7 дней:
     *    0–42 балла
     *
     * Интерпретация:
     *    0 — отсутствие симптомов
     *    1–6 — хорошо контролируемая крапивница
     *    7–15 — легкая степень
     *    16–27 — средняя степень
     *    28–42 — тяжелое течение
     */

    public static UAS7Result calc(int totalScore) {

        String interpretation;

        if (totalScore == 0) {
            interpretation = "Отсутствие симптомов";
        }
        else if (totalScore <= 6) {
            interpretation = "Хорошо контролируемая крапивница";
        }
        else if (totalScore <= 15) {
            interpretation = "Легкая степень";
        }
        else if (totalScore <= 27) {
            interpretation = "Средняя степень тяжести";
        }
        else {
            interpretation = "Тяжелое течение";
        }

        return new UAS7Result(totalScore, interpretation);
    }
}