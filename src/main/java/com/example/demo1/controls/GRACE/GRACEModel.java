package com.example.demo1.controls.GRACE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GRACEModel {

    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty hr = new SimpleStringProperty();
    private final StringProperty sbp = new SimpleStringProperty();
    private final StringProperty killip = new SimpleStringProperty();
    private final StringProperty creatinine = new SimpleStringProperty();
    private final StringProperty other = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public StringProperty ageProperty() { return age; }
    public StringProperty hrProperty() { return hr; }
    public StringProperty sbpProperty() { return sbp; }
    public StringProperty killipProperty() { return killip; }
    public StringProperty creatinineProperty() { return creatinine; }
    public StringProperty otherProperty() { return other; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            int ageVal = Integer.parseInt(age.get());
            int hrVal = Integer.parseInt(hr.get());
            int sbpVal = Integer.parseInt(sbp.get());
            double creatVal = Double.parseDouble(creatinine.get());

            GRACEResult res = GRACECalculator.calc(
                    ageVal, hrVal, sbpVal, killip.get(), creatVal, other.get()
            );

            result.set(res.toString());
        } catch (Exception e) {
            result.set("Ошибка: " + e.getMessage());
        }
    }
}
