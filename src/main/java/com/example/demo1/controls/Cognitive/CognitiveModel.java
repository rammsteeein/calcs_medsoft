package com.example.demo1.controls.Cognitive;

import javafx.beans.property.*;

public class CognitiveModel {
    private final BooleanProperty orientation1 = new SimpleBooleanProperty();
    private final BooleanProperty orientation2 = new SimpleBooleanProperty();
    private final BooleanProperty orientation3 = new SimpleBooleanProperty();
    private final BooleanProperty orientation4 = new SimpleBooleanProperty();
    private final BooleanProperty orientation5 = new SimpleBooleanProperty();
    private final BooleanProperty orientation6 = new SimpleBooleanProperty();
    private final BooleanProperty orientation7 = new SimpleBooleanProperty();
    private final BooleanProperty orientation8 = new SimpleBooleanProperty();
    private final BooleanProperty orientation9 = new SimpleBooleanProperty();
    private final BooleanProperty orientation10 = new SimpleBooleanProperty();

    private final BooleanProperty memoryBus = new SimpleBooleanProperty();
    private final BooleanProperty memoryDoor = new SimpleBooleanProperty();
    private final BooleanProperty memoryRose = new SimpleBooleanProperty();

    private final BooleanProperty subtract1 = new SimpleBooleanProperty();
    private final BooleanProperty subtract2 = new SimpleBooleanProperty();
    private final BooleanProperty subtract3 = new SimpleBooleanProperty();
    private final BooleanProperty subtract4 = new SimpleBooleanProperty();
    private final BooleanProperty subtract5 = new SimpleBooleanProperty();

    private final BooleanProperty recallBus = new SimpleBooleanProperty();
    private final BooleanProperty recallDoor = new SimpleBooleanProperty();
    private final BooleanProperty recallRose = new SimpleBooleanProperty();

    private final BooleanProperty speech16 = new SimpleBooleanProperty();
    private final BooleanProperty speech17 = new SimpleBooleanProperty();
    private final BooleanProperty speech18 = new SimpleBooleanProperty();
    private final BooleanProperty speech19 = new SimpleBooleanProperty();
    private final BooleanProperty speech20a = new SimpleBooleanProperty();
    private final BooleanProperty speech20b = new SimpleBooleanProperty();
    private final BooleanProperty speech20c = new SimpleBooleanProperty();
    private final BooleanProperty speech21 = new SimpleBooleanProperty();
    private final BooleanProperty speech22 = new SimpleBooleanProperty();

    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public BooleanProperty orientation1Property() { return orientation1; }
    public BooleanProperty orientation2Property() { return orientation2; }
    public BooleanProperty orientation3Property() { return orientation3; }
    public BooleanProperty orientation4Property() { return orientation4; }
    public BooleanProperty orientation5Property() { return orientation5; }
    public BooleanProperty orientation6Property() { return orientation6; }
    public BooleanProperty orientation7Property() { return orientation7; }
    public BooleanProperty orientation8Property() { return orientation8; }
    public BooleanProperty orientation9Property() { return orientation9; }
    public BooleanProperty orientation10Property() { return orientation10; }

    public BooleanProperty memoryBusProperty() { return memoryBus; }
    public BooleanProperty memoryDoorProperty() { return memoryDoor; }
    public BooleanProperty memoryRoseProperty() { return memoryRose; }

    public BooleanProperty subtract1Property() { return subtract1; }
    public BooleanProperty subtract2Property() { return subtract2; }
    public BooleanProperty subtract3Property() { return subtract3; }
    public BooleanProperty subtract4Property() { return subtract4; }
    public BooleanProperty subtract5Property() { return subtract5; }

    public BooleanProperty recallBusProperty() { return recallBus; }
    public BooleanProperty recallDoorProperty() { return recallDoor; }
    public BooleanProperty recallRoseProperty() { return recallRose; }

    public BooleanProperty speech16Property() { return speech16; }
    public BooleanProperty speech17Property() { return speech17; }
    public BooleanProperty speech18Property() { return speech18; }
    public BooleanProperty speech19Property() { return speech19; }
    public BooleanProperty speech20aProperty() { return speech20a; }
    public BooleanProperty speech20bProperty() { return speech20b; }
    public BooleanProperty speech20cProperty() { return speech20c; }
    public BooleanProperty speech21Property() { return speech21; }
    public BooleanProperty speech22Property() { return speech22; }

    public DoubleProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        CognitiveResult res = CognitiveCalculator.calc(
                orientation1.get(),
                orientation2.get(),
                orientation3.get(),
                orientation4.get(),
                orientation5.get(),
                orientation6.get(),
                orientation7.get(),
                orientation8.get(),
                orientation9.get(),
                orientation10.get(),
                memoryBus.get(),
                memoryDoor.get(),
                memoryRose.get(),
                subtract1.get(),
                subtract2.get(),
                subtract3.get(),
                subtract4.get(),
                subtract5.get(),
                recallBus.get(),
                recallDoor.get(),
                recallRose.get(),
                speech16.get(),
                speech17.get(),
                speech18.get(),
                speech19.get(),
                speech20a.get(),
                speech20b.get(),
                speech20c.get(),
                speech21.get(),
                speech22.get()
        );
        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}

