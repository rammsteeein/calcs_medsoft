package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class INBARModel {
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);
    private final StringProperty result = new SimpleStringProperty();

    public String getAge() { return age.get(); }
    public void setAge(String value) { age.set(value); }
    public StringProperty ageProperty() { return age; }

    public String getWeight() { return weight.get(); }
    public void setWeight(String value) { weight.set(value); }
    public StringProperty weightProperty() { return weight; }

    public Gender getGender() { return gender.get(); }
    public void setGender(Gender value) { gender.set(value); }
    public ObjectProperty<Gender> genderProperty() { return gender; }

    public String getResult() { return result.get(); }
    public void setResult(String value) { result.set(value); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            double ageValue = Double.parseDouble(getAge());
            double weightValue = Double.parseDouble(getWeight());
            Gender g = getGender();

            INBARResult calcResult = INBARCalculator.calc(ageValue, weightValue, g);
            setResult(calcResult.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
