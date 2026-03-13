package com.example.demo1.controls.HoehnYahr;

import javafx.beans.property.*;

public class HoehnYahrModel {

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

        HoehnYahrResult res = HoehnYahrCalculator.calc(status.get());

        resultValue.set(res.getStage());
        result.set(res.toString());
    }
}