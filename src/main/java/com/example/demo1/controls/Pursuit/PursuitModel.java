package com.example.demo1.controls.Pursuit;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.property.*;

public class PursuitModel {

    private final StringProperty age = new SimpleStringProperty("");
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);
    private final BooleanProperty hasAngina = new SimpleBooleanProperty();
    private final BooleanProperty hasHeartFailure = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private transient ResultStylerCallback onResultStyled;

    public interface ResultStylerCallback {
        void style(PursuitResult result);
    }

    public void setOnResultStyled(ResultStylerCallback callback) {
        this.onResultStyled = callback;
    }

    public StringProperty ageProperty() { return age; }
    public ObjectProperty<Gender> genderProperty() { return gender; }
    public BooleanProperty hasAnginaProperty() { return hasAngina; }
    public BooleanProperty hasHeartFailureProperty() { return hasHeartFailure; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            int ageValue = Integer.parseInt(age.get());
            PursuitResult res = PursuitCalculator.calc(ageValue, gender.get(), hasAngina.get(), hasHeartFailure.get());
            result.set(res.toString());

            if (onResultStyled != null) {
                onResultStyled.style(res);
            }
        } catch (Exception e) {
            result.set("Ошибка ввода: " + e.getMessage());
        }
    }
}
