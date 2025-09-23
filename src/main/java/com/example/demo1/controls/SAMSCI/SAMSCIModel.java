package com.example.demo1.controls.SAMSCI;

import javafx.beans.property.*;

public class SAMSCIModel {

    private final IntegerProperty muscleLocation = new SimpleIntegerProperty();
    private final IntegerProperty symptomTiming = new SimpleIntegerProperty();
    private final IntegerProperty statinDiscontinuation = new SimpleIntegerProperty();
    private final IntegerProperty statinRechallenge = new SimpleIntegerProperty();
    private final StringProperty result = new SimpleStringProperty();

    // Приватный конструктор для Builder
    private SAMSCIModel(Builder builder) {
        this.muscleLocation.set(builder.muscleLocation);
        this.symptomTiming.set(builder.symptomTiming);
        this.statinDiscontinuation.set(builder.statinDiscontinuation);
        this.statinRechallenge.set(builder.statinRechallenge);
        this.result.set(builder.result);
    }

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

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int muscleLocation = 0;
        private int symptomTiming = 0;
        private int statinDiscontinuation = 0;
        private int statinRechallenge = 0;
        private String result = "";

        public Builder withMuscleLocation(int val) { this.muscleLocation = val; return this; }
        public Builder withSymptomTiming(int val) { this.symptomTiming = val; return this; }
        public Builder withStatinDiscontinuation(int val) { this.statinDiscontinuation = val; return this; }
        public Builder withStatinRechallenge(int val) { this.statinRechallenge = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public SAMSCIModel build() {
            return new SAMSCIModel(this);
        }
    }
}