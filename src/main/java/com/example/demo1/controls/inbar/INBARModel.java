package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class INBARModel {
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);
    private final StringProperty result = new SimpleStringProperty("Введите данные");

    public StringProperty ageProperty() { return age; }
    public StringProperty weightProperty() { return weight; }
    public ObjectProperty<Gender> genderProperty() { return gender; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            double ageValue = Double.parseDouble(age.get());
            double weightValue = Double.parseDouble(weight.get());
            Gender g = gender.get();

            INBARResult calcResult = INBARCalculator.calc(ageValue, weightValue, g);
            result.set(calcResult.toString());
        } catch (NumberFormatException e) {
            result.set("Некорректный ввод чисел");
        } catch (Exception e) {
            result.set("Ошибка: " + e.getMessage());
        }
    }
}
