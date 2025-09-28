package com.example.demo1.controls.DASI;

public class DASIResult {
    private final double DASI;
    private final double VO2max;
    private final double MET;
    private final String calculation;

    public DASIResult(double DASI, double VO2max, double MET, String calculation) {
        this.DASI = DASI;
        this.VO2max = VO2max;
        this.MET = MET;
        this.calculation = calculation;
    }

    public double getDASI() {
        return DASI;
    }

    public double getVO2max() {
        return VO2max;
    }

    public double getMET() {
        return MET;
    }

    public String getCalculation() {
        return calculation;
    }

    @Override
    public String toString() {
        return calculation;
    }
}
