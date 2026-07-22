package com.example.demo1.controls.Naranjo;


public class NaranjoCalculator {


    /**
     * Шкала Наранжо (Naranjo Adverse Drug Reaction Probability Scale)
     *
     * Оценка причинно-следственной связи между приемом
     * лекарственного средства и развитием побочной реакции.
     *
     *
     * Интерпретация:
     *
     * 9 и более  — Определенно
     * 5-8       — Вероятно
     * 1-4       — Возможно
     * 0         — Сомнительно
     */


    public static NaranjoResult calc(
            String q1,
            String q2,
            String q3,
            String q4,
            String q5,
            String q6,
            String q7,
            String q8,
            String q9,
            String q10
    ) {


        int total = 0;
        int count = 0;



        if (q1 != null) {
            total += mapQ1(q1);
            count++;
        }

        if (q2 != null) {
            total += mapQ2(q2);
            count++;
        }

        if (q3 != null) {
            total += mapQ3(q3);
            count++;
        }

        if (q4 != null) {
            total += mapQ4(q4);
            count++;
        }

        if (q5 != null) {
            total += mapQ5(q5);
            count++;
        }

        if (q6 != null) {
            total += mapQ6(q6);
            count++;
        }

        if (q7 != null) {
            total += mapQ7(q7);
            count++;
        }

        if (q8 != null) {
            total += mapQ8(q8);
            count++;
        }

        if (q9 != null) {
            total += mapQ9(q9);
            count++;
        }

        if (q10 != null) {
            total += mapQ10(q10);
            count++;
        }



        if (count == 0) {

            return new NaranjoResult(
                    0,
                    "Выберите хотя бы один параметр"
            );
        }



        String interpretation;



        if (count < 10) {

            interpretation = String.format(
                    "Промежуточный результат (%d из 10): %d балл(ов)",
                    count,
                    total
            );


        } else {


            if (total >= 9) {

                interpretation =
                        "Определенно: связь между препаратом и реакцией установлена";


            } else if (total >= 5) {

                interpretation =
                        "Вероятно: связь между препаратом и реакцией вероятна";


            } else if (total >= 1) {

                interpretation =
                        "Возможно: связь между препаратом и реакцией возможна";


            } else {

                interpretation =
                        "Сомнительно: связь между препаратом и реакцией не подтверждена";

            }
        }



        return new NaranjoResult(
                total,
                interpretation
        );
    }




    private static int mapQ1(String value) {

        switch (value) {

            case "Да":
                return 1;

            case "Нет":
            case "Не знаю":
                return 0;

            default:
                throw new IllegalArgumentException(value);
        }
    }




    private static int mapQ2(String value) {

        switch (value) {

            case "Да":
                return 2;

            case "Нет":
                return -1;

            case "Не знаю":
                return 0;

            default:
                throw new IllegalArgumentException(value);
        }
    }




    private static int mapQ3(String value) {

        return mapQ1(value);
    }




    private static int mapQ4(String value) {

        return mapQ2(value);
    }




    private static int mapQ5(String value) {

        switch (value) {

            case "Да":
                return -1;

            case "Нет":
                return 2;

            case "Не знаю":
                return 0;

            default:
                throw new IllegalArgumentException(value);
        }
    }





    private static int mapQ6(String value) {

        switch (value) {

            case "Да":
                return -1;

            case "Нет":
                return 1;

            case "Не знаю":
                return 0;

            default:
                throw new IllegalArgumentException(value);
        }
    }





    private static int mapQ7(String value) {

        return mapQ1(value);
    }





    private static int mapQ8(String value) {

        return mapQ1(value);
    }





    private static int mapQ9(String value) {

        return mapQ1(value);
    }





    private static int mapQ10(String value) {

        return mapQ1(value);
    }

}