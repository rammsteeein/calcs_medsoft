package com.example.demo1.controls.Wells;

import javafx.beans.property.*;

public class WellsModel {

    private final BooleanProperty prevPEorDVT = new SimpleBooleanProperty();
    private final BooleanProperty tachycardia = new SimpleBooleanProperty();
    private final BooleanProperty surgeryOrImmobilization = new SimpleBooleanProperty();
    private final BooleanProperty hemoptysis = new SimpleBooleanProperty();
    private final BooleanProperty activeCancer = new SimpleBooleanProperty();
    private final BooleanProperty clinicalDVT = new SimpleBooleanProperty();
    private final BooleanProperty alternativeLessLikely = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private WellsModel(Builder builder) {
        this.prevPEorDVT.set(builder.prevPEorDVT);
        this.tachycardia.set(builder.tachycardia);
        this.surgeryOrImmobilization.set(builder.surgeryOrImmobilization);
        this.hemoptysis.set(builder.hemoptysis);
        this.activeCancer.set(builder.activeCancer);
        this.clinicalDVT.set(builder.clinicalDVT);
        this.alternativeLessLikely.set(builder.alternativeLessLikely);
        this.result.set(builder.result);
    }

    // --- Геттеры / Сеттеры ---
    public boolean isPrevPEorDVT() { return prevPEorDVT.get(); }
    public void setPrevPEorDVT(boolean val) { prevPEorDVT.set(val); }
    public BooleanProperty prevPEorDVTProperty() { return prevPEorDVT; }

    public boolean isTachycardia() { return tachycardia.get(); }
    public void setTachycardia(boolean val) { tachycardia.set(val); }
    public BooleanProperty tachycardiaProperty() { return tachycardia; }

    public boolean isSurgeryOrImmobilization() { return surgeryOrImmobilization.get(); }
    public void setSurgeryOrImmobilization(boolean val) { surgeryOrImmobilization.set(val); }
    public BooleanProperty surgeryOrImmobilizationProperty() { return surgeryOrImmobilization; }

    public boolean isHemoptysis() { return hemoptysis.get(); }
    public void setHemoptysis(boolean val) { hemoptysis.set(val); }
    public BooleanProperty hemoptysisProperty() { return hemoptysis; }

    public boolean isActiveCancer() { return activeCancer.get(); }
    public void setActiveCancer(boolean val) { activeCancer.set(val); }
    public BooleanProperty activeCancerProperty() { return activeCancer; }

    public boolean isClinicalDVT() { return clinicalDVT.get(); }
    public void setClinicalDVT(boolean val) { clinicalDVT.set(val); }
    public BooleanProperty clinicalDVTProperty() { return clinicalDVT; }

    public boolean isAlternativeLessLikely() { return alternativeLessLikely.get(); }
    public void setAlternativeLessLikely(boolean val) { alternativeLessLikely.set(val); }
    public BooleanProperty alternativeLessLikelyProperty() { return alternativeLessLikely; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        WellsResult res = WellsCalculator.calc(
                isPrevPEorDVT(),
                isTachycardia(),
                isSurgeryOrImmobilization(),
                isHemoptysis(),
                isActiveCancer(),
                isClinicalDVT(),
                isAlternativeLessLikely()
        );
        setResult(res.toString());
    }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private boolean prevPEorDVT = false;
        private boolean tachycardia = false;
        private boolean surgeryOrImmobilization = false;
        private boolean hemoptysis = false;
        private boolean activeCancer = false;
        private boolean clinicalDVT = false;
        private boolean alternativeLessLikely = false;
        private String result = "";

        public Builder withPrevPEorDVT(boolean val) { this.prevPEorDVT = val; return this; }
        public Builder withTachycardia(boolean val) { this.tachycardia = val; return this; }
        public Builder withSurgeryOrImmobilization(boolean val) { this.surgeryOrImmobilization = val; return this; }
        public Builder withHemoptysis(boolean val) { this.hemoptysis = val; return this; }
        public Builder withActiveCancer(boolean val) { this.activeCancer = val; return this; }
        public Builder withClinicalDVT(boolean val) { this.clinicalDVT = val; return this; }
        public Builder withAlternativeLessLikely(boolean val) { this.alternativeLessLikely = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public WellsModel build() { return new WellsModel(this); }
    }
}