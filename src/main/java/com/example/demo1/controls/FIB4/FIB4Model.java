package com.example.demo1.controls.FIB4;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FIB4Model {
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty ast = new SimpleStringProperty();
    private final StringProperty alt = new SimpleStringProperty();
    private final StringProperty platelets = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private final DoubleProperty resultValue = new SimpleDoubleProperty(Double.NaN);

    public StringProperty ageProperty() { return age; }
    public StringProperty astProperty() { return ast; }
    public StringProperty altProperty() { return alt; }
    public StringProperty plateletsProperty() { return platelets; }
    public StringProperty resultProperty() { return result; }
    public DoubleProperty resultValueProperty() { return resultValue; }

    public void calc() {
        try {
            int ageValue = Integer.parseInt(age.get());
            double astValue = Double.parseDouble(ast.get());
            double altValue = Double.parseDouble(alt.get());
            double plateletsValue = Double.parseDouble(platelets.get());

            FIB4Result res = FIB4Calculator.calc(ageValue, astValue, altValue, plateletsValue);
            resultValue.set(res.getValue());
            result.set(res.toString());
        } catch (Exception e) {
            resultValue.set(Double.NaN);
            result.set("Ошибка: " + e.getMessage());
        }
    }
}
