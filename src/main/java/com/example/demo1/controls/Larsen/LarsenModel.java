package com.example.demo1.controls.Larsen;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LarsenModel {
    private final StringProperty drug = new SimpleStringProperty();
    private final ObservableList<String> patientFactors = FXCollections.observableArrayList();

    private final IntegerProperty totalScore = new SimpleIntegerProperty(0);
    private final StringProperty interpretation = new SimpleStringProperty("Отсутствие риска");
    private final StringProperty calculation = new SimpleStringProperty("Результат появится здесь");

    public void calc() {
        LarsenResult result = LarsenCalculator.calc(drug.get(), patientFactors);
        totalScore.set(result.getTotalScore());
        interpretation.set(result.getInterpretation());
        calculation.set(result.getCalculation());
    }

    public StringProperty drugProperty() { return drug; }
    public ObservableList<String> getPatientFactors() { return patientFactors; }

    public IntegerProperty totalScoreProperty() { return totalScore; }
    public StringProperty interpretationProperty() { return interpretation; }
    public StringProperty calculationProperty() { return calculation; }
}
