package com.example.demo1.controls.NoSAS;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class NoSASModel {

    private final DoubleProperty neckCircumference = new SimpleDoubleProperty();
    private final DoubleProperty bmi = new SimpleDoubleProperty();
    private final BooleanProperty hasSnoring = new SimpleBooleanProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);
    private final StringProperty result = new SimpleStringProperty();

    public double getNeckCircumference() { return neckCircumference.get(); }
    public void setNeckCircumference(double val) { neckCircumference.set(val); }
    public DoubleProperty neckCircumferenceProperty() { return neckCircumference; }

    public double getBmi() { return bmi.get(); }
    public void setBmi(double val) { bmi.set(val); }
    public DoubleProperty bmiProperty() { return bmi; }

    public boolean isHasSnoring() { return hasSnoring.get(); }
    public void setHasSnoring(boolean val) { hasSnoring.set(val); }
    public BooleanProperty hasSnoringProperty() { return hasSnoring; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public Gender getGender() { return gender.get(); }
    public void setGender(Gender val) { gender.set(val); }
    public ObjectProperty<Gender> genderProperty() { return gender; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            NoSASResult res = NoSASCalculator.calc(
                    getNeckCircumference(),
                    getBmi(),
                    isHasSnoring(),
                    getAge(),
                    getGender()
            );
            setResult(String.format("Сумма баллов: %d\nРиск: %s", res.getScore(), res.getRiskLevel()));
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
