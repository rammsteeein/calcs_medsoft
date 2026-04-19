package com.example.demo1.controls.SCORE2OP;

import javafx.beans.property.*;

public class Score2OPModel {

    private final BooleanProperty absolute = new SimpleBooleanProperty(true);
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final BooleanProperty smoking = new SimpleBooleanProperty();
    private final IntegerProperty sysAd = new SimpleIntegerProperty();
    private final IntegerProperty cholesterol = new SimpleIntegerProperty();

    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public void calc() {
        if (age.get() <= 0 ||
                gender.get() == null ||
                sysAd.get() <= 0 ||
                cholesterol.get() <= 0) {

            resultValue.set(0);
            result.set("");
            return;
        }

        Score2OPResult res = Score2OPCalculator.calc(
                absolute.get(),
                age.get(),
                gender.get(),
                smoking.get(),
                sysAd.get(),
                cholesterol.get()
        );

        resultValue.set(res.getValue());
        result.set(res.toString());
    }

    public BooleanProperty absoluteProperty() { return absolute; }
    public IntegerProperty ageProperty() { return age; }
    public StringProperty genderProperty() { return gender; }
    public BooleanProperty smokingProperty() { return smoking; }
    public IntegerProperty sysAdProperty() { return sysAd; }
    public IntegerProperty cholesterolProperty() { return cholesterol; }

    public DoubleProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }
}