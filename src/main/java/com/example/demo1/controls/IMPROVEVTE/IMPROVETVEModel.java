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

    public BooleanProperty priorVTEProperty() { return priorVTE; }
    public BooleanProperty knownThrombophiliaProperty() { return knownThrombophilia; }
    public BooleanProperty lowerLimbParalysisProperty() { return lowerLimbParalysis; }
    public BooleanProperty activeCancerProperty() { return activeCancer; }
    public BooleanProperty immobilization7DaysProperty() { return immobilization7Days; }
    public BooleanProperty ICUstayProperty() { return ICUstay; }
    public IntegerProperty ageProperty() { return age; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        IMPROVEResult res = IMPROVETVECalculator.calc(
                priorVTE.get(),
                knownThrombophilia.get(),
                lowerLimbParalysis.get(),
                activeCancer.get(),
                immobilization7Days.get(),
                ICUstay.get(),
                age.get()
        );
        result.set(res.toString());
    }
}
