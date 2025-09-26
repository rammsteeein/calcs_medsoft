package com.example.demo1.controls.POAK_doze;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class POAKModel {
    private final StringProperty kreatinin = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public String getKreatinin() {
        return kreatinin.get();
    }

    public void setKreatinin(String kreatinin) {
        this.kreatinin.set(kreatinin);
    }

    public StringProperty kreatininProperty() {
        return kreatinin;
    }

    public String getResult() {
        return result.get();
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {
        try {
            double clearance = Double.parseDouble(getKreatinin());
            POAKResult calcResult = POAKCalculator.calc(clearance);
            setResult(calcResult.getFormatted());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
