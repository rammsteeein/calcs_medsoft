package com.example.demo1.controls.Pokrovsky;

import javafx.beans.property.*;

public class PokrovskyModel {

    private final StringProperty stool = new SimpleStringProperty();
    private final StringProperty vomiting = new SimpleStringProperty();
    private final StringProperty thirst = new SimpleStringProperty();
    private final StringProperty diuresis = new SimpleStringProperty();
    private final StringProperty cramps = new SimpleStringProperty();
    private final StringProperty condition = new SimpleStringProperty();
    private final StringProperty eyes = new SimpleStringProperty();
    private final StringProperty mucous = new SimpleStringProperty();
    private final StringProperty breathing = new SimpleStringProperty();
    private final StringProperty cyanosis = new SimpleStringProperty();
    private final StringProperty turgor = new SimpleStringProperty();
    private final StringProperty pulse = new SimpleStringProperty();
    private final StringProperty pressure = new SimpleStringProperty();
    private final StringProperty voice = new SimpleStringProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final DoubleProperty resultValue = new SimpleDoubleProperty();

    public void calc() {
        PokrovskyResult res = PokrovskyCalculator.calc(
                stool.get(), vomiting.get(), thirst.get(),
                diuresis.get(), cramps.get(), condition.get(),
                eyes.get(), mucous.get(), breathing.get(),
                cyanosis.get(), turgor.get(),
                pulse.get(), pressure.get(), voice.get()
        );

        result.set(res.toString());
        resultValue.set(res.getDegree());
    }

    public StringProperty resultProperty() { return result; }
    public DoubleProperty resultValueProperty() { return resultValue; }

    public StringProperty stoolProperty() { return stool; }
    public StringProperty vomitingProperty() { return vomiting; }
    public StringProperty thirstProperty() { return thirst; }
    public StringProperty diuresisProperty() { return diuresis; }
    public StringProperty crampsProperty() { return cramps; }
    public StringProperty conditionProperty() { return condition; }
    public StringProperty eyesProperty() { return eyes; }
    public StringProperty mucousProperty() { return mucous; }
    public StringProperty breathingProperty() { return breathing; }
    public StringProperty cyanosisProperty() { return cyanosis; }
    public StringProperty turgorProperty() { return turgor; }
    public StringProperty pulseProperty() { return pulse; }
    public StringProperty pressureProperty() { return pressure; }
    public StringProperty voiceProperty() { return voice; }
}