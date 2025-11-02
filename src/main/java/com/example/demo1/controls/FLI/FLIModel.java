package com.example.demo1.controls.FLI;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FLIModel {
    private final StringProperty triglycerides = new SimpleStringProperty("");
    private final StringProperty bmi = new SimpleStringProperty("");
    private final StringProperty ggt = new SimpleStringProperty("");
    private final StringProperty waistCircumference = new SimpleStringProperty("");
    private final StringProperty result = new SimpleStringProperty("Введите данные для расчёта");

    private final DoubleProperty resultValue = new SimpleDoubleProperty(Double.NaN);

    public StringProperty triglyceridesProperty() { return triglycerides; }
    public StringProperty bmiProperty() { return bmi; }
    public StringProperty ggtProperty() { return ggt; }
    public StringProperty waistCircumferenceProperty() { return waistCircumference; }
    public StringProperty resultProperty() { return result; }
    public DoubleProperty resultValueProperty() { return resultValue; }

    public void calc() {
        try {
            double triglyceridesValue = Double.parseDouble(triglycerides.get());
            double bmiValue = Double.parseDouble(bmi.get());
            double ggtValue = Double.parseDouble(ggt.get());
            double waistValue = Double.parseDouble(waistCircumference.get());

            FLIResult fliResult = FLICalculator.calc(triglyceridesValue, bmiValue, ggtValue, waistValue);

            resultValue.set(fliResult.getFli());
            result.set(fliResult.getFormatted());
        } catch (NumberFormatException e) {
            resultValue.set(Double.NaN);
            result.set("Ошибка: некорректный ввод чисел");
        } catch (Exception e) {
            resultValue.set(Double.NaN);
            result.set("Ошибка: " + e.getMessage());
        }
    }
}
