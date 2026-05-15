package com.example.demo1.controls.UCT;

import javafx.beans.property.*;

public class UCTModel {

    private final IntegerProperty q1 = new SimpleIntegerProperty();
    private final IntegerProperty q2 = new SimpleIntegerProperty();
    private final IntegerProperty q3 = new SimpleIntegerProperty();
    private final IntegerProperty q4 = new SimpleIntegerProperty();

    private final DoubleProperty resultValue =
            new SimpleDoubleProperty();

    private final StringProperty result =
            new SimpleStringProperty();

    public IntegerProperty q1Property() {
        return q1;
    }

    public IntegerProperty q2Property() {
        return q2;
    }

    public IntegerProperty q3Property() {
        return q3;
    }

    public IntegerProperty q4Property() {
        return q4;
    }

    public DoubleProperty resultValueProperty() {
        return resultValue;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {

        UCTResult res = UCTCalculator.calc(
                q1.get(),
                q2.get(),
                q3.get(),
                q4.get()
        );

        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}