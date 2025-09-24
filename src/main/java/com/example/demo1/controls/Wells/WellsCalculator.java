package com.example.demo1.controls.Wells;

public class WellsCalculator {

    /**
     * Шкала Wells — клиническая система для оценки вероятности тромбоэмболии лёгочной артерии (ТЭЛА).
     *
     * Критерии и баллы:
     * - Предшествующие ТЭЛА или тромбозы глубоких вен: 1.5 балла
     * - ЧСС > 100 уд/мин: 1.5 балла
     * - Недавние хирургические операции или иммобилизация в течение 4 недель: 1.5 балла
     * - Кровохарканье: 1 балл
     * - Активное злокачественное новообразование: 1 балл
     * - Клинические признаки тромбоза глубоких вен: 3 балла
     * - Альтернативный диагноз менее вероятен, чем ТЭЛА: 3 балла
     *
     * Интерпретация (трёхуровневая шкала):
     * - Низкая вероятность: 0–1 балл
     * - Средняя вероятность: 2–6 баллов
     * - Высокая вероятность: >7 баллов
     *
     * Интерпретация (двухуровневая шкала):
     * - ТЭЛА маловероятна: 0–4 балла
     * - ТЭЛА вероятна: ≥5 баллов
     *
     * Применение:
     * - Позволяет врачу быстро стратифицировать пациентов по риску ТЭЛА.
     * - Используется для принятия решения о дальнейших диагностических исследованиях (например, КТ-ангиографии).
     */


    public static WellsResult calc(
            boolean prevPEorDVT,
            boolean tachycardia,
            boolean surgeryOrImmobilization,
            boolean hemoptysis,
            boolean activeCancer,
            boolean clinicalDVT,
            boolean alternativeLessLikely
    ) {
        double score = 0;

        if (prevPEorDVT) score += 1.5;
        if (tachycardia) score += 1.5;
        if (surgeryOrImmobilization) score += 1.5;
        if (hemoptysis) score += 1;
        if (activeCancer) score += 1;
        if (clinicalDVT) score += 3;
        if (alternativeLessLikely) score += 3;

        String threeLevel = "";
        if (score >= 0 && score <= 1) threeLevel = "низкая";
        else if (score >= 2 && score <= 6) threeLevel = "средняя";
        else if (score > 7) threeLevel = "высокая";

        String twoLevel = "";
        if (score >= 0 && score <= 4) twoLevel = "ТЭЛА маловероятна";
        else if (score > 5) twoLevel = "ТЭЛА вероятна";

        String resultStr = String.format("Сумма баллов: %.1f\nТрехуровневая шкала: %s\nДвухуровневая шкала: %s",
                score, threeLevel, twoLevel);

        return new WellsResult(resultStr);
    }
}