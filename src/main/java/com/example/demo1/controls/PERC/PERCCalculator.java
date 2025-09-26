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

        if (age >= 50) score++;
        if (heartRate >= 100) score++;
        if (oxygen < 95.0) score++;
        if (unilateralLegEdema) score++;
        if (hemoptysis) score++;
        if (recentSurgeryOrTrauma) score++;
        if (surgeryWithin4Weeks) score++;
        if (prevPEorDVT) score++;
        if (hormoneUse) score++;

        String interpretation = (score == 0) ? "Низкий риск ПЭ — можно отказаться от дополнительных тестов"
                : "Не исключён риск ПЭ — требуется дальнейшее обследование";

        return new PERCResult(score, interpretation);
    }
}