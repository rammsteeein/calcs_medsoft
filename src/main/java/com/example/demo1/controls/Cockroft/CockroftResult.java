package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Unit;

public class CockroftResult {
    private final double clearance;
    private final Unit unit;
    private final String interpretation;

    public CockroftResult(double clearance, Unit unit, String interpretation) {
        this.clearance = clearance;
        this.unit = unit;
        this.interpretation = interpretation;
    }

    public double getClearance() {
        return clearance;
    }

    public Unit getUnit() {
        return unit;
    }

    public String getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return String.format(
                "Клиренс креатинина: %.2f мл/мин\nИнтерпретация: %s",
                clearance, interpretation
        );
    }
}