package com.example.demo1.controls.rGENEVA;

import javafx.beans.property.*;

public class rGENEVAModel {

    private final BooleanProperty prevPEorDVT = new SimpleBooleanProperty();
    private final IntegerProperty heartRate = new SimpleIntegerProperty();
    private final BooleanProperty surgeryOrFracture = new SimpleBooleanProperty();
    private final BooleanProperty hemoptysis = new SimpleBooleanProperty();
    private final BooleanProperty activeCancer = new SimpleBooleanProperty();
    private final BooleanProperty legPain = new SimpleBooleanProperty();
    private final BooleanProperty painAndSwelling = new SimpleBooleanProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty result = new SimpleStringProperty();

    private rGENEVAModel(Builder builder) {
        this.prevPEorDVT.set(builder.prevPEorDVT);
        this.heartRate.set(builder.heartRate);
        this.surgeryOrFracture.set(builder.surgeryOrFracture);
        this.hemoptysis.set(builder.hemoptysis);
        this.activeCancer.set(builder.activeCancer);
        this.legPain.set(builder.legPain);
        this.painAndSwelling.set(builder.painAndSwelling);
        this.age.set(builder.age);
        this.result.set(builder.result);
    }

    // --- Геттеры / Сеттеры ---
    public boolean isPrevPEorDVT() { return prevPEorDVT.get(); }
    public void setPrevPEorDVT(boolean val) { prevPEorDVT.set(val); }
    public BooleanProperty prevPEorDVTProperty() { return prevPEorDVT; }

    public int getHeartRate() { return heartRate.get(); }
    public void setHeartRate(int val) { heartRate.set(val); }
    public IntegerProperty heartRateProperty() { return heartRate; }

    public boolean isSurgeryOrFracture() { return surgeryOrFracture.get(); }
    public void setSurgeryOrFracture(boolean val) { surgeryOrFracture.set(val); }
    public BooleanProperty surgeryOrFractureProperty() { return surgeryOrFracture; }

    public boolean isHemoptysis() { return hemoptysis.get(); }
    public void setHemoptysis(boolean val) { hemoptysis.set(val); }
    public BooleanProperty hemoptysisProperty() { return hemoptysis; }

    public boolean isActiveCancer() { return activeCancer.get(); }
    public void setActiveCancer(boolean val) { activeCancer.set(val); }
    public BooleanProperty activeCancerProperty() { return activeCancer; }

    public boolean isLegPain() { return legPain.get(); }
    public void setLegPain(boolean val) { legPain.set(val); }
    public BooleanProperty legPainProperty() { return legPain; }

    public boolean isPainAndSwelling() { return painAndSwelling.get(); }
    public void setPainAndSwelling(boolean val) { painAndSwelling.set(val); }
    public BooleanProperty painAndSwellingProperty() { return painAndSwelling; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        rGENEVAResult res = rGENEVACalculator.calc(
                isPrevPEorDVT(),
                getHeartRate(),
                isSurgeryOrFracture(),
                isHemoptysis(),
                isActiveCancer(),
                isLegPain(),
                isPainAndSwelling(),
                getAge()
        );
        setResult(res.toString());
    }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private boolean prevPEorDVT = false;
        private int heartRate = 0;
        private boolean surgeryOrFracture = false;
        private boolean hemoptysis = false;
        private boolean activeCancer = false;
        private boolean legPain = false;
        private boolean painAndSwelling = false;
        private int age = 0;
        private String result = "";

        public Builder withPrevPEorDVT(boolean val) { this.prevPEorDVT = val; return this; }
        public Builder withHeartRate(int val) { this.heartRate = val; return this; }
        public Builder withSurgeryOrFracture(boolean val) { this.surgeryOrFracture = val; return this; }
        public Builder withHemoptysis(boolean val) { this.hemoptysis = val; return this; }
        public Builder withActiveCancer(boolean val) { this.activeCancer = val; return this; }
        public Builder withLegPain(boolean val) { this.legPain = val; return this; }
        public Builder withPainAndSwelling(boolean val) { this.painAndSwelling = val; return this; }
        public Builder withAge(int val) { this.age = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public rGENEVAModel build() { return new rGENEVAModel(this); }
    }
}