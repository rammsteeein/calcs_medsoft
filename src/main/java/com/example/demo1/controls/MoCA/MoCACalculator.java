package com.example.demo1.controls.MoCA;

public class MoCACalculator {

    /**
     * Монреальская шкала когнитивной оценки (MoCA)
     * Montreal Cognitive Assessment
     * Используется для скрининга лёгких и умеренных
     * когнитивных нарушений.
     * Оцениваемые функции:
     * - внимание и концентрация
     * - память
     * - исполнительные функции
     * - речь
     * - абстрактное мышление
     * - зрительно-пространственные навыки
     * - ориентировка
     * Разделы и баллы:
     * 1. Оптико-пространственная деятельность /
     * исполнительные функции — 5 баллов:
     * - Чередование (trail making) — 1 балл
     * - Копирование куба — 1 балл
     * - Часы:
     *   • контур — 1 балл
     *   • цифры — 1 балл
     *   • стрелки — 1 балл
     * 2. Называние — 3 балла:
     * - Лев — 1 балл
     * - Носорог — 1 балл
     * - Верблюд — 1 балл
     * 3. Внимание — 6 баллов:
     * - Повтор цифр вперёд — 1 балл
     * - Повтор цифр назад — 1 балл
     * - Реакция на букву «А» — 1 балл
     * - Последовательное вычитание по 7:
     *   • 1 правильный ответ — 1 балл
     *   • 2–3 правильных ответа — 2 балла
     *   • 4–5 правильных ответов — 3 балла
     * 4. Речь — 3 балла:
     * - Повторение первого предложения — 1 балл
     * - Повторение второго предложения — 1 балл
     * - Беглость речи (≥11 слов на букву К) — 1 балл
     * 5. Абстрактное мышление — 2 балла:
     * - Сходство поезд / велосипед — 1 балл
     * - Сходство часы / линейка — 1 балл
     * 6. Отсроченное воспроизведение — 5 баллов:
     * - ЛИЦО — 1 балл
     * - ВЕЛЬВЕТ — 1 балл
     * - ЦЕРКОВЬ — 1 балл
     * - МАРГАРИТКА — 1 балл
     * - КРАСНЫЙ — 1 балл
     * 7. Ориентировка — 6 баллов:
     * - Число — 1 балл
     * - Месяц — 1 балл
     * - Год — 1 балл
     * - День недели — 1 балл
     * - Место — 1 балл
     * - Город — 1 балл
     * Дополнительно:
     * +1 балл при образовании ≤ 12 лет
     * Максимальный балл: 30
     * Интерпретация:
     * 26–30 — норма
     * 18–25 — лёгкие когнитивные нарушения
     * 10–17 — умеренные когнитивные нарушения
     * <10 — выраженные когнитивные нарушения
     */

    public static MoCAResult calc(

            Boolean trailMaking,
            Boolean cube,

            Boolean clockContour,
            Boolean clockNumbers,
            Boolean clockHands,

            Boolean lion,
            Boolean rhino,
            Boolean camel,

            Boolean attentionForward,
            Boolean attentionBackward,
            Boolean attentionLetterA,

            Boolean subtract1,
            Boolean subtract2,
            Boolean subtract3,
            Boolean subtract4,
            Boolean subtract5,

            Boolean sentence1,
            Boolean sentence2,

            Boolean fluency,

            Boolean abstraction1,
            Boolean abstraction2,

            Boolean recallFace,
            Boolean recallVelvet,
            Boolean recallChurch,
            Boolean recallDaisy,
            Boolean recallRed,

            Boolean orientationDate,
            Boolean orientationMonth,
            Boolean orientationYear,
            Boolean orientationDay,
            Boolean orientationPlace,
            Boolean orientationCity,

            Boolean lowEducation
    ) {

        int total = 0;

        if (trailMaking != null && trailMaking) total++;
        if (cube != null && cube) total++;

        if (clockContour != null && clockContour) total++;
        if (clockNumbers != null && clockNumbers) total++;
        if (clockHands != null && clockHands) total++;

        if (lion != null && lion) total++;
        if (rhino != null && rhino) total++;
        if (camel != null && camel) total++;

        if (attentionForward != null && attentionForward) total++;
        if (attentionBackward != null && attentionBackward) total++;
        if (attentionLetterA != null && attentionLetterA) total++;

        int subtractionCorrect = 0;

        if (subtract1 != null && subtract1) subtractionCorrect++;
        if (subtract2 != null && subtract2) subtractionCorrect++;
        if (subtract3 != null && subtract3) subtractionCorrect++;
        if (subtract4 != null && subtract4) subtractionCorrect++;
        if (subtract5 != null && subtract5) subtractionCorrect++;

        if (subtractionCorrect == 1) {
            total += 1;
        }
        else if (subtractionCorrect >= 2 && subtractionCorrect <= 3) {
            total += 2;
        }
        else if (subtractionCorrect >= 4) {
            total += 3;
        }

        if (sentence1 != null && sentence1) total++;
        if (sentence2 != null && sentence2) total++;

        if (fluency != null && fluency) total++;

        if (abstraction1 != null && abstraction1) total++;
        if (abstraction2 != null && abstraction2) total++;

        if (recallFace != null && recallFace) total++;
        if (recallVelvet != null && recallVelvet) total++;
        if (recallChurch != null && recallChurch) total++;
        if (recallDaisy != null && recallDaisy) total++;
        if (recallRed != null && recallRed) total++;

        if (orientationDate != null && orientationDate) total++;
        if (orientationMonth != null && orientationMonth) total++;
        if (orientationYear != null && orientationYear) total++;
        if (orientationDay != null && orientationDay) total++;
        if (orientationPlace != null && orientationPlace) total++;
        if (orientationCity != null && orientationCity) total++;

        if (lowEducation != null && lowEducation) {
            total += 1;
        }

        if (total > 30) {
            total = 30;
        }

        return new MoCAResult(total, interpret(total));
    }

    private static String interpret(int score) {

        if (score >= 26) {
            return "Норма";
        }
        else if (score >= 18) {
            return "Легкие когнитивные нарушения";
        }
        else if (score >= 10) {
            return "Умеренные когнитивные нарушения";
        }
        else {
            return "Тяжелые когнитивные нарушения";
        }
    }
}