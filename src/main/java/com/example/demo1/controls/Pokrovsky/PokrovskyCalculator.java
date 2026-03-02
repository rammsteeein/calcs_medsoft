package com.example.demo1.controls.Pokrovsky;

public final class PokrovskyCalculator {
    /**
     * Оценка тяжести дегидратации по В.И. Покровскому.
     *
     * Назначение:
     * Клиническая шкала используется для определения степени обезвоживания
     * у пациентов с острыми кишечными инфекциями, рвотой, диареей,
     * токсикоинфекциями и другими состояниями с потерей жидкости.
     *
     * Принцип оценки:
     * Степень дегидратации определяется по клиническим признакам.
     * Итоговая тяжесть соответствует наиболее выраженному симптому
     * (используется принцип максимальной степени).
     *
     * Степени обезвоживания:
     * 1 степень — 1–3% потери массы тела (стертая / лёгкая)
     * 2 степень — 4–6% (средней тяжести)
     * 3 степень — 7–9% (тяжёлая)
     * 4 степень — ≥10% (очень тяжёлая)
     *
     * Критерии оценки:
     *
     * Стул:
     *  - до 10 раз → 1 степень
     *  - до 20 раз → 2 степень
     *  - более 20 раз → 3 степень
     *  - без счёта → 4 степень
     *
     * Рвота:
     *  - до 5 раз → 1 степень
     *  - до 10 раз → 2 степень
     *  - до 20 раз → 3 степень
     *  - многократная (неукротимая) → 4 степень
     *
     * Жажда:
     *  - слабо выражена → 1 степень
     *  - умеренно выраженная → 2 степень
     *  - резко выраженная → 3 степень
     *  - неутолимая / не может пить → 4 степень
     *
     * Диурез:
     *  - норма → 1 степень
     *  - снижен → 2 степень
     *  - олигурия → 3 степень
     *  - анурия → 4 степень
     *
     * Судороги:
     *  - отсутствуют → 1 степень
     *  - икроножные, кратковременные → 2 степень
     *  - продолжительные, болезненные → 3 степень
     *  - генерализованные клонические → 4 степень
     *
     * Состояние:
     *  - удовлетворительное → 1 степень
     *  - средней тяжести → 2 степень
     *  - тяжёлое → 3 степень
     *  - очень тяжёлое → 4 степень
     *
     * Глазные яблоки:
     *  - норма → 1 степень
     *  - запавшие → 3 степень
     *  - резко запавшие → 4 степень
     *
     * Слизистые оболочки:
     *  - влажные → 1 степень
     *  - суховатые → 2 степень
     *  - сухие → 3 степень
     *  - сухие, резко гиперемированы → 4 степень
     *
     * Дыхание:
     *  - норма → 1 степень
     *  - умеренное тахипноэ → 3 степень
     *  - тахипноэ → 4 степень
     *
     * Цианоз:
     *  - отсутствует → 1 степень
     *  - носогубный треугольник → 2 степень
     *  - акроцианоз → 3 степень
     *  - выраженный, диффузный → 4 степень
     *
     * Тургор кожи:
     *  - норма → 1 степень
     *  - сниженный (складка >1 сек) → 3 степень
     *  - резко сниженный (складка >2 сек) → 4 степень
     *
     * Пульс:
     *  - норма → 1 степень
     *  - до 100 в мин → 2 степень
     *  - до 120 в мин → 3 степень
     *  - >120 в мин, нитевидный → 4 степень
     *
     * Систолическое АД:
     *  - норма → 1 степень
     *  - до 100 мм рт.ст. → 2 степень
     *  - 60–100 мм рт.ст. → 3 степень
     *  - менее 60 мм рт.ст. → 4 степень
     *
     * Голос:
     *  - сохранён → 1 степень
     *  - осиплость → 3 степень
     *  - афония → 4 степень
     *
     * Клиническая интерпретация:
     * 1 степень — возможна пероральная регидратация.
     * 2 степень — показана активная регидратационная терапия.
     * 3 степень — показана госпитализация.
     * 4 степень — требуется неотложная помощь и интенсивная терапия.
     *
     * Применение:
     * Используется врачами инфекционного профиля, терапевтами,
     * педиатрами и в отделениях неотложной помощи
     * для быстрой оценки степени потери жидкости.
     */

    private PokrovskyCalculator() {}

