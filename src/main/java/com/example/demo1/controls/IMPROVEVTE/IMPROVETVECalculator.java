package com.example.demo1.controls.IMPROVEVTE;

public class IMPROVETVECalculator {
    /**
     * Класс для расчета риска венозной тромбоэмболии (ВТЭО) по шкале IMPROVE VTE.
     * <p>
     * IMPROVE VTE (International Medical Prevention Registry on Venous Thromboembolism) — это клиническая шкала,
     * которая позволяет оценить 3-месячный риск ВТЭО у госпитализированных пациентов на основе факторов риска.
     * Каждый фактор риска имеет определенное количество баллов:
     * </p>
     * <ul>
     *     <li>ВТЭО в анамнезе — 3 балла</li>
     *     <li>Известная тромбофилия (дефицит протеина C/S, фактор V Лейден, волчаночный антикоагулянт) — 2 балла</li>
     *     <li>Парез или паралич нижних конечностей — 2 балла</li>
     *     <li>Активный рак — 2 балла</li>
     *     <li>Иммобилизация ≥7 дней (нахождение в кровати или на стуле с выходом в туалет или без него) — 1 балл</li>
     *     <li>Пребывание в отделении интенсивной терапии/неотложной кардиологии — 1 балл</li>
     *     <li>Возраст > 60 лет — 1 балл</li>
     * </ul>
     * <p>
     * Сумма баллов позволяет классифицировать риск ВТЭО и приблизительно определить 3-месячную вероятность тромбоза:
     * <ul>
     *     <li>0 баллов — 0,4%</li>
     *     <li>1 балл — 0,6%</li>
     *     <li>2 балла — 1%</li>
     *     <li>3 балла — 1,7%</li>
     *     <li>4 балла — 2,9%</li>
     *     <li>5–10 баллов — 7,2%</li>
     */
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

        String risk;
        if (score == 0) risk = "0,4%";
        else if (score == 1) risk = "0,6%";
        else if (score == 2) risk = "1%";
        else if (score == 3) risk = "1,7%";
        else if (score == 4) risk = "2,9%";
        else risk = "7,2%";

        return new IMPROVEResult(score, risk);
    }
}

