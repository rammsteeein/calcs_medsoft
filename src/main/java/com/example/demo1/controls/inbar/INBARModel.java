package com.example.demo1.controls.inbar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class INBARModel {
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private INBARModel(Builder builder) {
        this.age.set(builder.age);
        this.weight.set(builder.weight);
        this.result.set(builder.result);
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public StringProperty resultProperty() {
        return result;
    }

    public StringProperty ageProperty() {
        return age;
    }

    public StringProperty weightProperty() {
        return weight;
    }


    public void calc() {
        try {
            double agrValue = Double.parseDouble(age.get());
            double weightValue = Double.parseDouble(weight.get());

            INBARResult calcResult = INBARCalculator.calc(agrValue, weightValue);
            setResult(calcResult.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String age = "";
        private String weight = "";
        private String result = "";

        public Builder withAge(String triglycerides) {
            this.age = triglycerides;
            return this;
        }

        public Builder withWeight(String bmi) {
            this.weight = bmi;
            return this;
        }

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public INBARModel build() {
            return new INBARModel(this);
        }
    }
}