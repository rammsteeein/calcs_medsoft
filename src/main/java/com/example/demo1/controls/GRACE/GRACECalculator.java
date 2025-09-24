package com.example.demo1.controls.GRACE;

public class GRACECalculator {

    /**
     * Шкала GRACE для оценки риска смерти у пациентов с острым коронарным синдромом (ОКС)
     *
     * Основные факторы и начисление баллов:
     * 1. Возраст (годы):
     *    - ≤30: 0
     *    - 30–39: 8
     *    - 40–49: 25
     *    - 50–59: 41
     *    - 60–69: 58
     *    - 70–79: 75
     *    - 80–89: 91
     *    - ≥90: 100
     *
     * 2. Частота сердечных сокращений (ЧСС, уд/мин):
     *    - ≤50: 0
     *    - 50–69: 3
     *    - 70–89: 9
     *    - 90–109: 15
     *    - 110–149: 24
     *    - 150–199: 38
     *    - ≥200: 46
     *
     * 3. Систолическое АД (мм рт. ст.):
     *    - ≤80: 58
     *    - 80–99: 53
     *    - 100–119: 43
     *    - 120–139: 34
     *    - 140–159: 24
     *    - 160–199: 10
     *    - ≥200: 0
     *
     * 4. Класс по Killip:
     *    - I: 0
     *    - II: 20
     *    - III: 39
     *    - IV: 59
     *
     * 5. Уровень креатинина (мг/дл):
     *    - 0–0,39: 1
     *    - 0,40–0,79: 4
     *    - 0,80–1,19: 7
     *    - 1,20–1,59: 10
     *    - 1,60–1,99: 13
     *    - 2,0–3,99: 21
     *    - ≥4,0: 28
     *
     * 6. Другие факторы:
     *    - Остановка сердца при поступлении: 39
     *    - Смещения сегмента ST, инверсия зубца T: 28
     *    - Повышенный уровень маркеров некроза миокарда: 14
     *
     * Интерпретация суммарного балла:
     * - Низкий риск (<1%): ≤108
     * - Умеренный риск (1–3%): 109–140
     * - Высокий риск (>3%): ≥141
     *
     * Примечания:
     * - Баллы по каждому параметру суммируются для получения итогового риска.
     * - Используется для прогнозирования смертности в стационаре при ОКС.
     * - Рекомендуется применять совместно с клиническими данными для принятия решений о лечении.
     */

    public static GRACEResult calc(int agePoints, int hrPoints, int sbpPoints, int killipPoints,
                                   int creatininePoints, int otherPoints) {
        int total = agePoints + hrPoints + sbpPoints + killipPoints + creatininePoints + otherPoints;

        String risk;
        if (total <= 108) {
            risk = "Низкий (<1%)";
        } else if (total <= 140) {
            risk = "Умеренный (1–3%)";
        } else {
            risk = "Высокий (>3%)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Баллы:\n");
        sb.append("Возраст: ").append(agePoints).append("\n");
        sb.append("ЧСС: ").append(hrPoints).append("\n");
        sb.append("Систолическое АД: ").append(sbpPoints).append("\n");
        sb.append("Класс по Киллип: ").append(killipPoints).append("\n");
        sb.append("Креатинин: ").append(creatininePoints).append("\n");
        sb.append("Другие факторы: ").append(otherPoints).append("\n");
        sb.append("Сумма баллов: ").append(total).append("\n");
        sb.append("Риск смерти в стационаре: ").append(risk);

        return new GRACEResult(sb.toString());
    }
}