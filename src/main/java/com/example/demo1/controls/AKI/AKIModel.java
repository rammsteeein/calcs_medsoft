package com.example.demo1.controls.AKI;

import javafx.beans.property.*;

public class AKIModel {
    private final DoubleProperty baselineCreatinine = new SimpleDoubleProperty();
    private final DoubleProperty currentCreatinine = new SimpleDoubleProperty();
    private final DoubleProperty urineOutput = new SimpleDoubleProperty();
    private final DoubleProperty weightKg = new SimpleDoubleProperty();
    private final DoubleProperty hours = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public double getBaselineCreatinine() { return baselineCreatinine.get(); }
    public void setBaselineCreatinine(double val) { baselineCreatinine.set(val); }
    public DoubleProperty baselineCreatinineProperty() { return baselineCreatinine; }

    public double getCurrentCreatinine() { return currentCreatinine.get(); }
    public void setCurrentCreatinine(double val) { currentCreatinine.set(val); }
    public DoubleProperty currentCreatinineProperty() { return currentCreatinine; }

    public double getUrineOutput() { return urineOutput.get(); }
    public void setUrineOutput(double val) { urineOutput.set(val); }
    public DoubleProperty urineOutputProperty() { return urineOutput; }

    public double getWeightKg() { return weightKg.get(); }
    public void setWeightKg(double val) { weightKg.set(val); }
    public DoubleProperty weightKgProperty() { return weightKg; }

    public double getHours() { return hours.get(); }
    public void setHours(double val) { hours.set(val); }
    public DoubleProperty hoursProperty() { return hours; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        AKIResult res = AKICalculator.calc(
                getBaselineCreatinine(),
                getCurrentCreatinine(),
                getUrineOutput(),
                getWeightKg(),
                getHours()
        );
        setResult(res.toString());
    }
}
