package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Unit;

public class CockroftResult {
    private final double clearance;
    private final Unit unit;
    private final String calculation;

    public CockroftResult(double clearance, Unit unit, String calculation) {
        this.clearance = clearance;
        this.unit = unit;
        this.calculation = calculation;
    }

    public double getClearance() {
        return clearance;
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
