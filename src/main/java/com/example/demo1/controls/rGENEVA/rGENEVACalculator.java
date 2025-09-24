package com.example.demo1.controls.rGENEVA;

public class rGENEVACalculator {

    /**
     * Шкала rGENEVA (упрощённая Женева) —
     * клиническая система для оценки вероятности тромбоэмболии лёгочной артерии (ТЭЛА).
     *
     * Критерии и баллы:
     * - Предшествующие эпизоды ТЭЛА или тромбоза глубоких вен (ТГВ): 3 балла
     * - Частота сердечных сокращений (ЧСС):
     *      • 75–94 уд/мин: 3 балла
     *      • ≥95 уд/мин: 5 баллов
     * - Недавние операции или переломы (в течение 1 месяца): 2 балла
     * - Кровохарканье: 2 балла
     * - Активное злокачественное новообразование: 3 балла
     * - Боль в одной нижней конечности: 3 балла
     * - Боль при пальпации глубоких вен и односторонний отёк: 4 балла
     * - Возраст >65 лет: 1 балл
     *
     * Интерпретация:
     * 1. Трёхуровневая шкала:
     *    - Низкая вероятность: 0–1 балл
     *    - Средняя вероятность: 2–6 баллов
     *    - Высокая вероятность: ≥7 баллов
     *
     * 2. Двухуровневая шкала:
     *    - ТЭЛА маловероятна: 0–4 балла
     *    - ТЭЛА вероятна: ≥5 баллов
     *
     * Примечания:
     * - Шкала помогает врачу быстро стратифицировать пациентов по риску ТЭЛА.
     * - Используется для принятия решения о дальнейших диагностических исследованиях (например, КТ-ангиографии).
     */

    public static rGENEVAResult calc(
            boolean prevPEorDVT,
            int heartRate,
            boolean surgeryOrFracture,
            boolean hemoptysis,
            boolean activeCancer,
            boolean legPain,
            boolean painAndSwelling,
            int age
    ) {
        int score = 0;

        if (prevPEorDVT) score += 3;

        if (heartRate >= 75 && heartRate <= 94) score += 3;
        else if (heartRate >= 95) score += 5;

        if (surgeryOrFracture) score += 2;
        if (hemoptysis) score += 2;
        if (activeCancer) score += 3;
        if (legPain) score += 3;
        if (painAndSwelling) score += 4;
        if (age > 65) score += 1;

        String threeLevel;
        if (score >= 0 && score <= 1) threeLevel = "низкая";
        else if (score >= 2 && score <= 6) threeLevel = "средняя";
        else threeLevel = "высокая";

        String twoLevel;
        if (score >= 0 && score <= 4) twoLevel = "ТЭЛА маловероятна";
        else twoLevel = "ТЭЛА вероятна";

        String resultStr = String.format("Сумма баллов: %d\nТрехуровневая шкала: %s\nДвухуровневая шкала: %s",
                score, threeLevel, twoLevel);

        return new rGENEVAResult(resultStr);
    }
}