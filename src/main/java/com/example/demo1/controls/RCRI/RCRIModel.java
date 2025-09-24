package com.example.demo1.controls.RCRI;

import javafx.beans.property.*;

public class RCRIModel {

    private final BooleanProperty highRiskSurgery = new SimpleBooleanProperty();
    private final BooleanProperty ischemicHeartDisease = new SimpleBooleanProperty();
    private final BooleanProperty heartFailure = new SimpleBooleanProperty();
    private final BooleanProperty cerebrovascularDisease = new SimpleBooleanProperty();
    private final BooleanProperty insulinTreatment = new SimpleBooleanProperty();
    private final BooleanProperty highCreatinine = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private RCRIModel(Builder builder) {
        this.highRiskSurgery.set(builder.highRiskSurgery);
        this.ischemicHeartDisease.set(builder.ischemicHeartDisease);
        this.heartFailure.set(builder.heartFailure);
        this.cerebrovascularDisease.set(builder.cerebrovascularDisease);
        this.insulinTreatment.set(builder.insulinTreatment);
        this.highCreatinine.set(builder.highCreatinine);
        this.result.set(builder.result);
    }

    public boolean isHighRiskSurgery() { return highRiskSurgery.get(); }
    public void setHighRiskSurgery(boolean val) { highRiskSurgery.set(val); }
    public BooleanProperty highRiskSurgeryProperty() { return highRiskSurgery; }

    public boolean isIschemicHeartDisease() { return ischemicHeartDisease.get(); }
    public void setIschemicHeartDisease(boolean val) { ischemicHeartDisease.set(val); }
    public BooleanProperty ischemicHeartDiseaseProperty() { return ischemicHeartDisease; }

    public boolean isHeartFailure() { return heartFailure.get(); }
    public void setHeartFailure(boolean val) { heartFailure.set(val); }
    public BooleanProperty heartFailureProperty() { return heartFailure; }

    public boolean isCerebrovascularDisease() { return cerebrovascularDisease.get(); }
    public void setCerebrovascularDisease(boolean val) { cerebrovascularDisease.set(val); }
    public BooleanProperty cerebrovascularDiseaseProperty() { return cerebrovascularDisease; }

    public boolean isInsulinTreatment() { return insulinTreatment.get(); }
    public void setInsulinTreatment(boolean val) { insulinTreatment.set(val); }
    public BooleanProperty insulinTreatmentProperty() { return insulinTreatment; }

    public boolean isHighCreatinine() { return highCreatinine.get(); }
    public void setHighCreatinine(boolean val) { highCreatinine.set(val); }
    public BooleanProperty highCreatinineProperty() { return highCreatinine; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        RCRIResult res = RCRICalculator.calc(
                isHighRiskSurgery(),
                isIschemicHeartDisease(),
                isHeartFailure(),
                isCerebrovascularDisease(),
                isInsulinTreatment(),
                isHighCreatinine()
        );
        setResult(res.toString());
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private boolean highRiskSurgery;
        private boolean ischemicHeartDisease;
        private boolean heartFailure;
        private boolean cerebrovascularDisease;
        private boolean insulinTreatment;
        private boolean highCreatinine;
        private String result = "";

        public Builder withHighRiskSurgery(boolean val) { this.highRiskSurgery = val; return this; }
        public Builder withIschemicHeartDisease(boolean val) { this.ischemicHeartDisease = val; return this; }
        public Builder withHeartFailure(boolean val) { this.heartFailure = val; return this; }
        public Builder withCerebrovascularDisease(boolean val) { this.cerebrovascularDisease = val; return this; }
        public Builder withInsulinTreatment(boolean val) { this.insulinTreatment = val; return this; }
        public Builder withHighCreatinine(boolean val) { this.highCreatinine = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public RCRIModel build() { return new RCRIModel(this); }
    }
}
