package com.example.demo1.controls.Mehran2;

public class Mehran2Calculator {

    /**
     * Шкала Mehran-2 (оценка риска контраст-индуцированной нефропатии (КИН) после ЧКВ).
     *
     * Критерии и баллы:
     * - Гипотония (САД < 80 мм рт.ст. ≥1 часа, требующая инотропной поддержки или баллонного насоса) → +5 баллов
     * - Внутриаортальный баллонный насос (ВАБК) → +5 баллов
     * - Хроническая сердечная недостаточность (NYHA III/IV) или отек легких в анамнезе → +5 баллов
     * - Возраст > 75 лет → +4 балла
     * - Анемия (Ht <39% у мужчин или <36% у женщин) → +3 балла
     * - Сахарный диабет → +3 балла
     * - Объем контрастного вещества: +1 балл за каждые 100 мл
     * - СКФ (мл/мин/1.73 м²):
     *      ≥60 → 0 баллов
     *      40–59 → +2 балла
     *      20–39 → +4 балла
     *      <20 → +6 баллов
     *
     * Интерпретация (риск пост-ЧКВ КИН):
     * - ≤ 5 баллов → 7.5% (КИН), 0.04% (диализ)
     * - 6–10 баллов → 14.0% (КИН), 0.12% (диализ)
     * - 11–15 баллов → 26.1% (КИН), 1.09% (диализ)
     * - ≥16 баллов → 57.3% (КИН), 12.6% (диализ)
     *
     * Применение:
     * Используется у пациентов, которым планируется проведение коронарографии / ЧКВ
     * для прогнозирования вероятности развития КИН и необходимости диализа.
     */

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
        score += (int) (contrastVolume / 100);

        if (gfr >= 60) score += 0;
        else if (gfr >= 40) score += 2;
        else if (gfr >= 20) score += 4;
        else score += 6;

        String interpretation;
        if (score <= 5) interpretation = "Низкий риск КИН (7.5%)";
        else if (score <= 10) interpretation = "Умеренный риск КИН (14.0%)";
        else if (score <= 15) interpretation = "Высокий риск КИН (26.1%)";
        else interpretation = "Очень высокий риск КИН (57.3%)";

        return new Mehran2Result(interpretation, score);
    }
}
