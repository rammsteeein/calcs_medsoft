package com.example.demo1.controls.IMPROVEVTE;

public class IMPROVETVECalculator {

    public static IMPROVEResult calc(
            boolean priorVTE,
            boolean knownThrombophilia,
            boolean lowerLimbParalysis,
            boolean activeCancer,
            boolean immobilization7Days,
            boolean ICUstay,
            int age
    ) {
        int score = 0;

        if (priorVTE) score += 3;
        if (knownThrombophilia) score += 2;
        if (lowerLimbParalysis) score += 2;
        if (activeCancer) score += 2;
        if (immobilization7Days) score += 1;
        if (ICUstay) score += 1;
        if (age > 60) score += 1;

        String riskCategory;
        if (score == 0) riskCategory = "Низкий риск ВТЭО: 0,4%";
        else if (score == 1) riskCategory = "Риск 0,6%";
        else if (score == 2) riskCategory = "Риск 1%";
        else if (score == 3) riskCategory = "Риск 1,7%";
        else if (score == 4) riskCategory = "Риск 2,9%";
        else riskCategory = "Риск 7,2%";

        String resultStr = String.format("Сумма баллов: %d\n%s", score, riskCategory);

        return new IMPROVEResult(resultStr);
    }
}
