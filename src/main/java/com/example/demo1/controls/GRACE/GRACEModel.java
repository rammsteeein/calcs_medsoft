package com.example.demo1.controls.GRACE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GRACEModel {

    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty hr = new SimpleStringProperty();
    private final StringProperty sbp = new SimpleStringProperty();
    private final StringProperty killipClass = new SimpleStringProperty();
    private final StringProperty creatinine = new SimpleStringProperty();
    private final StringProperty otherPoints = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public GRACEModel() {
        // пустой конструктор для прямого создания
    }

    // Properties
    public StringProperty ageProperty() { return age; }
    public StringProperty hrProperty() { return hr; }
    public StringProperty sbpProperty() { return sbp; }
    public StringProperty killipClassProperty() { return killipClass; }
    public StringProperty creatinineProperty() { return creatinine; }
    public StringProperty otherPointsProperty() { return otherPoints; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            int ageVal = Integer.parseInt(age.get());
            int hrVal = Integer.parseInt(hr.get());
            int sbpVal = Integer.parseInt(sbp.get());
            double creatVal = Double.parseDouble(creatinine.get());

            int other = GRACECalculator.mapOtherPoints(otherPoints.get());
            int agePoints = GRACECalculator.getAgePoints(ageVal);
            int hrPoints = GRACECalculator.getHRPoints(hrVal);
            int sbpPoints = GRACECalculator.getSBPPoints(sbpVal);
            int creatPoints = GRACECalculator.getCreatininePoints(creatVal);
            int killipPoints = GRACECalculator.mapKillipToPoints(killipClass.get());

            GRACEResult res = GRACECalculator.calc(agePoints, hrPoints, sbpPoints, killipPoints, creatPoints, other);
            result.set(res.toString());
        } catch (Exception e) {
            result.set("Ошибка: " + e.getMessage());
        }
    }

    // Для совместимости с прежним кодом
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String age = "";
        private String hr = "";
        private String sbp = "";
        private String killipClass = "";
        private String creatinine = "";
        private String otherPoints = "";
        private String result = "";

        public Builder withAge(String age) { this.age = age; return this; }
        public Builder withHR(String hr) { this.hr = hr; return this; }
        public Builder withSBP(String sbp) { this.sbp = sbp; return this; }
        public Builder withKillipClass(String killipClass) { this.killipClass = killipClass; return this; }
        public Builder withCreatinine(String creatinine) { this.creatinine = creatinine; return this; }
        public Builder withOtherPoints(String otherPoints) { this.otherPoints = otherPoints; return this; }
        public Builder withResult(String result) { this.result = result; return this; }

        public GRACEModel build() {
            GRACEModel model = new GRACEModel();
            model.ageProperty().set(age);
            model.hrProperty().set(hr);
            model.sbpProperty().set(sbp);
            model.killipClassProperty().set(killipClass);
            model.creatinineProperty().set(creatinine);
            model.otherPointsProperty().set(otherPoints);
            model.resultProperty().set(result);
            return model;
        }
    }
}
