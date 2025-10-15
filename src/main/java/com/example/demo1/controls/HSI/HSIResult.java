package com.example.demo1.controls.HSI;

import com.example.demo1.common.services.ResultStyler;

public class HSIResult {
    private final String result;
    private final double hsiValue;
    private final ResultStyler.Zone zone;

    public HSIResult(String result, double hsiValue, ResultStyler.Zone zone) {
        this.result = result;
        this.hsiValue = hsiValue;
        this.zone = zone;
    }

    public String getResult() { return result; }
    public double getHsiValue() { return hsiValue; }
    public ResultStyler.Zone getZone() { return zone; }

    @Override
    public String toString() { return result; }
}