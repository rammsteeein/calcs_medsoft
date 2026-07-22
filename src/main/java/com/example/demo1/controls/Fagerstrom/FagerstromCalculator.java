package com.example.demo1.controls.Fagerstrom;

public class FagerstromCalculator {

    /**
     * Тест Фагерстрема
     *
     * Оценка никотиновой зависимости.
     *
     * Вопросы:
     *
     * 1. Как скоро после пробуждения Вы выкуриваете первую сигарету?
     *
     * первые 5 минут       — 3 балла
     * 6-30 минут            — 2 балла
     * 30-60 минут           — 1 балл
     * через 1 час           — 0 баллов
     *
     *
     * 2. Сложно ли воздержаться от курения в местах, где курение запрещено?
     *
     * Да                   — 1 балл
     * Нет                   — 0 баллов
     *
     *
     * 3. От какой сигареты сложнее всего отказаться?
     *
     * Первая утром          — 1 балл
     * Все остальные         — 0 баллов
     *
     *
     * 4. Сколько сигарет в день?
     *
     * 10 и меньше            — 0 баллов
     * 11-20                  — 1 балл
     * 21-30                  — 2 балла
     * 31 и более             — 3 балла
     *
     *
     * 5. Курите чаще утром?
     *
     * Да                    — 1 балл
     * Нет                   — 0 баллов
     *
     *
     * 6. Курите во время болезни?
     *
     * Да                    — 1 балл
     * Нет                   — 0 баллов
     *
     *
     * Интерпретация:
     *
     * 0-2  — очень слабая зависимость
     * 3-4  — слабая зависимость
     * 5    — средняя зависимость
     * 6-7  — высокая зависимость
     * 8-10 — очень высокая зависимость
     */


    public static FagerstromResult calc(
            String q1,
            String q2,
            String q3,
            String q4,
            String q5,
            String q6
    ) {

        int total = 0;
        int count = 0;


        if (q1 != null) {
            total += mapQuestion1(q1);
            count++;
        }

        if (q2 != null) {
            total += mapYesNo(q2);
            count++;
        }

        if (q3 != null) {
            total += mapQuestion3(q3);
            count++;
        }

        if (q4 != null) {
            total += mapQuestion4(q4);
            count++;
        }

        if (q5 != null) {
            total += mapYesNo(q5);
            count++;
        }

        if (q6 != null) {
            total += mapYesNo(q6);
            count++;
        }


        if (count == 0) {

            return new FagerstromResult(
                    0,
                    "Выберите хотя бы один параметр"
            );
        }


        String interpretation;


        if (count < 6) {

            interpretation = String.format(
                    "Промежуточный результат (%d из 6): %d балл(ов)",
                    count,
                    total
            );

        } else {


            if (total <= 2) {

                interpretation =
                        "Очень слабая никотиновая зависимость";

            } else if (total <= 4) {

                interpretation =
                        "Слабая никотиновая зависимость";

            } else if (total == 5) {

                interpretation =
                        "Средняя никотиновая зависимость";

            } else if (total <= 7) {

                interpretation =
                        "Высокая никотиновая зависимость";

            } else {

                interpretation =
                        "Очень высокая никотиновая зависимость";
            }
        }


        return new FagerstromResult(
                total,
                interpretation
        );
    }



    private static int mapQuestion1(String value) {

        switch (value) {

            case "Первые 5 минут":
                return 3;

            case "6-30 минут":
                return 2;

            case "30-60 минут":
                return 1;

            case "Через 1 час":
                return 0;

            default:
                throw new IllegalArgumentException(
                        "Неизвестное значение: " + value
                );
        }
    }



    private static int mapQuestion3(String value) {

        switch (value) {

            case "Первая утром":
                return 1;

            case "Все остальные":
                return 0;

            default:
                throw new IllegalArgumentException(
                        "Неизвестное значение: " + value
                );
        }
    }



    private static int mapQuestion4(String value) {

        switch (value) {

            case "10 и меньше":
                return 0;

            case "11-20":
                return 1;

            case "21-30":
                return 2;

            case "31 и более":
                return 3;

            default:
                throw new IllegalArgumentException(
                        "Неизвестное значение: " + value
                );
        }
    }



    private static int mapYesNo(String value) {

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