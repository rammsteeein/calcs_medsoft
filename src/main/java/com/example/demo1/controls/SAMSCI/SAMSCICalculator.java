package com.example.demo1.controls.SAMSCI;

public class SAMSCICalculator {

    public static SAMSCIResult calc(
            int muscleLocation,
            int symptomTiming,
            int statinDiscontinuation,
            int statinRechallenge
    ) {
        int score = muscleLocation + symptomTiming + statinDiscontinuation + statinRechallenge;

        String likelihood = "";
        if (score >= 9 && score <= 11) likelihood = "Достоверный";
        else if (score >= 7 && score <= 8) likelihood = "Возможен";
        else if (score < 7) likelihood = "Маловероятный";

        String resultStr = String.format("Сумма баллов: %d\nКлинический индекс: %s", score, likelihood);

        return new SAMSCIResult(resultStr);
    }
}