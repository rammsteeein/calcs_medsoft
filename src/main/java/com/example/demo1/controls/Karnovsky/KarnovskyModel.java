package com.example.demo1.controls.Karnovsky;

import javafx.beans.property.*;

public class KarnovskyModel {
    private final StringProperty status = new SimpleStringProperty();
    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public StringProperty statusProperty() {
        return status;
    }

    public DoubleProperty resultValueProperty() {
        return resultValue;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {
        KarnovskyResult res = KarnovskyCalculator.calc(status.get());
        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}



