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

    public BooleanProperty prevPEorDVTProperty() { return prevPEorDVT; }
    public BooleanProperty tachycardiaProperty() { return tachycardia; }
    public BooleanProperty surgeryOrImmobilizationProperty() { return surgeryOrImmobilization; }
    public BooleanProperty hemoptysisProperty() { return hemoptysis; }
    public BooleanProperty activeCancerProperty() { return activeCancer; }
    public BooleanProperty clinicalDVTProperty() { return clinicalDVT; }
    public BooleanProperty alternativeLessLikelyProperty() { return alternativeLessLikely; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        WellsResult res = WellsCalculator.calc(
                prevPEorDVT.get(),
                tachycardia.get(),
                surgeryOrImmobilization.get(),
                hemoptysis.get(),
                activeCancer.get(),
                clinicalDVT.get(),
                alternativeLessLikely.get()
        );
        result.set(res.toString());
    }
}
