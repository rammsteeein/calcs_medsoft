package com.example.demo1.controls.PESI;

import com.example.demo1.common.enums.Gender;
import javafx.beans.property.*;

public class PESIModel {

    private final IntegerProperty age = new SimpleIntegerProperty();
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>(Gender.FEMALE);
    private final BooleanProperty hasCancer = new SimpleBooleanProperty();
    private final BooleanProperty hasCHF = new SimpleBooleanProperty();
    private final BooleanProperty hasChronicLungDisease = new SimpleBooleanProperty();
    private final IntegerProperty heartRate = new SimpleIntegerProperty();
    private final IntegerProperty systolicBP = new SimpleIntegerProperty();
    private final IntegerProperty respiratoryRate = new SimpleIntegerProperty();
    private final DoubleProperty temperature = new SimpleDoubleProperty(36.6);
    private final BooleanProperty alteredMentalStatus = new SimpleBooleanProperty();
    private final DoubleProperty oxygenSaturation = new SimpleDoubleProperty(100);
    private final StringProperty result = new SimpleStringProperty();

    public IntegerProperty ageProperty() { return age; }
    public ObjectProperty<Gender> genderProperty() { return gender; }
    public BooleanProperty hasCancerProperty() { return hasCancer; }
    public BooleanProperty hasCHFProperty() { return hasCHF; }
    public BooleanProperty hasChronicLungDiseaseProperty() { return hasChronicLungDisease; }
    public IntegerProperty heartRateProperty() { return heartRate; }
    public IntegerProperty systolicBPProperty() { return systolicBP; }
    public IntegerProperty respiratoryRateProperty() { return respiratoryRate; }
    public DoubleProperty temperatureProperty() { return temperature; }
    public BooleanProperty alteredMentalStatusProperty() { return alteredMentalStatus; }
    public DoubleProperty oxygenSaturationProperty() { return oxygenSaturation; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        PESIResult res = PESICalculator.calc(
                age.get(),
                gender.get(),
                hasCancer.get(),
                hasCHF.get(),
                hasChronicLungDisease.get(),
                heartRate.get(),
                systolicBP.get(),
                respiratoryRate.get(),
                temperature.get(),
                alteredMentalStatus.get(),
                oxygenSaturation.get()
        );
        result.set(res.toString());

        // Красим результат по классу риска
        if (onResultStyled != null) {
            onResultStyled.style(res);
        }
    }

    private transient ResultStylerCallback onResultStyled;

    public void setOnResultStyled(ResultStylerCallback callback) {
        this.onResultStyled = callback;
    }

    public interface ResultStylerCallback {
        void style(PESIResult result);
    }
}
