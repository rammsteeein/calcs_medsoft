package com.example.demo1.controls.CHA2DS2VASc;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class CHA2DS2VAScModel {

    private final BooleanProperty congestiveHeartFailure = new SimpleBooleanProperty();
    private final BooleanProperty hypertension = new SimpleBooleanProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final BooleanProperty diabetes = new SimpleBooleanProperty();
    private final BooleanProperty stroke = new SimpleBooleanProperty();
    private final BooleanProperty cardiovascular = new SimpleBooleanProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.MALE);

    private final StringProperty result = new SimpleStringProperty();
    private final DoubleProperty resultValue = new SimpleDoubleProperty(Double.NaN);

    public boolean isCongestiveHeartFailure() { return congestiveHeartFailure.get(); }
    public void setCongestiveHeartFailure(boolean val) { congestiveHeartFailure.set(val); }
    public BooleanProperty congestiveHeartFailureProperty() { return congestiveHeartFailure; }

    public boolean isHypertension() { return hypertension.get(); }
    public void setHypertension(boolean val) { hypertension.set(val); }
    public BooleanProperty hypertensionProperty() { return hypertension; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public boolean isDiabetes() { return diabetes.get(); }
    public void setDiabetes(boolean val) { diabetes.set(val); }
    public BooleanProperty diabetesProperty() { return diabetes; }

    public boolean isStroke() { return stroke.get(); }
    public void setStroke(boolean val) { stroke.set(val); }
    public BooleanProperty strokeProperty() { return stroke; }

    public boolean isCardiovascular() { return cardiovascular.get(); }
    public void setCardiovascular(boolean val) { cardiovascular.set(val); }
    public BooleanProperty cardiovascularProperty() { return cardiovascular; }

    public Gender getGender() { return gender.get(); }
    public void setGender(Gender val) { gender.set(val); }
    public ObjectProperty<Gender> genderProperty() { return gender; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public double getResultValue() { return resultValue.get(); }
    public void setResultValue(double val) { resultValue.set(val); }
    public DoubleProperty resultValueProperty() { return resultValue; }

    public void calc() {
        CHA2DS2VAScResult r = CHA2DS2VAScCalculator.calc(
                isCongestiveHeartFailure(),
                isHypertension(),
                getAge(),
                isDiabetes(),
                isStroke(),
                isCardiovascular(),
                getGender()
        );
        setResult(r.toString());
        setResultValue(r.getScore());
    }
}
