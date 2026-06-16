package com.example.demo1.controls.WIFI;

import javafx.beans.property.*;

public class WIFIModel {

    private final IntegerProperty wound = new SimpleIntegerProperty(-1);
    private final IntegerProperty ischemia = new SimpleIntegerProperty(-1);
    private final IntegerProperty infection = new SimpleIntegerProperty(-1);
    private final StringProperty result = new SimpleStringProperty();

    public IntegerProperty woundProperty() {
        return wound;
    }

    public IntegerProperty ischemiaProperty() {
        return ischemia;
    }

    public IntegerProperty infectionProperty() {
        return infection;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {
        Integer w = wound.get() < 0 ? null : wound.get();
        Integer i = ischemia.get() < 0 ? null : ischemia.get();
        Integer f = infection.get() < 0 ? null : infection.get();
        WIFIResult res = WIFICalculator.calc(w, i, f);
        result.set(res.toString());
    }
}
