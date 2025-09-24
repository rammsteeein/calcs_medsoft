package com.example.demo1.controls.inbar;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class INBARModel {
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);
    private final StringProperty result = new SimpleStringProperty();

    private INBARModel(Builder builder) {
        this.age.set(builder.age);
        this.weight.set(builder.weight);
        this.result.set(builder.result);
        this.gender.set(builder.gender);
    }

    public StringProperty resultProperty() { return result; }
    public StringProperty ageProperty() { return age; }
    public StringProperty weightProperty() { return weight; }
    public ObjectProperty<Gender> genderProperty() { return gender; }

    public void setResult(String result) { this.result.set(result); }

    public void calc() {
        try {
            double agrValue = Double.parseDouble(age.get());
            double weightValue = Double.parseDouble(weight.get());
            Gender g = gender.get();

            INBARResult calcResult = INBARCalculator.calc(agrValue, weightValue, g);
            setResult(calcResult.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String age = "";
        private String weight = "";
        private String result = "";
        private Gender gender = Gender.MALE;

        public Builder withAge(String age) { this.age = age; return this; }
        public Builder withWeight(String weight) { this.weight = weight; return this; }
        public Builder withResult(String result) { this.result = result; return this; }
        public Builder withGender(Gender gender) { this.gender = gender; return this; }

        public INBARModel build() { return new INBARModel(this); }
    }
}
