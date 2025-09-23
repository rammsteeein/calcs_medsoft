package com.example.demo1.controls.DASI;

public class DASICalculator {

    public static DASIResult calc(
            boolean selfCare,
            boolean walkIndoors,
            boolean walk1to2Blocks,
            boolean climbStairsOrHill,
            boolean runShortDistance,
            boolean lightHousework,
            boolean moderateHousework,
            boolean heavyHousework,
            boolean yardWork,
            boolean sexualActivity,
            boolean moderateRecreation,
            boolean strenuousSports
    ) {
        double DASI = 0.0;

        if (selfCare) DASI += 2.75;
        if (walkIndoors) DASI += 1.75;
        if (walk1to2Blocks) DASI += 2.75;
        if (climbStairsOrHill) DASI += 5.5;
        if (runShortDistance) DASI += 8.0;
        if (lightHousework) DASI += 2.7;
        if (moderateHousework) DASI += 3.5;
        if (heavyHousework) DASI += 8.0;
        if (yardWork) DASI += 4.5;
        if (sexualActivity) DASI += 5.25;
        if (moderateRecreation) DASI += 6.0;
        if (strenuousSports) DASI += 7.5;

        double VO2max = 0.43 * DASI + 9.6;
        double MET = VO2max / 3.5;

        String resultStr = String.format("DASI score: %.2f\nVO2max: %.2f мл/кг/мин\nMET: %.2f", DASI, VO2max, MET);
        return new DASIResult(resultStr);
    }
}
