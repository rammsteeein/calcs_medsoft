package com.example.demo1.controls.Pursuit;

import com.example.demo1.controls.Pursuit.PursuitResult;

public class PursuitCalculator {

    public static PursuitResult calc(int age, boolean isMale, boolean hasAngina, boolean hasHeartFailure) {
        int score = 0;

        if (age >= 50 && age <= 59) score += 8;
        else if (age >= 60 && age <= 69) score += 9;
        else if (age >= 70 && age <= 79) score += 11;
        else if (age >= 80) score += 12;

        if (isMale) score += 1;
        if (hasAngina) score += 2;
        if (hasHeartFailure) score += 2;

        String riskCategory;
        if (score <= 12) riskCategory = "Низкий риск (<10%)";
        else if (score <= 14) riskCategory = "Средний риск (10-19%)";
        else riskCategory = "Высокий риск (>19%)";

        String result = String.format("Сумма баллов: %d, Категория: %s", score, riskCategory);
        return new PursuitResult(result);
    }
}