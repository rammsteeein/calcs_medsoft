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

    private static final double[] RISKS = {7.5, 14.0, 26.1, 57.3};

    public static Mehran2Result calc(
            String oksType,
            String diabetesType,
            boolean lvefLow,
            boolean anemia,
            int age,
            int contrastVolume,
            boolean bleeding
    ) {
        int score = 0;

        switch (oksType) {
            case "STEMI": score += 8; break;
            case "NSTEMI": score += 4; break;
            case "Нестабильная стенокардия": score += 2; break;
        }

        if (diabetesType != null) {
            switch (diabetesType) {
                case "Инсулинозависимый": score += 2; break;
                case "Неинсулинозависимый": score += 1; break;
            }
        }

        if (lvefLow) score += 2;

        if (anemia) score += 1;

        if (age >= 75) score += 1;

        if (contrastVolume >= 100 && contrastVolume <= 199) score += 1;
        else if (contrastVolume >= 200 && contrastVolume <= 299) score += 3;
        else if (contrastVolume >= 300) score += 4;

        if (bleeding) score += 4;

        String interpretation;
        if (score <= 2) interpretation = "Низкий риск (<5%)";
        else if (score <= 7) interpretation = "Умеренный риск (5–15%)";
        else if (score <= 11) interpretation = "Высокий риск (15–30%)";
        else interpretation = "Очень высокий риск (>30%)";

        return new Mehran2Result(interpretation, score, 0);
    }
}