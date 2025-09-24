package com.example.demo1.controls.Pursuit;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class PursuitModel {
    private final StringProperty age = new SimpleStringProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final BooleanProperty hasAngina = new SimpleBooleanProperty();
    private final BooleanProperty hasHeartFailure = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private PursuitModel(Builder builder) {
        this.age.set(builder.age);
        this.gender.set(builder.gender);
        this.result.set(builder.result);
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
            setResult(calcResult.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String age = "";
        private Gender gender = Gender.MALE;
        private String result = "";

        public Builder withAge(String age) {
            this.age = age;
            return this;
        }

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public PursuitModel build() {
            return new PursuitModel(this);
        }
    }
}
