package com.example.demo1.controls.Khorana;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KhoranaModel {
    private final StringProperty tumorLocation = new SimpleStringProperty();
    private final ObservableList<String> patientFactors = FXCollections.observableArrayList();
    private final StringProperty result = new SimpleStringProperty();

    private KhoranaModel(Builder builder) {
        this.tumorLocation.set(builder.tumorLocation);
        this.patientFactors.addAll(builder.patientFactors);
        this.result.set(builder.result);
    }

    public StringProperty tumorLocationProperty() { return tumorLocation; }
    public ObservableList<String> getPatientFactors() { return patientFactors; }
    public StringProperty resultProperty() { return result; }

    public void setResult(String result) { this.result.set(result); }

    public void calc() {
        try {
            KhoranaResult res = KhoranaCalculator.calc(tumorLocation.get(), patientFactors);
            setResult(res.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String tumorLocation = "";
        private ObservableList<String> patientFactors = FXCollections.observableArrayList();
        private String result = "";

        public Builder withTumorLocation(String tumorLocation) { this.tumorLocation = tumorLocation; return this; }
        public Builder withPatientFactors(ObservableList<String> patientFactors) { this.patientFactors = patientFactors; return this; }
        public Builder withResult(String result) { this.result = result; return this; }

        public KhoranaModel build() { return new KhoranaModel(this); }
    }
}