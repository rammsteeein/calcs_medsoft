package com.example.demo1.controls.Caprini;

public class CapriniCalculator {

    public static CapriniResult calc(
            int age,
            boolean varicoseVeins,
            boolean bmiOver25,
            boolean minorSurgery,
            boolean sepsis,
            boolean lungDisease,
            boolean hormones,
            boolean pregnancyPostpartum,
            boolean priorMiscarriage,
            boolean acuteMI,
            boolean heartFailure,
            boolean bedRestNonSurgical,
            boolean IBD,
            boolean COPD,
            boolean legEdema,
            boolean arthroscopicSurgery,
            boolean malignancy,
            boolean laparoscopicSurgeryOver60min,
            boolean bedRestOver72h,
            boolean limbImmobilization,
            boolean centralLine,
            boolean majorSurgeryOver45min,
            boolean priorVTE,
            boolean familyVTE,
            boolean factorVLeiden,
            boolean prothrombin20210A,
            boolean hyperhomocysteinemia,
            boolean HIT,
            boolean anticardiolipin,
            boolean lupusAnticoagulant,
            boolean otherThrombophilia,
            boolean recentStroke,
            boolean jointReplacement,
            boolean fracture,
            boolean spinalTrauma,
            boolean multipleTrauma
    ) {
        int score = 0;

        // 1 балл
        if (age >= 41 && age <= 60) score += 1;
        if (varicoseVeins) score += 1;
        if (bmiOver25) score += 1;
        if (minorSurgery) score += 1;
        if (sepsis) score += 1;
        if (lungDisease) score += 1;
        if (hormones) score += 1;
        if (pregnancyPostpartum) score += 1;
        if (priorMiscarriage) score += 1;
        if (acuteMI) score += 1;
        if (heartFailure) score += 1;
        if (bedRestNonSurgical) score += 1;
        if (IBD) score += 1;
        if (COPD) score += 1;
        if (legEdema) score += 1;

        // 2 балла
        if (age >= 61 && age <= 74) score += 2;
        if (arthroscopicSurgery) score += 2;
        if (malignancy) score += 2;
        if (laparoscopicSurgeryOver60min) score += 2;
        if (bedRestOver72h) score += 2;
        if (limbImmobilization) score += 2;
        if (centralLine) score += 2;
        if (majorSurgeryOver45min) score += 2;

        // 3 балла
        if (age >= 75) score += 3;
        if (priorVTE) score += 3;
        if (familyVTE) score += 3;
        if (factorVLeiden) score += 3;
        if (prothrombin20210A) score += 3;
        if (hyperhomocysteinemia) score += 3;
        if (HIT) score += 3;
        if (anticardiolipin) score += 3;
        if (lupusAnticoagulant) score += 3;
        if (otherThrombophilia) score += 3;

        // 5 баллов
        if (recentStroke) score += 5;
        if (jointReplacement) score += 5;
        if (fracture) score += 5;
        if (spinalTrauma) score += 5;
        if (multipleTrauma) score += 5;

        String risk;
        if (score < 3) risk = "Низкий";
        else if (score <= 4) risk = "Умеренный";
        else if (score <= 6) risk = "Повышенный";
        else if (score <= 8) risk = "Высокий";
        else risk = "Очень высокий";

        return new CapriniResult(score, risk);
    }
}
