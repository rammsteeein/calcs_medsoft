package com.example.demo1.controls.FLI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FLIModel {
    private final StringProperty triglycerides = new SimpleStringProperty();
    private final StringProperty bmi = new SimpleStringProperty();
    private final StringProperty ggt = new SimpleStringProperty();
    private final StringProperty waistCircumference = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private FLIModel(Builder builder) {
        this.triglycerides.set(builder.triglycerides);
        this.bmi.set(builder.bmi);
        this.ggt.set(builder.ggt);
        this.waistCircumference.set(builder.waistCircumference);
        this.result.set(builder.result);
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty triglyceridesProperty() {
        return triglycerides;
    }

    public StringProperty bmiProperty() {
        return bmi;
    }

    public StringProperty ggtProperty() {
        return ggt;
    }

    public StringProperty waistCircumferenceProperty() {
        return waistCircumference;
    }

    public void calc() {
        try {
            double triglyceridesValue = Double.parseDouble(triglycerides.get());
            double bmiValue = Double.parseDouble(bmi.get());
            double ggtValue = Double.parseDouble(ggt.get());
            double waistCircumferenceValue = Double.parseDouble(waistCircumference.get());

            FLIResult calcResult = FLICalculator.calc(triglyceridesValue, bmiValue, ggtValue, waistCircumferenceValue);
            setResult(calcResult.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String triglycerides = "";
        private String bmi = "";
        private String ggt = "";
        private String waistCircumference = "";
        private String result = "";

        public Builder withTriglycerides(String triglycerides) {
            this.triglycerides = triglycerides;
            return this;
        }

        public Builder withBMI(String bmi) {
            this.bmi = bmi;
            return this;
        }

        public Builder withGGT(String ggt) {
            this.ggt = ggt;
            return this;
        }

        public Builder withWaistCircumference(String waistCircumference) {
            this.waistCircumference = waistCircumference;
            return this;
        }

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public FLIModel build() {
            return new FLIModel(this);
        }
    }
}