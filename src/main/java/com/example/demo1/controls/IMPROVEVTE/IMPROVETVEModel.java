package com.example.demo1.controls.IMPROVEVTE;

import javafx.beans.property.*;

public class IMPROVETVEModel {

    private final BooleanProperty priorVTE = new SimpleBooleanProperty();
    private final BooleanProperty knownThrombophilia = new SimpleBooleanProperty();
    private final BooleanProperty lowerLimbParalysis = new SimpleBooleanProperty();
    private final BooleanProperty activeCancer = new SimpleBooleanProperty();
    private final BooleanProperty immobilization7Days = new SimpleBooleanProperty();
    private final BooleanProperty ICUstay = new SimpleBooleanProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty result = new SimpleStringProperty();

    private IMPROVETVEModel(Builder builder) {
        this.priorVTE.set(builder.priorVTE);
        this.knownThrombophilia.set(builder.knownThrombophilia);
        this.lowerLimbParalysis.set(builder.lowerLimbParalysis);
        this.activeCancer.set(builder.activeCancer);
        this.immobilization7Days.set(builder.immobilization7Days);
        this.ICUstay.set(builder.ICUstay);
        this.age.set(builder.age);
        this.result.set(builder.result);
    }

    // --- Геттеры / Сеттеры ---
    public boolean isPriorVTE() { return priorVTE.get(); }
    public void setPriorVTE(boolean val) { priorVTE.set(val); }
    public BooleanProperty priorVTEProperty() { return priorVTE; }

    public boolean isKnownThrombophilia() { return knownThrombophilia.get(); }
    public void setKnownThrombophilia(boolean val) { knownThrombophilia.set(val); }
    public BooleanProperty knownThrombophiliaProperty() { return knownThrombophilia; }

    public boolean isLowerLimbParalysis() { return lowerLimbParalysis.get(); }
    public void setLowerLimbParalysis(boolean val) { lowerLimbParalysis.set(val); }
    public BooleanProperty lowerLimbParalysisProperty() { return lowerLimbParalysis; }

    public boolean isActiveCancer() { return activeCancer.get(); }
    public void setActiveCancer(boolean val) { activeCancer.set(val); }
    public BooleanProperty activeCancerProperty() { return activeCancer; }

    public boolean isImmobilization7Days() { return immobilization7Days.get(); }
    public void setImmobilization7Days(boolean val) { immobilization7Days.set(val); }
    public BooleanProperty immobilization7DaysProperty() { return immobilization7Days; }

    public boolean isICUstay() { return ICUstay.get(); }
    public void setICUstay(boolean val) { ICUstay.set(val); }
    public BooleanProperty ICUstayProperty() { return ICUstay; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        IMPROVEResult res = IMPROVETVECalculator.calc(
                isPriorVTE(),
                isKnownThrombophilia(),
                isLowerLimbParalysis(),
                isActiveCancer(),
                isImmobilization7Days(),
                isICUstay(),
                getAge()
        );
        setResult(res.toString());
    }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private boolean priorVTE = false;
        private boolean knownThrombophilia = false;
        private boolean lowerLimbParalysis = false;
        private boolean activeCancer = false;
        private boolean immobilization7Days = false;
        private boolean ICUstay = false;
        private int age = 0;
        private String result = "";

        public Builder withPriorVTE(boolean val) { this.priorVTE = val; return this; }
        public Builder withKnownThrombophilia(boolean val) { this.knownThrombophilia = val; return this; }
        public Builder withLowerLimbParalysis(boolean val) { this.lowerLimbParalysis = val; return this; }
        public Builder withActiveCancer(boolean val) { this.activeCancer = val; return this; }
        public Builder withImmobilization7Days(boolean val) { this.immobilization7Days = val; return this; }
        public Builder withICUstay(boolean val) { this.ICUstay = val; return this; }
        public Builder withAge(int val) { this.age = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public IMPROVETVEModel build() { return new IMPROVETVEModel(this); }
    }
}
