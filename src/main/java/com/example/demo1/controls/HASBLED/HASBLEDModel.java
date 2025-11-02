package com.example.demo1.controls.HASBLED;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HASBLEDModel {
    private final StringProperty hypertension = new SimpleStringProperty();
    private final StringProperty renalLiver = new SimpleStringProperty();
    private final StringProperty stroke = new SimpleStringProperty();
    private final StringProperty bleeding = new SimpleStringProperty();
    private final StringProperty inr = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty drugsAlcohol = new SimpleStringProperty();

    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public StringProperty hypertensionProperty() { return hypertension; }
    public StringProperty renalLiverProperty() { return renalLiver; }
    public StringProperty strokeProperty() { return stroke; }
    public StringProperty bleedingProperty() { return bleeding; }
    public StringProperty inrProperty() { return inr; }
    public StringProperty ageProperty() { return age; }
    public StringProperty drugsAlcoholProperty() { return drugsAlcohol; }

    public DoubleProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        HASBLEDResult res = HASBLEDCalculator.calc(
                hypertension.get(),
                renalLiver.get(),
                stroke.get(),
                bleeding.get(),
                inr.get(),
                age.get(),
                drugsAlcohol.get()
        );
        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}
