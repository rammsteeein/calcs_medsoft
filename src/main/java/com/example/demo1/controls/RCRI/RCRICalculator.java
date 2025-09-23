package com.example.demo1.controls.RCRI;

public class RCRICalculator {

    public static RCRIResult calc(
            boolean highRiskSurgery,
            boolean ischemicHeartDisease,
            boolean heartFailure,
            boolean cerebrovascularDisease,
            boolean insulinTreatment,
            boolean highCreatinine
    ) {
        int score = 0;
        if (highRiskSurgery) score += 1;
        if (ischemicHeartDisease) score += 1;
        if (heartFailure) score += 1;
        if (cerebrovascularDisease) score += 1;
        if (insulinTreatment) score += 1;
        if (highCreatinine) score += 1;

        String risk;
        switch (score) {
            case 0:
                risk = "Риск большого кардиального события: 3,9% (2,8-5,4%)";
                break;
            case 1:
                risk = "Риск большого кардиального события: 6,0% (4,9-7,4%)";
                break;
            case 2:
                risk = "Риск большого кардиального события: 10,1% (8,1-12,6%)";
                break;
            default:
                risk = "Риск большого кардиального события: 15% (11,1-20,0%)";
                break;
        }

        String resultStr = String.format("RCRI score: %d\n%s", score, risk);
        return new RCRIResult(resultStr);
    }
}
