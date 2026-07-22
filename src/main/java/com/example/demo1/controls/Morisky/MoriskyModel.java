package com.example.demo1.controls.Morisky;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MoriskyModel {

    private final StringProperty question1 = new SimpleStringProperty();
    private final StringProperty question2 = new SimpleStringProperty();
    private final StringProperty question3 = new SimpleStringProperty();
    private final StringProperty question4 = new SimpleStringProperty();

    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public StringProperty question1Property() {
        return question1;
    }

    public StringProperty question2Property() {
        return question2;
    }

    public StringProperty question3Property() {
        return question3;
    }

    public StringProperty question4Property() {
        return question4;
    }

    public DoubleProperty resultValueProperty() {
        return resultValue;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {

        MoriskyResult res = MoriskyCalculator.calc(
                question1.get(),
                question2.get(),
                question3.get(),
                question4.get()
        );

        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}