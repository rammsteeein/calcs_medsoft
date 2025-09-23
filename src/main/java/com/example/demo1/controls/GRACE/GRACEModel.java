package com.example.demo1.controls.GRACE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GRACEModel {

    private final StringProperty agePoints = new SimpleStringProperty();
    private final StringProperty hrPoints = new SimpleStringProperty();
    private final StringProperty sbpPoints = new SimpleStringProperty();
    private final StringProperty killipClass = new SimpleStringProperty(); // I, II, III, IV
    private final StringProperty creatininePoints = new SimpleStringProperty();
    private final StringProperty otherPoints = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private GRACEModel(Builder builder) {
        this.agePoints.set(builder.agePoints);
        this.hrPoints.set(builder.hrPoints);
        this.sbpPoints.set(builder.sbpPoints);
        this.killipClass.set(builder.killipClass);
        this.creatininePoints.set(builder.creatininePoints);
        this.otherPoints.set(builder.otherPoints);
        this.result.set(builder.result);
    }

    public StringProperty agePointsProperty() { return agePoints; }
    public StringProperty hrPointsProperty() { return hrPoints; }
    public StringProperty sbpPointsProperty() { return sbpPoints; }
    public StringProperty killipClassProperty() { return killipClass; }
    public StringProperty creatininePointsProperty() { return creatininePoints; }
    public StringProperty otherPointsProperty() { return otherPoints; }
    public StringProperty resultProperty() { return result; }

    public void setResult(String result) { this.result.set(result); }

    public void calc() {
        try {
            int age = Integer.parseInt(agePoints.get());
            int hr = Integer.parseInt(hrPoints.get());
            int sbp = Integer.parseInt(sbpPoints.get());
            int creat = Integer.parseInt(creatininePoints.get());
            int other = Integer.parseInt(otherPoints.get());

            int killipPoints = mapKillipToPoints(killipClass.get());

            GRACEResult res = GRACECalculator.calc(age, hr, sbp, killipPoints, creat, other);
            setResult(res.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    private int mapKillipToPoints(String killip) {
        if (killip == null) return 0;
        switch (killip) {
            case "I": return 0;
            case "II": return 20;
            case "III": return 39;
            case "IV": return 59;
            default: return 0;
        }
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String agePoints = "";
        private String hrPoints = "";
        private String sbpPoints = "";
        private String killipClass = "";
        private String creatininePoints = "";
        private String otherPoints = "";
        private String result = "";

        public Builder withAgePoints(String agePoints) { this.agePoints = agePoints; return this; }
        public Builder withHRPoints(String hrPoints) { this.hrPoints = hrPoints; return this; }
        public Builder withSBPPoints(String sbpPoints) { this.sbpPoints = sbpPoints; return this; }
        public Builder withKillipClass(String killipClass) { this.killipClass = killipClass; return this; }
        public Builder withCreatininePoints(String creatininePoints) { this.creatininePoints = creatininePoints; return this; }
        public Builder withOtherPoints(String otherPoints) { this.otherPoints = otherPoints; return this; }
        public Builder withResult(String result) { this.result = result; return this; }

        public GRACEModel build() { return new GRACEModel(this); }
    }
}