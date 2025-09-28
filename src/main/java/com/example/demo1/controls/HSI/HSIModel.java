package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class HSIModel {

    private final DoubleProperty alt = new SimpleDoubleProperty();
    private final DoubleProperty ast = new SimpleDoubleProperty();
    private final DoubleProperty bmi = new SimpleDoubleProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);
    private final BooleanProperty hasDiabetes = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

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
}
