package com.example.demo1.controls.PERC;

public class PERCCalculator {

    /**
     * PERC (Pulmonary Embolism Rule-out Criteria) —
     * клиническое правило, позволяющее исключить вероятность ТЭЛА
     * у пациентов с низким риском без проведения дополнительных исследований.
     *
     * Каждый критерий = 1 балл:
     * - Возраст ≥50 лет
     * - ЧСС ≥100 уд/мин
     * - Сатурация O₂ при комнатном воздухе <95%
     * - Односторонний отек ноги
     * - Кровохарканье
     * - Недавняя операция или травма (≤4 недель назад, требующая общей анестезии)
     * - Предшествующая ТЭЛА или тромбоз глубоких вен
     * - Использование гормональной терапии (оральные контрацептивы,
     *   заместительная терапия или эстрогены у мужчин/женщин)
     *
     * Интерпретация:
     * - При низкой клинической вероятности ТЭЛА и 0 баллов по шкале PERC —
     *   вероятность ТЭЛА считается крайне низкой, можно отказаться от дополнительных тестов.
     * - При ≥1 балле — требуется дальнейшее обследование (D-димер, КТ-ангиография).
     *
     * Важно: шкала применяется только у пациентов с изначально низким риском ТЭЛА!
     */

    public static PERCResult calc(
            int age,
            int heartRate,
            double oxygen,
            boolean unilateralLegEdema,
            boolean hemoptysis,
            boolean recentSurgeryOrTrauma,
            boolean surgeryWithin4Weeks,
            boolean prevPEorDVT,
            boolean hormoneUse
    ) {
        int score = 0;

        if (age >= 50) score += 1;
        if (heartRate >= 100) score += 1;
        if (oxygen < 95.0) score += 1;
        if (unilateralLegEdema) score += 1;
        if (hemoptysis) score += 1;
        if (recentSurgeryOrTrauma) score += 1;
        if (surgeryWithin4Weeks) score += 1;
        if (prevPEorDVT) score += 1;
        if (hormoneUse) score += 1;

        String riskStr = (score == 0) ? "Низкий риск ПЭ" : "Не исключен риск ПЭ";

        String resultStr = String.format("Сумма баллов: %d\n%s", score, riskStr);

        return new PERCResult(resultStr);
    }
}
