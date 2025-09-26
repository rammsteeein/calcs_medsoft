package com.example.demo1.controls.LDL;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LDLModel {
    private final StringProperty nonHDL = new SimpleStringProperty();
    private final StringProperty tg = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public String getNonHDL() { return nonHDL.get(); }
    public void setNonHDL(String value) { nonHDL.set(value); }
    public StringProperty nonHDLProperty() { return nonHDL; }

    public String getTG() { return tg.get(); }
    public void setTG(String value) { tg.set(value); }
    public StringProperty tgProperty() { return tg; }

    public String getResult() { return result.get(); }
    public void setResult(String value) { result.set(value); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            double nonHDLValue = Double.parseDouble(getNonHDL());
            double tgValue = Double.parseDouble(getTG());

            LDLResult calcResult = LDLCalculator.calc(nonHDLValue, tgValue);
            setResult(calcResult.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
