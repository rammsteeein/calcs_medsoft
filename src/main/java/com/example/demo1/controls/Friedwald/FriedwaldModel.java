package com.example.demo1.controls.Friedwald;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FriedwaldModel {
    private final StringProperty totalChol = new SimpleStringProperty("");
    private final StringProperty triglycerides = new SimpleStringProperty("");
    private final StringProperty hdl = new SimpleStringProperty("");
    private final StringProperty result = new SimpleStringProperty("Введите данные для расчёта");

    public StringProperty totalCholProperty() { return totalChol; }
    public StringProperty triglyceridesProperty() { return triglycerides; }
    public StringProperty hdlProperty() { return hdl; }
    public StringProperty resultProperty() { return result; }

    public void setResult(String res) { result.set(res); }

    public void calc() {
        try {
            double totalCholValue = Double.parseDouble(totalChol.get());
            double triglyceridesValue = Double.parseDouble(triglycerides.get());
            double hdlValue = Double.parseDouble(hdl.get());

            FriedwaldResult r = FriedwaldCalculator.calc(totalCholValue, triglyceridesValue, hdlValue);
            setResult(r.getFormatted());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
