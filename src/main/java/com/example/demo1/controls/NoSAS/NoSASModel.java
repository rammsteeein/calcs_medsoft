package com.example.demo1.controls.NoSAS;

import javafx.beans.property.*;

public class NoSASModel {

    private final DoubleProperty neckCircumference = new SimpleDoubleProperty();
    private final DoubleProperty bmi = new SimpleDoubleProperty();
    private final BooleanProperty hasSnoring = new SimpleBooleanProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final BooleanProperty isMale = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private NoSASModel(Builder builder) {
        this.neckCircumference.set(builder.neckCircumference);
        this.bmi.set(builder.bmi);
        this.hasSnoring.set(builder.hasSnoring);
        this.age.set(builder.age);
        this.isMale.set(builder.isMale);
        this.result.set(builder.result);
    }

    public double getNeckCircumference() { return neckCircumference.get(); }
    public void setNeckCircumference(double val) { neckCircumference.set(val); }
    public DoubleProperty neckCircumferenceProperty() { return neckCircumference; }

    public double getBmi() { return bmi.get(); }
    public void setBmi(double val) { bmi.set(val); }
    public DoubleProperty bmiProperty() { return bmi; }

    public boolean isHasSnoring() { return hasSnoring.get(); }
    public void setHasSnoring(boolean val) { hasSnoring.set(val); }
    public BooleanProperty hasSnoringProperty() { return hasSnoring; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public boolean isMale() { return isMale.get(); }
    public void setMale(boolean val) { isMale.set(val); }
    public BooleanProperty isMaleProperty() { return isMale; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            NoSASResult res = NoSASCalculator.calc(
                    getNeckCircumference(),
                    getBmi(),
                    isHasSnoring(),
                    getAge(),
                    isMale()
            );
            setResult(res.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private double neckCircumference = 0;
        private double bmi = 0;
        private boolean hasSnoring = false;
        private int age = 0;
        private boolean isMale = false;
        private String result = "";

        public Builder withNeckCircumference(double val) { this.neckCircumference = val; return this; }
        public Builder withBmi(double val) { this.bmi = val; return this; }
        public Builder withHasSnoring(boolean val) { this.hasSnoring = val; return this; }
        public Builder withAge(int val) { this.age = val; return this; }
        public Builder withIsMale(boolean val) { this.isMale = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public NoSASModel build() { return new NoSASModel(this); }
    }
}