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

    public IntegerProperty ageProperty() { return age; }
    public IntegerProperty heartRateProperty() { return heartRate; }
    public DoubleProperty oxygenProperty() { return oxygen; }
    public BooleanProperty unilateralLegEdemaProperty() { return unilateralLegEdema; }
    public BooleanProperty hemoptysisProperty() { return hemoptysis; }
    public BooleanProperty recentSurgeryOrTraumaProperty() { return recentSurgeryOrTrauma; }
    public BooleanProperty surgeryWithin4WeeksProperty() { return surgeryWithin4Weeks; }
    public BooleanProperty prevPEorDVTProperty() { return prevPEorDVT; }
    public BooleanProperty hormoneUseProperty() { return hormoneUse; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        PERCResult res = PERCCalculator.calc(
                age.get(),
                heartRate.get(),
                oxygen.get(),
                unilateralLegEdema.get(),
                hemoptysis.get(),
                recentSurgeryOrTrauma.get(),
                surgeryWithin4Weeks.get(),
                prevPEorDVT.get(),
                hormoneUse.get()
        );
        result.set(res.toString());
    }
}
