package com.example.demo1.controls.DLCN;

public class DLCNCalculator {

    /**
     * Критерии DLCN (Dutch Lipid Clinic Network) для диагностики
     * гетерозиготной семейной гиперхолестеринемии (FH).
     *
     * - Семейный анамнез:
     * - Ранние ССЗ атеросклеротического генеза у родственника первой степени
     *   (мужчины <55 лет, женщины <60 лет) или повышение ЛНП >95-го перцентиля
     *   + 1 балл
     * - Ксантомы сухожилия и/или липидная дуга роговицы у родственников первой степени
     *   или повышение ЛНП у детей <18 лет >95-го перцентиля
     *   + 2 балла
     *
     * - Индивидуальный анамнез:
     * - Ранняя ИБС (мужчины <55 лет, женщины <60 лет)
     *   + 2 балла
     * - Раннее поражение мозговых или периферических артерий
     *   (атеротромботический инсульт, ТИА, периферический атеросклероз ≥50%)
     *   + 1 балл
     *
     * - Физикальное обследование:
     * - Ксантомы сухожилия + 6 баллов
     * - Липидная дуга роговицы у пациентов <45 лет + 4 балла
     *
     * - Лабораторные данные (ЛНП, ммоль/л):
     * - ≥8.5 (≥328 мг/дл) + 8 баллов
     * - 6.5–8.4 (251–327 мг/дл) + 5 баллов
     * - 5.0–6.4 (193–250 мг/дл) + 3 балла
     * - 4.0–4.9 (155–192 мг/дл) + 1 балл
     *
     * - Генетическое обследование:
     * - Обнаружение мутаций LDLR, APOB, PCSK9 + 8 баллов
     *
     * - Интерпретация:
     * - ≥8 баллов - достоверный диагноз FH
     * - 6–8 баллов - вероятный диагноз
     * - 3–5 баллов - возможный диагноз
     * - ≤2 баллов - диагноз маловероятен
     */


    public static DLCNResult calc(
            boolean familyEarlyASCVDorHighLDL,
            boolean familyTendonXanthomasOrChildHighLDL,
            boolean personalEarlyCHD,
            boolean personalEarlyCerebrovascularDisease,
            boolean tendonXanthomas,
            boolean cornealArcusUnder45
    ) {
        int score = 0;

        if (familyEarlyASCVDorHighLDL) score += 1;
        if (familyTendonXanthomasOrChildHighLDL) score += 2;
        if (personalEarlyCHD) score += 2;
        if (personalEarlyCerebrovascularDisease) score += 1;
        if (tendonXanthomas) score += 6;
        if (cornealArcusUnder45) score += 4;

        String diagnosis;
        if (score >= 8) diagnosis = "Достоверный диагноз";
        else if (score >= 6) diagnosis = "Вероятный диагноз";
        else if (score >= 3) diagnosis = "Возможный диагноз";
        else diagnosis = "Диагноз маловероятен";

        String resultStr = String.format("Сумма баллов: %d\n%s", score, diagnosis);

        return new DLCNResult(resultStr);
    }
}
