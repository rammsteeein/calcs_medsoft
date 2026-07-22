package com.example.demo1.controls.Morisky;

public class MoriskyCalculator {

    /**
     * Шкала Мориски–Грин (Morisky-Green)
     *
     * Вопросы:
     * 1. Вы когда-нибудь забывали принимать препараты?
     * 2. Не относитесь ли вы невнимательно к часам приема препаратов?
     * 3. Если вы чувствуете себя лучше, вы иногда прекращаете прием препаратов?
     * 4. Иногда, если вы чувствуете себя плохо после приема препаратов,
     *    вы пропускаете следующий прием?
     *
     * Начисление баллов:
     * Да  = 1
     * Нет = 0
     *
     * Интерпретация:
     * 0 баллов  — Высокая приверженность
     * 1 балл    — Недостаточная приверженность (группа риска)
     * ≥2 баллов — Неприверженность
     */

    public static MoriskyResult calc(
            String q1,
            String q2,
            String q3,
            String q4
    ) {

        int total = 0;
        int count = 0;

        if (q1 != null) {
            total += mapAnswer(q1);
            count++;
        }

        if (q2 != null) {
            total += mapAnswer(q2);
            count++;
        }

        if (q3 != null) {
            total += mapAnswer(q3);
            count++;
        }

        if (q4 != null) {
            total += mapAnswer(q4);
            count++;
        }

        if (count == 0) {
            return new MoriskyResult(
                    0,
                    "Выберите хотя бы один параметр"
            );
        }

        String interpretation;

        if (count < 4) {

            interpretation = String.format(
                    "Промежуточный результат (%d из 4): %d балл(ов)",
                    count,
                    total
            );

        } else {

            if (total == 0) {

                interpretation =
                        "Высокая приверженность лечению";

            } else if (total == 1) {

                interpretation =
                        "Недостаточная приверженность. " +
                                "Группа риска по развитию неприверженности";

            } else {

                interpretation =
                        "Неприверженность";

            }

        }

        return new MoriskyResult(
                total,
                interpretation
        );
    }

    private static int mapAnswer(String value) {

        switch (value) {

            case "Да":
                return 1;

            case "Нет":
                return 0;

            default:
                throw new IllegalArgumentException(
                        "Неизвестное значение: " + value
                );
        }
    }

}