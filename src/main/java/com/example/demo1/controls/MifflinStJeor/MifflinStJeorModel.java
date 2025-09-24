package com.example.demo1.controls.MifflinStJeor;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class MifflinStJeorModel {

    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final DoubleProperty weight = new SimpleDoubleProperty();
    private final DoubleProperty height = new SimpleDoubleProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty result = new SimpleStringProperty();

    private MifflinStJeorModel(Builder builder) {
        this.gender.set(builder.gender);
        this.weight.set(builder.weight);
        this.height.set(builder.height);
        this.age.set(builder.age);
        this.result.set(builder.result);
    }

    public Gender getGender() { return gender.get(); }
    public void setGender(Gender val) { gender.set(val); }
    public ObjectProperty<Gender> genderProperty() { return gender; }

    public double getWeight() { return weight.get(); }
    public void setWeight(double val) { weight.set(val); }
    public DoubleProperty weightProperty() { return weight; }

    public double getHeight() { return height.get(); }
    public void setHeight(double val) { height.set(val); }
    public DoubleProperty heightProperty() { return height; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        MifflinStJeorResult res = MifflinStJeorCalculator.calc(getGender(), getWeight(), getHeight(), getAge());
        setResult(res.toString());
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Gender gender;
        private double weight;
        private double height;
        private int age;
        private String result = "";

        public Builder withGender(Gender gender) { this.gender = gender; return this; }
        public Builder withWeight(double weight) { this.weight = weight; return this; }
        public Builder withHeight(double height) { this.height = height; return this; }
        public Builder withAge(int age) { this.age = age; return this; }
        public Builder withResult(String result) { this.result = result; return this; }

        public MifflinStJeorModel build() { return new MifflinStJeorModel(this); }
    }
}