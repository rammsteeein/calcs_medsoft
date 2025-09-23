package com.example.demo1.controls.Larsen;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LarsenModel {
    private final StringProperty drug = new SimpleStringProperty();
    private final ObservableList<String> patientFactors = FXCollections.observableArrayList();
    private final StringProperty result = new SimpleStringProperty();

    private LarsenModel(Builder builder) {
        this.drug.set(builder.drug);
        this.patientFactors.addAll(builder.patientFactors);
        this.result.set(builder.result);
    }

    public StringProperty drugProperty() {
        return drug;
    }

    public ObservableList<String> getPatientFactors() {
        return patientFactors;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public void calc() {
        try {
            LarsenResult res = LarsenCalculator.calc(drug.get(), patientFactors);
            setResult(res.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String drug = "";
        private ObservableList<String> patientFactors = FXCollections.observableArrayList();
        private String result = "";

        public Builder withDrug(String drug) {
            this.drug = drug;
            return this;
        }

        public Builder withPatientFactors(ObservableList<String> patientFactors) {
            this.patientFactors = patientFactors;
            return this;
        }

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public LarsenModel build() {
            return new LarsenModel(this);
        }
    }
}
