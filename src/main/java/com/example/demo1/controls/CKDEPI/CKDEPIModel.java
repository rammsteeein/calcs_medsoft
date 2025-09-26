package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.Unit;
import com.example.demo1.common.enums.Gender;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CKDEPIModel {
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final StringProperty kreatinin = new SimpleStringProperty();
    private final ObjectProperty<Unit> creatininUnit = new SimpleObjectProperty<>();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

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

    public Unit getCreatininUnit() {
        return creatininUnit.get();
    }

    public void setCreatininUnit(Unit unit) {
        this.creatininUnit.set(unit);
    }

    public ObjectProperty<Unit> creatininUnitProperty() {
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
            Unit unitValue = getCreatininUnit();
            int ageValue = Integer.parseInt(getAge());

            CKDEPIResult calcResult = CKDEPICalculator.calc(genderValue, kreatininValue, unitValue, ageValue);
            setResult(calcResult.getFormatted());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }
}
