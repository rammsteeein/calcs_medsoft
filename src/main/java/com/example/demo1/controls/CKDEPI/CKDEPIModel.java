package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.CreatininUnit;
import com.example.demo1.common.enums.Gender;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CKDEPIModel {
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final StringProperty kreatinin = new SimpleStringProperty();
    private final ObjectProperty<CreatininUnit> creatininUnit = new SimpleObjectProperty<>();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private CKDEPIModel(Builder builder) {
        this.gender.set(builder.gender);
        this.kreatinin.set(builder.kreatinin);
        this.creatininUnit.set(builder.creatininUnit);
        this.age.set(builder.age);
        this.result.set(builder.result);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Gender gender;
        private String kreatinin;
        private CreatininUnit creatininUnit;
        private String age;
        private String result = "";

        public Builder withGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder withKreatinin(String kreatinin) {
            this.kreatinin = kreatinin;
            return this;
        }

        public Builder withCreatininUnit(CreatininUnit creatininUnit) {
            this.creatininUnit = creatininUnit;
            return this;
        }

        public Builder withAge(String age) {
            this.age = age;
            return this;
        }

        public Builder withResult(String result) {
            this.result = result;
            return this;
        }

        public CKDEPIModel build() {
            return new CKDEPIModel(this);
        }
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
            Gender genderValue = getGender();
            double kreatininValue = Double.parseDouble(getKreatinin());
            CreatininUnit creatininUnitValue = getCreatininUnit();
            int ageValue = Integer.parseInt(getAge());

            CKDEPIResult calcResult = CKDEPICalculator.calc(genderValue, kreatininValue, creatininUnitValue, ageValue);
            setResult(calcResult.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
