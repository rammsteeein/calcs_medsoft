package com.example.demo1.controls.UAS7;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UAS7Model {

    private final IntegerProperty[] wheals = new IntegerProperty[7];
    private final IntegerProperty[] itch = new IntegerProperty[7];

    private final StringProperty result = new SimpleStringProperty("");
    private final IntegerProperty resultValue = new SimpleIntegerProperty();

    public UAS7Model() {

        for (int i = 0; i < 7; i++) {
            wheals[i] = new SimpleIntegerProperty(0);
            itch[i] = new SimpleIntegerProperty(0);
        }
    }

    public IntegerProperty whealsProperty(int day) {
        return wheals[day];
    }

    public IntegerProperty itchProperty(int day) {
        return itch[day];
    }

    public StringProperty resultProperty() {
        return result;
    }

    public IntegerProperty resultValueProperty() {
        return resultValue;
    }

    public void calc() {

        int total = 0;

        for (int i = 0; i < 7; i++) {
            total += wheals[i].get();
            total += itch[i].get();
        }

        UAS7Result res = UAS7Calculator.calc(total);

        resultValue.set(res.getScore());
        result.set(res.toString());
    }
}