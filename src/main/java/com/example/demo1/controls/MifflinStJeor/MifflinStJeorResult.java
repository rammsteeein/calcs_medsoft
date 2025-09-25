package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Unit;

public class MifflinStJeorResult {
    private final double bmr;
    private final Unit unit;
    private final String calculation;

    public MifflinStJeorResult(double bmr, Unit unit, String calculation) {
        this.bmr = bmr;
        this.unit = unit;
        this.calculation = calculation;
    }

    public double getBmr() {
        return bmr;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getCalculation() {
        return calculation;
    }

    @Override
    public String toString() {
        return calculation;
    }
}
