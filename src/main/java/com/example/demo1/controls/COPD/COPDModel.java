package com.example.demo1.controls.COPD;

import javafx.beans.property.*;

public class COPDModel {

    private final StringProperty age = new SimpleStringProperty();
    private final IntegerProperty packYears = new SimpleIntegerProperty();

    private final DoubleProperty bmi = new SimpleDoubleProperty();

    private final StringProperty weatherCough = new SimpleStringProperty();
    private final StringProperty coughWithPhlegm = new SimpleStringProperty();
    private final StringProperty morningCough = new SimpleStringProperty();
    private final StringProperty dyspnea = new SimpleStringProperty();
    private final StringProperty allergy = new SimpleStringProperty();

    private final IntegerProperty resultValue = new SimpleIntegerProperty();
    private final StringProperty result = new SimpleStringProperty();

    private final DoubleProperty weight = new SimpleDoubleProperty();
    private final DoubleProperty heightCm = new SimpleDoubleProperty();

    public StringProperty ageProperty() { return age; }
    public IntegerProperty packYearsProperty() { return packYears; }

    public DoubleProperty bmiProperty() { return bmi; }

    public StringProperty weatherCoughProperty() { return weatherCough; }
    public StringProperty coughWithPhlegmProperty() { return coughWithPhlegm; }
    public StringProperty morningCoughProperty() { return morningCough; }
    public StringProperty dyspneaProperty() { return dyspnea; }
    public StringProperty allergyProperty() { return allergy; }

    public DoubleProperty weightProperty() { return weight; }
    public DoubleProperty heightCmProperty() { return heightCm; }

    public IntegerProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }

    public void calc() {

        double bmiValue = 0;

        if (weight.get() > 0 && heightCm.get() > 0) {
            double heightMeters = heightCm.get() / 100.0;
            bmiValue = weight.get() / (heightMeters * heightMeters);
        } else {
            bmiValue = bmi.get();
        }

        COPDResult res = COPDCalculator.calc(
                age.get(),
                packYears.get(),
                bmiValue,
                weatherCough.get(),
                coughWithPhlegm.get(),
                morningCough.get(),
                dyspnea.get(),
                allergy.get()
        );

        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}