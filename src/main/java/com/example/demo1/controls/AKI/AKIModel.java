package com.example.demo1.controls.AKI;

import javafx.beans.property.*;

public class AKIModel {

    private final DoubleProperty baselineCreatinine = new SimpleDoubleProperty();
    private final DoubleProperty currentCreatinine = new SimpleDoubleProperty();
    private final DoubleProperty urineOutput = new SimpleDoubleProperty();
    private final DoubleProperty weightKg = new SimpleDoubleProperty();
    private final DoubleProperty hours = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    private AKIModel(Builder builder) {
        this.baselineCreatinine.set(builder.baselineCreatinine);
        this.currentCreatinine.set(builder.currentCreatinine);
        this.urineOutput.set(builder.urineOutput);
        this.weightKg.set(builder.weightKg);
        this.hours.set(builder.hours);
        this.result.set(builder.result);
    }

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

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private double baselineCreatinine;
        private double currentCreatinine;
        private double urineOutput;
        private double weightKg;
        private double hours;
        private String result = "";

        public Builder withBaselineCreatinine(double val) { this.baselineCreatinine = val; return this; }
        public Builder withCurrentCreatinine(double val) { this.currentCreatinine = val; return this; }
        public Builder withUrineOutput(double val) { this.urineOutput = val; return this; }
        public Builder withWeightKg(double val) { this.weightKg = val; return this; }
        public Builder withHours(double val) { this.hours = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public AKIModel build() { return new AKIModel(this); }
    }
}