    public static PokrovskyResult calc(
            String stool,
            String vomiting,
            String thirst,
            String diuresis,
            String cramps,
            String condition,
            String eyes,
            String mucous,
            String breathing,
            String cyanosis,
            String turgor,
            String pulse,
            String pressure,
            String voice
    ) {

        int max = 0;

        max = max(max, mapStool(stool));
        max = max(max, mapVomiting(vomiting));
        max = max(max, mapThirst(thirst));
        max = max(max, mapDiuresis(diuresis));
        max = max(max, mapCramps(cramps));
        max = max(max, mapCondition(condition));
        max = max(max, mapEyes(eyes));
        max = max(max, mapMucous(mucous));
        max = max(max, mapBreathing(breathing));
        max = max(max, mapCyanosis(cyanosis));
        max = max(max, mapTurgor(turgor));
        max = max(max, mapPulse(pulse));
        max = max(max, mapPressure(pressure));
        max = max(max, mapVoice(voice));

        if (max == 0) {
            return new PokrovskyResult(0, "Выберите хотя бы один параметр");
        }

        String text;

        switch (max) {
            case 1:
                text = "1–3% потери массы тела\nЛёгкая дегидратация";
                break;
            case 2:
                text = "4–6% потери массы тела\nДегидратация средней тяжести";
                break;
            case 3:
                text = "7–9% потери массы тела\nТяжёлая дегидратация\nРекомендована госпитализация";
                break;
            case 4:
                text = "≥10% потери массы тела\nОчень тяжёлая дегидратация\nТребуется срочная госпитализация";
                break;
            default:
                text = "Ошибка расчёта";
        }

        return new PokrovskyResult(max, text);
    }

    private static int max(int a, int b) { return Math.max(a, b); }

    private static int mapStool(String v) {
        if (v == null) return 0;
        switch (v) {
            case "До 10 раз": return 1;
            case "До 20 раз": return 2;
            case "Более 20 раз": return 3;
            case "Без счёта": return 4;
        }
        return 0;
    }

    private static int mapVomiting(String v) {
        if (v == null) return 0;
        switch (v) {
            case "До 5 раз": return 1;
            case "До 10 раз": return 2;
            case "До 20 раз": return 3;
            case "Неукротимая": return 4;
        }
        return 0;
    }

    private static int mapThirst(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Слабо": return 1;
            case "Умеренно выраженная": return 2;
            case "Резко выраженная": return 3;
            case "Неутолимая / не может пить": return 4;
        }
        return 0;
    }

    private static int mapDiuresis(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Норма": return 1;
            case "Снижен": return 2;
            case "Олигурия": return 3;
            case "Анурия": return 4;
        }
        return 0;
    }

    private static int mapCramps(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Нет": return 1;
            case "Икроножные кратковременные": return 2;
            case "Продолжительные болезненные": return 3;
            case "Генерализованные клонические": return 4;
        }
        return 0;
    }

    private static int mapCondition(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Удовлетворительное": return 1;
            case "Средней тяжести": return 2;
            case "Тяжёлое": return 3;
            case "Очень тяжёлое": return 4;
        }
        return 0;
    }

    private static int mapEyes(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Норма": return 1;
            case "Запавшие": return 3;
            case "Резко запавшие": return 4;
        }
        return 0;
    }

    private static int mapMucous(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Влажные": return 1;
            case "Суховатые": return 2;
            case "Сухие": return 3;
            case "Сухие резко гиперемированы": return 4;
        }
        return 0;
    }

    private static int mapBreathing(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Норма": return 1;
            case "Умеренное тахипноэ": return 3;
            case "Тахипноэ": return 4;
        }
        return 0;
    }

    private static int mapCyanosis(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Нет": return 1;
            case "Носогубный треугольник": return 2;
            case "Акроцианоз": return 3;
            case "Диффузный": return 4;
        }
        return 0;
    }

    private static int mapTurgor(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Норма": return 1;
            case "Складка >1 сек": return 3;
            case "Складка >2 сек": return 4;
        }
        return 0;
    }

    private static int mapPulse(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Норма": return 1;
            case "До 100": return 2;
            case "До 120": return 3;
            case "Более 120, нитевидный": return 4;
        }
        return 0;
    }

    private static int mapPressure(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Норма": return 1;
            case "До 100": return 2;
            case "60–100": return 3;
            case "Менее 60": return 4;
        }
        return 0;
    }

    private static int mapVoice(String v) {
        if (v == null) return 0;
        switch (v) {
            case "Сохранено": return 1;
            case "Осиплость": return 3;
            case "Афония": return 4;
        }
        return 0;
    }
}