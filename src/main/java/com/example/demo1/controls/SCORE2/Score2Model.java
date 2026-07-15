package com.example.demo1.controls.SCORE2;

import javafx.beans.property.*;

public class Score2Model {

    private static final int MIN_AGE = 40;
    private static final int MAX_AGE = 69;

    private final BooleanProperty absolute = new SimpleBooleanProperty(true);
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty gender = new SimpleStringProperty();
    private final BooleanProperty smoking = new SimpleBooleanProperty();
    private final IntegerProperty sysAd = new SimpleIntegerProperty();
    private final IntegerProperty cholesterol = new SimpleIntegerProperty();

    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    private final BooleanProperty valid = new SimpleBooleanProperty(false);

    public void calc() {
        valid.set(false);

        if (age.get() <= 0) {
            resultValue.set(0);
            result.set("");
            return;
        }

        if (age.get() < MIN_AGE) {
            resultValue.set(0);
            result.set("Возраст некорректный. Шкала SCORE2 применяется с " + MIN_AGE + " лет.");
            return;
        }

        if (age.get() > MAX_AGE) {
            resultValue.set(0);
            result.set("Возраст некорректный. Шкала SCORE2 применяется до " + MAX_AGE + " лет.");
            return;
        }

        if (gender.get() == null ||
                sysAd.get() <= 0 ||
                cholesterol.get() <= 0) {

            resultValue.set(0);
            result.set("");
            return;
        }

        Score2Result res = Score2Calculator.calc(
                absolute.get(),
                age.get(),
                gender.get(),
                smoking.get(),
                sysAd.get(),
                cholesterol.get()
        );

        resultValue.set(res.getValue());
        result.set(res.toString());
        valid.set(true);
    }

    public BooleanProperty absoluteProperty() { return absolute; }
    public IntegerProperty ageProperty() { return age; }
    public StringProperty genderProperty() { return gender; }
    public BooleanProperty smokingProperty() { return smoking; }
    public IntegerProperty sysAdProperty() { return sysAd; }
    public IntegerProperty cholesterolProperty() { return cholesterol; }

    public DoubleProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }
    public BooleanProperty validProperty() { return valid; }
}
