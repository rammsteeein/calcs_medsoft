package com.example.demo1.controls.Pursuit;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class PursuitModel {

    private final StringProperty age = new SimpleStringProperty("");
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(null);
    private final BooleanProperty hasAngina = new SimpleBooleanProperty(false);
    private final BooleanProperty hasHeartFailure = new SimpleBooleanProperty(false);
    private final StringProperty result = new SimpleStringProperty("Введите данные");

    private final DoubleProperty resultValue = new SimpleDoubleProperty(Double.NaN);

    public StringProperty ageProperty() { return age; }
    public ObjectProperty<Gender> genderProperty() { return gender; }
    public BooleanProperty hasAnginaProperty() { return hasAngina; }
    public BooleanProperty hasHeartFailureProperty() { return hasHeartFailure; }
    public StringProperty resultProperty() { return result; }
    public DoubleProperty resultValueProperty() { return resultValue; }

    public void calc() {
        try {
            int ageValue = Integer.parseInt(age.get());
            if (gender.get() == null) throw new IllegalArgumentException("Пол не выбран");

            PursuitResult res = PursuitCalculator.calc(ageValue, gender.get(), hasAngina.get(), hasHeartFailure.get());

            resultValue.set(res.getScore());
            result.set(res.toString());

        } catch (NumberFormatException e) {
            resultValue.set(Double.NaN);
            result.set("Некорректный ввод чисел");
        } catch (Exception e) {
            resultValue.set(Double.NaN);
            result.set("Ошибка: " + e.getMessage());
        }
    }
}