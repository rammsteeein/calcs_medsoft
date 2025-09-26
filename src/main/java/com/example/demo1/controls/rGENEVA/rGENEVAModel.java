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

    public BooleanProperty prevPEorDVTProperty() { return prevPEorDVT; }
    public IntegerProperty heartRateProperty() { return heartRate; }
    public BooleanProperty surgeryOrFractureProperty() { return surgeryOrFracture; }
    public BooleanProperty hemoptysisProperty() { return hemoptysis; }
    public BooleanProperty activeCancerProperty() { return activeCancer; }
    public BooleanProperty legPainProperty() { return legPain; }
    public BooleanProperty painAndSwellingProperty() { return painAndSwelling; }
    public IntegerProperty ageProperty() { return age; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        rGENEVAResult res = rGENEVACalculator.calc(
                prevPEorDVT.get(),
                heartRate.get(),
                surgeryOrFracture.get(),
                hemoptysis.get(),
                activeCancer.get(),
                legPain.get(),
                painAndSwelling.get(),
                age.get()
        );
        result.set(res.toString());
    }
}
