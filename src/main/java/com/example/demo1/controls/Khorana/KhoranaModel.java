package com.example.demo1.controls.Khorana;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class KhoranaModel {
    private final StringProperty tumorLocation = new SimpleStringProperty();
    private final ObservableList<String> patientFactors = FXCollections.observableArrayList();
    private final StringProperty result = new SimpleStringProperty();

    public StringProperty tumorLocationProperty() { return tumorLocation; }
    public ObservableList<String> getPatientFactors() { return patientFactors; }
    public StringProperty resultProperty() { return result; }

    public void setResult(String value) { result.set(value); }

    public void calc() {
        try {
            KhoranaResult res = KhoranaCalculator.calc(tumorLocation.get(), patientFactors);
            setResult(res.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
