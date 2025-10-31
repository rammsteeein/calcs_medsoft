package com.example.demo1.controls.CDS;

import javafx.beans.property.*;

public class CDSModel {
    private final StringProperty appearance = new SimpleStringProperty();
    private final StringProperty eyes = new SimpleStringProperty();
    private final StringProperty mucous = new SimpleStringProperty();
    private final StringProperty tears = new SimpleStringProperty();

    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public StringProperty appearanceProperty() { return appearance; }
    public StringProperty eyesProperty() { return eyes; }
    public StringProperty mucousProperty() { return mucous; }
    public StringProperty tearsProperty() { return tears; }

    public DoubleProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        CDSResult res = CDSCalculator.calc(
                appearance.get(),
                eyes.get(),
                mucous.get(),
                tears.get()
        );
        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}