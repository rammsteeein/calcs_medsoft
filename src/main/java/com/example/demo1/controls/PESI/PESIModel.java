package com.example.demo1.controls.PESI;

import javafx.beans.property.*;

public class PESIModel {

    private final IntegerProperty age = new SimpleIntegerProperty();
    private final BooleanProperty isMale = new SimpleBooleanProperty();
    private final BooleanProperty hasCancer = new SimpleBooleanProperty();
    private final BooleanProperty hasCHF = new SimpleBooleanProperty();
    private final BooleanProperty hasChronicLungDisease = new SimpleBooleanProperty();
    private final IntegerProperty heartRate = new SimpleIntegerProperty();
    private final IntegerProperty systolicBP = new SimpleIntegerProperty();
    private final IntegerProperty respiratoryRate = new SimpleIntegerProperty();
    private final DoubleProperty temperature = new SimpleDoubleProperty();
    private final BooleanProperty alteredMentalStatus = new SimpleBooleanProperty();
    private final DoubleProperty oxygenSaturation = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    private PESIModel(Builder builder) {
        this.age.set(builder.age);
        this.isMale.set(builder.isMale);
        this.hasCancer.set(builder.hasCancer);
        this.hasCHF.set(builder.hasCHF);
        this.hasChronicLungDisease.set(builder.hasChronicLungDisease);
        this.heartRate.set(builder.heartRate);
        this.systolicBP.set(builder.systolicBP);
        this.respiratoryRate.set(builder.respiratoryRate);
        this.temperature.set(builder.temperature);
        this.alteredMentalStatus.set(builder.alteredMentalStatus);
        this.oxygenSaturation.set(builder.oxygenSaturation);
        this.result.set(builder.result);
    }

    // --- Геттеры / Сеттеры ---
    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public boolean isMale() { return isMale.get(); }
    public void setMale(boolean val) { isMale.set(val); }
    public BooleanProperty isMaleProperty() { return isMale; }

    public boolean hasCancer() { return hasCancer.get(); }
    public void setHasCancer(boolean val) { hasCancer.set(val); }
    public BooleanProperty hasCancerProperty() { return hasCancer; }

    public boolean hasCHF() { return hasCHF.get(); }
    public void setHasCHF(boolean val) { hasCHF.set(val); }
    public BooleanProperty hasCHFProperty() { return hasCHF; }

    public boolean hasChronicLungDisease() { return hasChronicLungDisease.get(); }
    public void setHasChronicLungDisease(boolean val) { hasChronicLungDisease.set(val); }
    public BooleanProperty hasChronicLungDiseaseProperty() { return hasChronicLungDisease; }

    public int getHeartRate() { return heartRate.get(); }
    public void setHeartRate(int val) { heartRate.set(val); }
    public IntegerProperty heartRateProperty() { return heartRate; }

    public int getSystolicBP() { return systolicBP.get(); }
    public void setSystolicBP(int val) { systolicBP.set(val); }
    public IntegerProperty systolicBPProperty() { return systolicBP; }

    public int getRespiratoryRate() { return respiratoryRate.get(); }
    public void setRespiratoryRate(int val) { respiratoryRate.set(val); }
    public IntegerProperty respiratoryRateProperty() { return respiratoryRate; }

    public double getTemperature() { return temperature.get(); }
    public void setTemperature(double val) { temperature.set(val); }
    public DoubleProperty temperatureProperty() { return temperature; }

    public boolean isAlteredMentalStatus() { return alteredMentalStatus.get(); }
    public void setAlteredMentalStatus(boolean val) { alteredMentalStatus.set(val); }
    public BooleanProperty alteredMentalStatusProperty() { return alteredMentalStatus; }

    public double getOxygenSaturation() { return oxygenSaturation.get(); }
    public void setOxygenSaturation(double val) { oxygenSaturation.set(val); }
    public DoubleProperty oxygenSaturationProperty() { return oxygenSaturation; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        PESIResult res = PESICalculator.calc(
                getAge(),
                isMale(),
                hasCancer(),
                hasCHF(),
                hasChronicLungDisease(),
                getHeartRate(),
                getSystolicBP(),
                getRespiratoryRate(),
                getTemperature(),
                isAlteredMentalStatus(),
                getOxygenSaturation()
        );
        setResult(res.toString());
    }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int age = 0;
        private boolean isMale = false;
        private boolean hasCancer = false;
        private boolean hasCHF = false;
        private boolean hasChronicLungDisease = false;
        private int heartRate = 0;
        private int systolicBP = 0;
        private int respiratoryRate = 0;
        private double temperature = 36.6;
        private boolean alteredMentalStatus = false;
        private double oxygenSaturation = 100;
        private String result = "";

        public Builder withAge(int val) { this.age = val; return this; }
        public Builder withIsMale(boolean val) { this.isMale = val; return this; }
        public Builder withHasCancer(boolean val) { this.hasCancer = val; return this; }
        public Builder withHasCHF(boolean val) { this.hasCHF = val; return this; }
        public Builder withHasChronicLungDisease(boolean val) { this.hasChronicLungDisease = val; return this; }
        public Builder withHeartRate(int val) { this.heartRate = val; return this; }
        public Builder withSystolicBP(int val) { this.systolicBP = val; return this; }
        public Builder withRespiratoryRate(int val) { this.respiratoryRate = val; return this; }
        public Builder withTemperature(double val) { this.temperature = val; return this; }
        public Builder withAlteredMentalStatus(boolean val) { this.alteredMentalStatus = val; return this; }
        public Builder withOxygenSaturation(double val) { this.oxygenSaturation = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public PESIModel build() { return new PESIModel(this); }
    }
}