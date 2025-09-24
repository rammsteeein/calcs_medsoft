package com.example.demo1.controls.GuptaMICA;

import java.util.Map;

public class GuptaMICACalculator {

    public static GuptaMICAResult calc(double ageYears,
                                       String functionalStatus,
                                       String asaStatus,
                                       String creatinine,
                                       String surgeryType) {

        Map<String, Double> funcMap = GuptaMICAModel.functionalStatusMap;
        Map<String, Double> asaMap = GuptaMICAModel.asaStatusMap;
        Map<String, Double> creatMap = GuptaMICAModel.creatinineMap;
        Map<String, Double> surgMap = GuptaMICAModel.surgeryTypeMap;

        double funcVal = 0.0;
        if (functionalStatus != null) {
            funcVal = funcMap.getOrDefault(functionalStatus, 0.0);
        }

        double asaVal = 0.0;
        if (asaStatus != null) {
            asaVal = asaMap.getOrDefault(asaStatus, 0.0);
        }

        double creatVal = 0.0;
        if (creatinine != null) {
            creatVal = creatMap.getOrDefault(creatinine, 0.0);
        }

        double surgVal = 0.0;
        if (surgeryType != null) {
            surgVal = surgMap.getOrDefault(surgeryType, 0.0);
        }

        double x = -5.25
                + ageYears * 0.02
                + funcVal
                + asaVal
                + creatVal
                + surgVal;

        double risk = Math.exp(x) / (1 + Math.exp(x)) * 100;

        String riskCategory;
        if (risk < 0.14) riskCategory = "Низкий риск";
        else if (risk < 1.47) riskCategory = "Средний риск";
        else if (risk < 2.6) riskCategory = "Выше среднего";
        else if (risk < 7.69) riskCategory = "Высокий риск";
        else riskCategory = "Очень высокий риск";

        String resultText = String.format(
                "x = %.2f\nMICA риск = %.2f%%\nСтепень риска: %s",
                x, risk, riskCategory
        );

        return new GuptaMICAResult(resultText);
    }
}