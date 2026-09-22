package com.example.demo1.controls.CTSI;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CTSIModel {

    private final StringProperty balthazar =
            new SimpleStringProperty();

    private final StringProperty necrosis =
            new SimpleStringProperty();

    private final DoubleProperty resultValue =
            new SimpleDoubleProperty();

    private final StringProperty result =
            new SimpleStringProperty();

    public StringProperty balthazarProperty() {
        return balthazar;
    }

    public StringProperty necrosisProperty() {
        return necrosis;
    }

    public DoubleProperty resultValueProperty() {
        return resultValue;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {

        CTSIResult r = CTSICalculator.calc(
                balthazar.get(),
                necrosis.get()
        );

        resultValue.set(r.getValue());
        result.set(r.toString());
    }
}