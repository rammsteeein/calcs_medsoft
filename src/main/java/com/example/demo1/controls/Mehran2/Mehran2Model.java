package com.example.demo1.controls.Mehran2;

import javafx.beans.property.*;

public class Mehran2Model {

    private final BooleanProperty hypotension = new SimpleBooleanProperty();
    private final BooleanProperty balloonPump = new SimpleBooleanProperty();
    private final BooleanProperty heartFailure = new SimpleBooleanProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final BooleanProperty anemia = new SimpleBooleanProperty();
    private final BooleanProperty diabetes = new SimpleBooleanProperty();
    private final DoubleProperty contrastVolume = new SimpleDoubleProperty();
    private final DoubleProperty gfr = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();


    public boolean isHypotension() { return hypotension.get(); }
    public void setHypotension(boolean val) { hypotension.set(val); }
    public BooleanProperty hypotensionProperty() { return hypotension; }

    public boolean isBalloonPump() { return balloonPump.get(); }
    public void setBalloonPump(boolean val) { balloonPump.set(val); }
    public BooleanProperty balloonPumpProperty() { return balloonPump; }

    public boolean isHeartFailure() { return heartFailure.get(); }
    public void setHeartFailure(boolean val) { heartFailure.set(val); }
    public BooleanProperty heartFailureProperty() { return heartFailure; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public boolean isAnemia() { return anemia.get(); }
    public void setAnemia(boolean val) { anemia.set(val); }
    public BooleanProperty anemiaProperty() { return anemia; }

    public boolean isDiabetes() { return diabetes.get(); }
    public void setDiabetes(boolean val) { diabetes.set(val); }
    public BooleanProperty diabetesProperty() { return diabetes; }

    public double getContrastVolume() { return contrastVolume.get(); }
    public void setContrastVolume(double val) { contrastVolume.set(val); }
    public DoubleProperty contrastVolumeProperty() { return contrastVolume; }

    public double getGfr() { return gfr.get(); }
    public void setGfr(double val) { gfr.set(val); }
    public DoubleProperty gfrProperty() { return gfr; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        Mehran2Result res = Mehran2Calculator.calc(
                isHypotension(),
                isBalloonPump(),
                isHeartFailure(),
                getAge(),
                isAnemia(),
                isDiabetes(),
                getContrastVolume(),
                getGfr()
        );
        setResult(res.toString());
    }
}
