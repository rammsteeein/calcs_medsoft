package com.example.demo1.controls.SAMSCI;

import javafx.beans.property.*;

public class SAMSCIModel {

    private final IntegerProperty muscleLocation = new SimpleIntegerProperty();
    private final IntegerProperty symptomTiming = new SimpleIntegerProperty();
    private final IntegerProperty statinDiscontinuation = new SimpleIntegerProperty();
    private final IntegerProperty statinRechallenge = new SimpleIntegerProperty();
    private final StringProperty result = new SimpleStringProperty();

    public int getMuscleLocation() { return muscleLocation.get(); }
    public void setMuscleLocation(int val) { muscleLocation.set(val); }
    public IntegerProperty muscleLocationProperty() { return muscleLocation; }

    public int getSymptomTiming() { return symptomTiming.get(); }
    public void setSymptomTiming(int val) { symptomTiming.set(val); }
    public IntegerProperty symptomTimingProperty() { return symptomTiming; }

    public int getStatinDiscontinuation() { return statinDiscontinuation.get(); }
    public void setStatinDiscontinuation(int val) { statinDiscontinuation.set(val); }
    public IntegerProperty statinDiscontinuationProperty() { return statinDiscontinuation; }

    public int getStatinRechallenge() { return statinRechallenge.get(); }
    public void setStatinRechallenge(int val) { statinRechallenge.set(val); }
    public IntegerProperty statinRechallengeProperty() { return statinRechallenge; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        SAMSCIResult res = SAMSCICalculator.calc(
                getMuscleLocation(),
                getSymptomTiming(),
                getStatinDiscontinuation(),
                getStatinRechallenge()
        );
        setResult(res.toString());
    }
}
