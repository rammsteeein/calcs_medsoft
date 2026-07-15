package com.example.demo1.controls.GorelkinPinhasov_age;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class GorelkinPinhasovModel {

    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();

    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty height = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final StringProperty waist = new SimpleStringProperty();
    private final StringProperty hips = new SimpleStringProperty();

    private final StringProperty result = new SimpleStringProperty();

    public GorelkinPinhasovModel() {

        gender.addListener((obs, oldVal, newVal) -> calc());
        age.addListener((obs, oldVal, newVal) -> calc());
        height.addListener((obs, oldVal, newVal) -> calc());
        weight.addListener((obs, oldVal, newVal) -> calc());
        waist.addListener((obs, oldVal, newVal) -> calc());
        hips.addListener((obs, oldVal, newVal) -> calc());

    }

    public Gender getGender() {
        return gender.get();
    }

    public void setGender(Gender value) {
        gender.set(value);
    }

    public ObjectProperty<Gender> genderProperty() {
        return gender;
    }

    public String getAge() {
        return age.get();
    }

    public void setAge(String value) {
        age.set(value);
    }

    public StringProperty ageProperty() {
        return age;
    }

    public String getHeight() {
        return height.get();
    }

    public void setHeight(String value) {
        height.set(value);
    }

    public StringProperty heightProperty() {
        return height;
    }

    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String value) {
        weight.set(value);
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public String getWaist() {
        return waist.get();
    }

    public void setWaist(String value) {
        waist.set(value);
    }

    public StringProperty waistProperty() {
        return waist;
    }

    public String getHips() {
        return hips.get();
    }

    public void setHips(String value) {
        hips.set(value);
    }

    public StringProperty hipsProperty() {
        return hips;
    }

    public String getResult() {
        return result.get();
    }

    public void setResult(String value) {
        result.set(value);
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {

        try {

            Gender genderVal = getGender();

            if (genderVal == null) {
                setResult("Выберите пол");
                return;
            }

            String res = GorelkinPinhasovCalculator.calc(
                    genderVal,
                    parseOrZero(getAge()),
                    parseOrZero(getHeight()),
                    parseOrZero(getWeight()),
                    parseOrZero(getWaist()),
                    parseOrZero(getHips())
            );

            setResult(res);

        } catch (NumberFormatException ex) {

            setResult("Некорректный формат числа");

        } catch (Exception ex) {

            setResult("Ошибка: " + ex.getMessage());

        }

    }

    private double parseOrZero(String value) {

        if (value == null || value.trim().isEmpty()) {
            return 0;
        }

        return Double.parseDouble(value.trim().replace(",", "."));
    }

}