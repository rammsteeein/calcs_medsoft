package com.example.demo1.controls.PERC;

public class PERCCalculator {

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
