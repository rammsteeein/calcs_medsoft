package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;
import javafx.beans.property.*;

public class MifflinStJeorModel {

    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final DoubleProperty weight = new SimpleDoubleProperty();
    private final DoubleProperty height = new SimpleDoubleProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();

    private final DoubleProperty bmr = new SimpleDoubleProperty();
    private final ObjectProperty<Unit> unit = new SimpleObjectProperty<>(Unit.KCAL_PER_DAY);
    private final StringProperty calculation = new SimpleStringProperty();

    public void calc() {
        if (gender.get() == null || weight.get() <= 0 || height.get() <= 0 || age.get() <= 0) {
            calculation.set("Недостаточно данных для расчёта");
            return;
        }

        MifflinStJeorResult result = MifflinStJeorCalculator.calc(getGender(), getWeight(), getHeight(), getAge());
        bmr.set(result.getBmr());
        unit.set(result.getUnit());
        calculation.set(result.getCalculation());
    }

    // getters/properties
    public ObjectProperty<Gender> genderProperty() { return gender; }
    public Gender getGender() { return gender.get(); }
    public void setGender(Gender val) { gender.set(val); }

    public DoubleProperty weightProperty() { return weight; }
    public double getWeight() { return weight.get(); }
    public void setWeight(double val) { weight.set(val); }

    public DoubleProperty heightProperty() { return height; }
    public double getHeight() { return height.get(); }
    public void setHeight(double val) { height.set(val); }

    public IntegerProperty ageProperty() { return age; }
    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }

    public DoubleProperty bmrProperty() { return bmr; }
    public double getBmr() { return bmr.get(); }

    public ObjectProperty<Unit> unitProperty() { return unit; }
    public Unit getUnit() { return unit.get(); }

    public StringProperty calculationProperty() { return calculation; }
    public String getCalculation() { return calculation.get(); }
}
