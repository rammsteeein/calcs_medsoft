package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class HSIModel {

    private final DoubleProperty alt = new SimpleDoubleProperty();
    private final DoubleProperty ast = new SimpleDoubleProperty();
    private final DoubleProperty bmi = new SimpleDoubleProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final BooleanProperty hasDiabetes = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private HSIModel(Builder builder) {
        this.alt.set(builder.alt);
        this.ast.set(builder.ast);
        this.bmi.set(builder.bmi);
        this.gender.set(builder.gender);
        this.hasDiabetes.set(builder.hasDiabetes);
        this.result.set(builder.result);
    }

    // --- Геттеры и свойства ---
    public double getAlt() { return alt.get(); }
    public void setAlt(double val) { alt.set(val); }
    public DoubleProperty altProperty() { return alt; }

    public double getAst() { return ast.get(); }
    public void setAst(double val) { ast.set(val); }
    public DoubleProperty astProperty() { return ast; }

    public double getBmi() { return bmi.get(); }
    public void setBmi(double val) { bmi.set(val); }
    public DoubleProperty bmiProperty() { return bmi; }

    public Gender getGender() { return gender.get(); }
    public void setGender(Gender val) { gender.set(val); }
    public ObjectProperty<Gender> genderProperty() { return gender; }

    public boolean isHasDiabetes() { return hasDiabetes.get(); }
    public void setHasDiabetes(boolean val) { hasDiabetes.set(val); }
    public BooleanProperty hasDiabetesProperty() { return hasDiabetes; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        HSIResult res = HSICalculator.calc(getAlt(), getAst(), getBmi(), getGender(), isHasDiabetes());
        setResult(res.toString());
    }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private double alt;
        private double ast;
        private double bmi;
        private Gender gender;
        private boolean hasDiabetes;
        private String result = "";

        public Builder withAlt(double val) { this.alt = val; return this; }
        public Builder withAst(double val) { this.ast = val; return this; }
        public Builder withBmi(double val) { this.bmi = val; return this; }
        public Builder withGender(Gender val) { this.gender = val; return this; }
        public Builder withHasDiabetes(boolean val) { this.hasDiabetes = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public HSIModel build() { return new HSIModel(this); }
    }
}
