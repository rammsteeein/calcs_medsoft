package com.example.demo1.controls.LDL;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LDLModel {
    private final StringProperty nonHDL = new SimpleStringProperty();
    private final StringProperty tg = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private LDLModel(Builder builder) {
        this.nonHDL.set(builder.nonHDL);
        this.tg.set(builder.tg);
        this.result.set(builder.result);
    }

    public StringProperty nonHDLProperty() {
        return nonHDL;
    }

    public StringProperty tgProperty() {
        return tg;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {
        try {
            double nonHDLValue = Double.parseDouble(nonHDL.get());
            double tgValue = Double.parseDouble(tg.get());
            LDLResult calcResult = LDLCalculator.calc(nonHDLValue, tgValue);
            result.set(calcResult.toString());
        } catch (Exception e) {
            result.set("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String nonHDL = "";
        private String tg = "";
        private String result = "";

        public Builder withNonHDL(String nonHDL) {
            this.nonHDL = nonHDL;
            return this;
        }

        public Builder withTG(String tg) {
            this.tg = tg;
            return this;
        }

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public LDLModel build() {
            return new LDLModel(this);
        }
    }
}