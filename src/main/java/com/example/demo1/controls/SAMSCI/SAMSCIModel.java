package com.example.demo1.controls.SAMSCI;

import javafx.beans.property.*;

public class SAMSCIModel {

    private final IntegerProperty muscleLocation = new SimpleIntegerProperty();
    private final IntegerProperty symptomTiming = new SimpleIntegerProperty();
    private final IntegerProperty statinDiscontinuation = new SimpleIntegerProperty();
    private final IntegerProperty statinRechallenge = new SimpleIntegerProperty();

    private final IntegerProperty score = new SimpleIntegerProperty();
    private final StringProperty interpretation = new SimpleStringProperty();

    public int getMuscleLocation() { return muscleLocation.get(); }
    public void setMuscleLocation(int val) { muscleLocation.set(val); }

    public int getSymptomTiming() { return symptomTiming.get(); }
    public void setSymptomTiming(int val) { symptomTiming.set(val); }

    public int getStatinDiscontinuation() { return statinDiscontinuation.get(); }
    public void setStatinDiscontinuation(int val) { statinDiscontinuation.set(val); }

    public int getStatinRechallenge() { return statinRechallenge.get(); }
    public void setStatinRechallenge(int val) { statinRechallenge.set(val); }

    public int getScore() { return score.get(); }
    public void setScore(int val) { score.set(val); }
    public IntegerProperty scoreProperty() { return score; }

    public String getInterpretation() { return interpretation.get(); }
    public void setInterpretation(String val) { interpretation.set(val); }
    public StringProperty interpretationProperty() { return interpretation; }

    public void calc() {
        SAMSCIResult res = SAMSCICalculator.calc(
                getMuscleLocation(),
                getSymptomTiming(),
                getStatinDiscontinuation(),
                getStatinRechallenge()
        );
        setScore(res.getScore());
        setInterpretation(res.getInterpretation());
    }
}
