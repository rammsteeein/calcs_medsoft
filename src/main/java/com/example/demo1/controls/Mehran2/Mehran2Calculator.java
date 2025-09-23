package com.example.demo1.controls.Mehran2;

public class Mehran2Calculator {

    public static Mehran2Result calc(
            boolean hypotension,
            boolean balloonPump,
            boolean heartFailure,
            int age,
            boolean anemia,
            boolean diabetes,
            double contrastVolume,
            double gfr
    ) {
        int score = 0;

        if (hypotension) score += 5;
        if (balloonPump) score += 5;
        if (heartFailure) score += 5;
        if (age > 75) score += 4;
        if (anemia) score += 3;
        if (diabetes) score += 3;
        score += (int) (contrastVolume / 100); // 1 балл на 100 мл

        // СКФ
        if (gfr >= 60) score += 0;
        else if (gfr >= 40) score += 2;
        else if (gfr >= 20) score += 4;
        else score += 6;

        String interpretation;
        if (score <= 5) interpretation = "Низкий риск КИН (7.5%)";
        else if (score <= 10) interpretation = "Умеренный риск КИН (14.0%)";
        else if (score <= 15) interpretation = "Высокий риск КИН (26.1%)";
        else interpretation = "Очень высокий риск КИН (57.3%)";

        String resultStr = String.format("Mehran-2 score: %d\nИнтерпретация: %s", score, interpretation);

        return new Mehran2Result(resultStr);
    }
}
