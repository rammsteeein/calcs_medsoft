package com.example.demo1.controls.FLI;

public class FLICalculator {

    /**
     * Расчет Fatty Liver Index (FLI) для оценки вероятности жировой болезни печени
     *
     * Формула:
     * FLI = [ e^(0.953 * ln(триглицериды) + 0.139 * ИМТ + 0.718 * ln(ГГТП) + 0.053 * окружность талии - 15.745) ] /
     *       [ 1 + e^(0.953 * ln(триглицериды) + 0.139 * ИМТ + 0.718 * ln(ГГТП) + 0.053 * окружность талии - 15.745) ] × 100
     *
     * Где:
     * - триглицериды: уровень триглицеридов в крови (ммоль/л)
     * - ИМТ: индекс массы тела (кг/м²)
     * - ГГТП (GGT): гамма-глутамилтрансфераза в крови
     * - окружность талии: в сантиметрах
     * - ln(): натуральный логарифм
     *
     * Примечания:
     * - Результат FLI выражен в процентах (0–100%).
     * - FLI <30: низкая вероятность стеатоза печени
     * - FLI 30–59: промежуточная вероятность
     * - FLI ≥60: высокая вероятность жировой болезни печени
     * - Используется как неинвазивный скрининг для выявления стеатоза печени.
     */

    public static FLIResult calc(double triglycerides, double bmi, double ggtp, double waist) {
        if (triglycerides <= 0 || ggtp <= 0 || bmi <= 0 || waist <= 0) {
            return new FLIResult("Ошибка: все значения должны быть положительными");
        }

        double L = 0.953 * Math.log(triglycerides) +
                0.139 * bmi +
                0.718 * Math.log(ggtp) +
                0.053 * waist -
                15.745;

        double fli = Math.exp(L) / (1 + Math.exp(L)) * 100;

        String interpretation;
        if (fli < 30) {
            interpretation = "Низкий риск стеатоза печени";
        } else if (fli >= 30 && fli < 60) {
            interpretation = "Промежуточный риск стеатоза печени";
        } else {
            interpretation = "Высокий риск стеатоза печени";
        }

        String result = String.format("FLI: %.2f\nИнтерпретация: %s", fli, interpretation);
        return new FLIResult(result);
    }
}