package com.example.demo1.controls.Friedwald;

import com.example.demo1.common.enums.CreatininUnit;
import com.example.demo1.common.enums.Gender;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class POAKModel {
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final StringProperty kreatinin = new SimpleStringProperty();
    private final ObjectProperty<CreatininUnit> creatininUnit = new SimpleObjectProperty<>();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private POAKModel(Builder builder) {
        this.kreatinin.set(builder.kreatinin);
        this.result.set(builder.result);
    }

    public Gender getGender() {
        return gender.get();
    }

    public void setGender(Gender gender) {
        this.gender.set(gender);
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public String getKreatinin() {
        return kreatinin.get();
    }

    public void setKreatinin(String kreatinin) {
        this.kreatinin.set(kreatinin);
    }

    public StringProperty kreatininProperty() {
        return kreatinin;
    }

    public CreatininUnit getCreatininUnit() {
        return creatininUnit.get();
    }

    public void setCreatininUnit(CreatininUnit creatininUnit) {
        this.creatininUnit.set(creatininUnit);
    }

    public ObjectProperty<CreatininUnit> creatininUnitProperty() {
        return creatininUnit;
    }

    public String getAge() {
        return age.get();
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public StringProperty ageProperty() {
        return age;
    }

    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public String getResult() {
        return result.get();
    }

    public void setResult(String result) {
        this.result.set(result);
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {
        try {
            double kreatininValue = Double.parseDouble(getKreatinin());
            POAKResult calcResult = FriedwaldCalculator.calc(kreatininValue);
            setResult(calcResult.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String kreatinin;
        private String result = "";


        public Builder withKreatinin(String kreatinin) {
            this.kreatinin = kreatinin;
            return this;
        }


        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public POAKModel build() {
            return new POAKModel(this);
        }
    }
}
