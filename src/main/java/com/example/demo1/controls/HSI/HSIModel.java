package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.property.*;
import javafx.scene.control.TextArea;

public class HSIModel {

    private final DoubleProperty alt = new SimpleDoubleProperty();
    private final DoubleProperty ast = new SimpleDoubleProperty();
    private final DoubleProperty bmi = new SimpleDoubleProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);
    private final BooleanProperty hasDiabetes = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private TextArea resultControl;

    public void setResultControl(TextArea control) { this.resultControl = control; }

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

        if (resultControl != null) {
            double score = res.getHsiValue();
            ResultStyler.Zone zone;
            if (score <= 2) zone = ResultStyler.Zone.LOW;
            else if (score <= 4) zone = ResultStyler.Zone.GRAY;
            else zone = ResultStyler.Zone.HIGH;

            ResultStyler.applyStyle(resultControl, zone);
        }
    }
}
