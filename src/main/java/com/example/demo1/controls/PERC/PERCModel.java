package com.example.demo1.controls.PERC;

import javafx.beans.property.*;

public class PERCModel {

    private final IntegerProperty age = new SimpleIntegerProperty();
    private final IntegerProperty heartRate = new SimpleIntegerProperty();
    private final IntegerProperty spo2 = new SimpleIntegerProperty();
    private final BooleanProperty unilateralLegEdema = new SimpleBooleanProperty();
    private final BooleanProperty hemoptysis = new SimpleBooleanProperty();
    private final BooleanProperty recentTraumaOrSurgery = new SimpleBooleanProperty();
    private final BooleanProperty previousVte = new SimpleBooleanProperty();
    private final BooleanProperty hormoneUse = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public int getHeartRate() { return heartRate.get(); }
    public void setHeartRate(int val) { heartRate.set(val); }
    public IntegerProperty heartRateProperty() { return heartRate; }

    public int getSpo2() { return spo2.get(); }
    public void setSpo2(int val) { spo2.set(val); }
    public IntegerProperty spo2Property() { return spo2; }

    public boolean isUnilateralLegEdema() { return unilateralLegEdema.get(); }
    public void setUnilateralLegEdema(boolean val) { unilateralLegEdema.set(val); }
    public BooleanProperty unilateralLegEdemaProperty() { return unilateralLegEdema; }

    public boolean isHemoptysis() { return hemoptysis.get(); }
    public void setHemoptysis(boolean val) { hemoptysis.set(val); }
    public BooleanProperty hemoptysisProperty() { return hemoptysis; }

    public boolean isRecentTraumaOrSurgery() { return recentTraumaOrSurgery.get(); }
    public void setRecentTraumaOrSurgery(boolean val) { recentTraumaOrSurgery.set(val); }
    public BooleanProperty recentTraumaOrSurgeryProperty() { return recentTraumaOrSurgery; }

    public boolean isPreviousVte() { return previousVte.get(); }
    public void setPreviousVte(boolean val) { previousVte.set(val); }
    public BooleanProperty previousVteProperty() { return previousVte; }

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
                getSpo2(),
                isUnilateralLegEdema(),
                isHemoptysis(),
                isRecentTraumaOrSurgery(),
                isPreviousVte(),
                isHormoneUse()
        );
        setResult(res.toString());
    }
}
