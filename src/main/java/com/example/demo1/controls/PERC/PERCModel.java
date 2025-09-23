package com.example.demo1.controls.PERC;

import javafx.beans.property.*;

public class PERCModel {

    private final IntegerProperty age = new SimpleIntegerProperty();
    private final IntegerProperty heartRate = new SimpleIntegerProperty();
    private final DoubleProperty oxygen = new SimpleDoubleProperty();
    private final BooleanProperty unilateralLegEdema = new SimpleBooleanProperty();
    private final BooleanProperty hemoptysis = new SimpleBooleanProperty();
    private final BooleanProperty recentSurgeryOrTrauma = new SimpleBooleanProperty();
    private final BooleanProperty surgeryWithin4Weeks = new SimpleBooleanProperty();
    private final BooleanProperty prevPEorDVT = new SimpleBooleanProperty();
    private final BooleanProperty hormoneUse = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private PERCModel(Builder builder) {
        this.age.set(builder.age);
        this.heartRate.set(builder.heartRate);
        this.oxygen.set(builder.oxygen);
        this.unilateralLegEdema.set(builder.unilateralLegEdema);
        this.hemoptysis.set(builder.hemoptysis);
        this.recentSurgeryOrTrauma.set(builder.recentSurgeryOrTrauma);
        this.surgeryWithin4Weeks.set(builder.surgeryWithin4Weeks);
        this.prevPEorDVT.set(builder.prevPEorDVT);
        this.hormoneUse.set(builder.hormoneUse);
        this.result.set(builder.result);
    }

    // --- Геттеры / Сеттеры ---
    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public int getHeartRate() { return heartRate.get(); }
    public void setHeartRate(int val) { heartRate.set(val); }
    public IntegerProperty heartRateProperty() { return heartRate; }

    public double getOxygen() { return oxygen.get(); }
    public void setOxygen(double val) { oxygen.set(val); }
    public DoubleProperty oxygenProperty() { return oxygen; }

    public boolean isUnilateralLegEdema() { return unilateralLegEdema.get(); }
    public void setUnilateralLegEdema(boolean val) { unilateralLegEdema.set(val); }
    public BooleanProperty unilateralLegEdemaProperty() { return unilateralLegEdema; }

    public boolean isHemoptysis() { return hemoptysis.get(); }
    public void setHemoptysis(boolean val) { hemoptysis.set(val); }
    public BooleanProperty hemoptysisProperty() { return hemoptysis; }

    public boolean isRecentSurgeryOrTrauma() { return recentSurgeryOrTrauma.get(); }
    public void setRecentSurgeryOrTrauma(boolean val) { recentSurgeryOrTrauma.set(val); }
    public BooleanProperty recentSurgeryOrTraumaProperty() { return recentSurgeryOrTrauma; }

    public boolean isSurgeryWithin4Weeks() { return surgeryWithin4Weeks.get(); }
    public void setSurgeryWithin4Weeks(boolean val) { surgeryWithin4Weeks.set(val); }
    public BooleanProperty surgeryWithin4WeeksProperty() { return surgeryWithin4Weeks; }

    public boolean isPrevPEorDVT() { return prevPEorDVT.get(); }
    public void setPrevPEorDVT(boolean val) { prevPEorDVT.set(val); }
    public BooleanProperty prevPEorDVTProperty() { return prevPEorDVT; }

    public boolean isHormoneUse() { return hormoneUse.get(); }
    public void setHormoneUse(boolean val) { hormoneUse.set(val); }
    public BooleanProperty hormoneUseProperty() { return hormoneUse; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        PERCResult res = PERCCalculator.calc(
                getAge(),
                getHeartRate(),
                getOxygen(),
                isUnilateralLegEdema(),
                isHemoptysis(),
                isRecentSurgeryOrTrauma(),
                isSurgeryWithin4Weeks(),
                isPrevPEorDVT(),
                isHormoneUse()
        );
        setResult(res.toString());
    }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int age = 0;
        private int heartRate = 0;
        private double oxygen = 100.0;
        private boolean unilateralLegEdema = false;
        private boolean hemoptysis = false;
        private boolean recentSurgeryOrTrauma = false;
        private boolean surgeryWithin4Weeks = false;
        private boolean prevPEorDVT = false;
        private boolean hormoneUse = false;
        private String result = "";

        public Builder withAge(int val) { this.age = val; return this; }
        public Builder withHeartRate(int val) { this.heartRate = val; return this; }
        public Builder withOxygen(double val) { this.oxygen = val; return this; }
        public Builder withUnilateralLegEdema(boolean val) { this.unilateralLegEdema = val; return this; }
        public Builder withHemoptysis(boolean val) { this.hemoptysis = val; return this; }
        public Builder withRecentSurgeryOrTrauma(boolean val) { this.recentSurgeryOrTrauma = val; return this; }
        public Builder withSurgeryWithin4Weeks(boolean val) { this.surgeryWithin4Weeks = val; return this; }
        public Builder withPrevPEorDVT(boolean val) { this.prevPEorDVT = val; return this; }
        public Builder withHormoneUse(boolean val) { this.hormoneUse = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public PERCModel build() { return new PERCModel(this); }
    }
}
