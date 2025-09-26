package com.example.demo1.controls.REACH;

import javafx.beans.property.*;

public class REACHModel {

    private final StringProperty age = new SimpleStringProperty();
    private final BooleanProperty peripheralAtherosclerosis = new SimpleBooleanProperty();
    private final BooleanProperty heartFailure = new SimpleBooleanProperty();
    private final BooleanProperty diabetes = new SimpleBooleanProperty();
    private final BooleanProperty hypercholesterolemia = new SimpleBooleanProperty();
    private final BooleanProperty hypertension = new SimpleBooleanProperty();
    private final IntegerProperty smokingStatus = new SimpleIntegerProperty(0);
    private final IntegerProperty antiplatelet = new SimpleIntegerProperty(0);
    private final BooleanProperty oralAnticoagulant = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    public StringProperty ageProperty() { return age; }
    public BooleanProperty peripheralAtherosclerosisProperty() { return peripheralAtherosclerosis; }
    public BooleanProperty heartFailureProperty() { return heartFailure; }
    public BooleanProperty diabetesProperty() { return diabetes; }
    public BooleanProperty hypercholesterolemiaProperty() { return hypercholesterolemia; }
    public BooleanProperty hypertensionProperty() { return hypertension; }
    public IntegerProperty smokingStatusProperty() { return smokingStatus; }
    public IntegerProperty antiplateletProperty() { return antiplatelet; }
    public BooleanProperty oralAnticoagulantProperty() { return oralAnticoagulant; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            int ageVal = Integer.parseInt(age.get());
            REACHResult res = REACHCalculator.calc(
                    ageVal,
                    peripheralAtherosclerosis.get(),
                    heartFailure.get(),
                    diabetes.get(),
                    hypercholesterolemia.get(),
                    hypertension.get(),
                    smokingStatus.get(),
                    antiplatelet.get(),
                    oralAnticoagulant.get()
            );
            result.set(res.toString());
        } catch (Exception e) {
            result.set("Ошибка: " + e.getMessage());
        }
    }
}
