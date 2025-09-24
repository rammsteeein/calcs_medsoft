package com.example.demo1.controls.REACH;

import javafx.beans.property.*;

public class REACHModel {

    private final StringProperty age = new SimpleStringProperty();
    private final BooleanProperty peripheralAtherosclerosis = new SimpleBooleanProperty();
    private final BooleanProperty heartFailure = new SimpleBooleanProperty();
    private final BooleanProperty diabetes = new SimpleBooleanProperty();
    private final BooleanProperty hypercholesterolemia = new SimpleBooleanProperty();
    private final BooleanProperty hypertension = new SimpleBooleanProperty();
    private final IntegerProperty smokingStatus = new SimpleIntegerProperty();
    private final IntegerProperty antiplatelet = new SimpleIntegerProperty();
    private final BooleanProperty oralAnticoagulant = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private REACHModel(Builder builder) {
        this.age.set(builder.age);
        this.peripheralAtherosclerosis.set(builder.peripheralAtherosclerosis);
        this.heartFailure.set(builder.heartFailure);
        this.diabetes.set(builder.diabetes);
        this.hypercholesterolemia.set(builder.hypercholesterolemia);
        this.hypertension.set(builder.hypertension);
        this.smokingStatus.set(builder.smokingStatus);
        this.antiplatelet.set(builder.antiplatelet);
        this.oralAnticoagulant.set(builder.oralAnticoagulant);
        this.result.set(builder.result);
    }

    public String getAge() { return age.get(); }
    public void setAge(String value) { age.set(value); }
    public StringProperty ageProperty() { return age; }

    public boolean isPeripheralAtherosclerosis() { return peripheralAtherosclerosis.get(); }
    public void setPeripheralAtherosclerosis(boolean val) { peripheralAtherosclerosis.set(val); }
    public BooleanProperty peripheralAtherosclerosisProperty() { return peripheralAtherosclerosis; }

    public boolean isHeartFailure() { return heartFailure.get(); }
    public void setHeartFailure(boolean val) { heartFailure.set(val); }
    public BooleanProperty heartFailureProperty() { return heartFailure; }

    public boolean isDiabetes() { return diabetes.get(); }
    public void setDiabetes(boolean val) { diabetes.set(val); }
    public BooleanProperty diabetesProperty() { return diabetes; }

    public boolean isHypercholesterolemia() { return hypercholesterolemia.get(); }
    public void setHypercholesterolemia(boolean val) { hypercholesterolemia.set(val); }
    public BooleanProperty hypercholesterolemiaProperty() { return hypercholesterolemia; }

    public boolean isHypertension() { return hypertension.get(); }
    public void setHypertension(boolean val) { hypertension.set(val); }
    public BooleanProperty hypertensionProperty() { return hypertension; }

    public int getSmokingStatus() { return smokingStatus.get(); }
    public void setSmokingStatus(int val) { smokingStatus.set(val); }
    public IntegerProperty smokingStatusProperty() { return smokingStatus; }

    public int getAntiplatelet() { return antiplatelet.get(); }
    public void setAntiplatelet(int val) { antiplatelet.set(val); }
    public IntegerProperty antiplateletProperty() { return antiplatelet; }

    public boolean isOralAnticoagulant() { return oralAnticoagulant.get(); }
    public void setOralAnticoagulant(boolean val) { oralAnticoagulant.set(val); }
    public BooleanProperty oralAnticoagulantProperty() { return oralAnticoagulant; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            int ageVal = Integer.parseInt(getAge());
            REACHResult res = REACHCalculator.calc(
                    ageVal,
                    isPeripheralAtherosclerosis(),
                    isHeartFailure(),
                    isDiabetes(),
                    isHypercholesterolemia(),
                    isHypertension(),
                    getSmokingStatus(),
                    getAntiplatelet(),
                    isOralAnticoagulant()
            );
            setResult(res.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String age = "";
        private boolean peripheralAtherosclerosis = false;
        private boolean heartFailure = false;
        private boolean diabetes = false;
        private boolean hypercholesterolemia = false;
        private boolean hypertension = false;
        private int smokingStatus = 0;
        private int antiplatelet = 0;
        private boolean oralAnticoagulant = false;
        private String result = "";

        public Builder withAge(String val) { this.age = val; return this; }
        public Builder withPeripheralAtherosclerosis(boolean val) { this.peripheralAtherosclerosis = val; return this; }
        public Builder withHeartFailure(boolean val) { this.heartFailure = val; return this; }
        public Builder withDiabetes(boolean val) { this.diabetes = val; return this; }
        public Builder withHypercholesterolemia(boolean val) { this.hypercholesterolemia = val; return this; }
        public Builder withHypertension(boolean val) { this.hypertension = val; return this; }
        public Builder withSmokingStatus(int val) { this.smokingStatus = val; return this; }
        public Builder withAntiplatelet(int val) { this.antiplatelet = val; return this; }
        public Builder withOralAnticoagulant(boolean val) { this.oralAnticoagulant = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public REACHModel build() {
            return new REACHModel(this);
        }
    }
}