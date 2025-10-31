package com.example.demo1.controls.FLI;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FLIModel {
    private final StringProperty triglycerides = new SimpleStringProperty("");
    private final StringProperty bmi = new SimpleStringProperty("");
    private final StringProperty ggt = new SimpleStringProperty("");
    private final StringProperty waistCircumference = new SimpleStringProperty("");
    private final StringProperty result = new SimpleStringProperty("Введите данные для расчёта");

    public FLIModel() {
    }

    public StringProperty triglyceridesProperty() { return triglycerides; }
    public StringProperty bmiProperty() { return bmi; }
    public StringProperty ggtProperty() { return ggt; }
    public StringProperty waistCircumferenceProperty() { return waistCircumference; }
    public StringProperty resultProperty() { return result; }

    public void setResult(String result) { this.result.set(result); }

    public void calc() {
        try {
            double triglyceridesValue = Double.parseDouble(triglycerides.get());
            double bmiValue = Double.parseDouble(bmi.get());
            double ggtValue = Double.parseDouble(ggt.get());
            double waistCircumferenceValue = Double.parseDouble(waistCircumference.get());

            FLIResult calcResult = FLICalculator.calc(triglyceridesValue, bmiValue, ggtValue, waistCircumferenceValue);
            setResult(calcResult.getFormatted());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
