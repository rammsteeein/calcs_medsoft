package com.example.demo1.controls.Pursuit;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class PursuitModel {
    private final StringProperty age = new SimpleStringProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final BooleanProperty hasAngina = new SimpleBooleanProperty();
    private final BooleanProperty hasHeartFailure = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    public PursuitModel() {
        this.age.set("");
        this.gender.set(Gender.MALE);
        this.result.set("");
    }

    public String getAge() { return age.get(); }
    public void setAge(String age) { this.age.set(age); }
    public StringProperty ageProperty() { return age; }

    public Gender getGender() { return gender.get(); }
    public void setGender(Gender gender) { this.gender.set(gender); }
    public ObjectProperty<Gender> genderProperty() { return gender; }

    public boolean isHasAngina() { return hasAngina.get(); }
    public void setHasAngina(boolean hasAngina) { this.hasAngina.set(hasAngina); }
    public BooleanProperty hasAnginaProperty() { return hasAngina; }

    public boolean isHasHeartFailure() { return hasHeartFailure.get(); }
    public void setHasHeartFailure(boolean hasHeartFailure) { this.hasHeartFailure.set(hasHeartFailure); }
    public BooleanProperty hasHeartFailureProperty() { return hasHeartFailure; }

    public String getResult() { return result.get(); }
    public void setResult(String result) { this.result.set(result); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            int ageValue = Integer.parseInt(getAge());
            PursuitResult calcResult = PursuitCalculator.calc(
                    ageValue,
                    getGender(),
                    isHasAngina(),
                    isHasHeartFailure()
            );
            setResult(calcResult.getInterpretation());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
