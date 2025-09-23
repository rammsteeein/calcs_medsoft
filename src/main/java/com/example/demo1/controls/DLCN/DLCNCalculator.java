package com.example.demo1.controls.DLCN;

public class DLCNCalculator {

    public static DLCNResult calc(
            boolean familyEarlyASCVDorHighLDL,  // 1 балл
            boolean familyTendonXanthomasOrChildHighLDL, // 2 балла
            boolean personalEarlyCHD, // 2 балла
            boolean personalEarlyCerebrovascularDisease, // 1 балл
            boolean tendonXanthomas, // 6 баллов
            boolean cornealArcusUnder45 // 4 балла
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
