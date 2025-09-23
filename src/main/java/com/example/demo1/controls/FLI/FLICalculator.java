package com.example.demo1.controls.FLI;

public class FLICalculator {

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