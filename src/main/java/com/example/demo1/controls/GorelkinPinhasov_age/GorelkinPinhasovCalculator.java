package com.example.demo1.controls.GorelkinPinhasov_age;

import com.example.demo1.common.enums.Gender;

public class GorelkinPinhasovCalculator {

    /**
     * Формулы Горелкина–Пинхасова
     *
     * Мужчины:
     * КСС = (ОТ × МТ) /
     *       (ОБ × Рост² × (17.2 + 0.31×РЛ + 0.0012×РЛ²))
     *
     * Женщины:
     * КСС = (ОТ × МТ) /
     *       (ОБ × Рост² × (14.7 + 0.26×РЛ + 0.001×РЛ²))
     *
     * где:
     * РЛ = КВ - 21 (мужчины)
     * РЛ = КВ - 18 (женщины)
     *
     * Биологический возраст:
     *
     * Мужчины:
     * БВ = КСС × (КВ - 21) + 21
     *
     * Женщины:
     * БВ = КСС × (КВ - 18) + 18
     */

    public static String calc(
            Gender gender,
            double age,
            double heightCm,
            double weight,
            double waist,
            double hips
    ) {

        if (gender == null) {
            return "Выберите пол";
        }

        if (age <= 0 ||
                heightCm <= 0 ||
                weight <= 0 ||
                waist <= 0 ||
                hips <= 0) {

            return "Заполните все поля";
        }

        double height = heightCm / 100.0;

        double rl;
        double kss;
        double biologicalAge;

        if (gender == Gender.MALE) {

            rl = age - 21;

            kss =
                    (waist * weight) /
                            (
                                    hips *
                                            Math.pow(height, 2) *
                                            (
                                                    17.2 +
                                                            0.31 * rl +
                                                            0.0012 * rl * rl
                                            )
                            );

            biologicalAge = kss * rl + 21;

        } else {

            rl = age - 18;

            kss =
                    (waist * weight) /
                            (
                                    hips *
                                            Math.pow(height, 2) *
                                            (
                                                    14.7 +
                                                            0.26 * rl +
                                                            0.001 * rl * rl
                                            )
                            );

            biologicalAge = kss * rl + 18;
        }

        String kssInterpretation;

        if (kss <= 0.95) {
            kssInterpretation = "Замедленное старение";
        } else if (kss >= 1.05) {
            kssInterpretation = "Ускоренное старение";
        } else {
            kssInterpretation = "Нормальный темп старения";
        }

        double diff = biologicalAge - age;

        String anthropoInterpretation;
        String recommendation;

        if (diff < -2) {

            anthropoInterpretation = "Благоприятный прогноз";
            recommendation =
                    "Профилактическое консультирование, поддержание текущего образа жизни.";

        } else if (Math.abs(diff) <= 2) {

            anthropoInterpretation = "Нормальный темп старения";
            recommendation =
                    "Профилактическое консультирование, контроль через 12 месяцев.";

        } else if (diff <= 4) {

            anthropoInterpretation = "Умеренное ускорение старения";
            recommendation =
                    "Рекомендуется дополнительное профилактическое консультирование.";

        } else {

            anthropoInterpretation = "Выраженное ускорение старения";
            recommendation =
                    "ОБЯЗАТЕЛЬНО рекомендуется проведение исследований второго этапа.";

        }

        return String.format(
                "Коэффициент скорости старения (КСС): %.3f\n\n"
                        + "Интерпретация КСС:\n%s\n\n"
                        + "Биологический возраст: %.1f лет\n"
                        + "Календарный возраст: %.1f лет\n"
                        + "Разница: %+.1f лет\n\n"
                        + "Интерпретация AnthropoAge:\n%s\n\n"
                        + "Рекомендации:\n%s",

                kss,
                kssInterpretation,
                biologicalAge,
                age,
                diff,
                anthropoInterpretation,
                recommendation
        );
    }

}