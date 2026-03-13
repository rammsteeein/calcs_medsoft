package com.example.demo1.controls.EHRA;

import javafx.beans.property.*;

public class EHRAModel {

    private final StringProperty status = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public StringProperty statusProperty() {
        return status;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {

        EHRAResult res = EHRACalculator.calc(status.get());

        result.set(res.toString());
    }
}