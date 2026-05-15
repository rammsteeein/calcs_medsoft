package com.example.demo1.controls.MoCA;

import javafx.beans.property.*;

public class MoCAModel {

    private final BooleanProperty trailMaking = new SimpleBooleanProperty();
    private final BooleanProperty cube = new SimpleBooleanProperty();

    private final BooleanProperty clockContour = new SimpleBooleanProperty();
    private final BooleanProperty clockNumbers = new SimpleBooleanProperty();
    private final BooleanProperty clockHands = new SimpleBooleanProperty();

    private final BooleanProperty lion = new SimpleBooleanProperty();
    private final BooleanProperty rhino = new SimpleBooleanProperty();
    private final BooleanProperty camel = new SimpleBooleanProperty();

    private final BooleanProperty attentionForward = new SimpleBooleanProperty();
    private final BooleanProperty attentionBackward = new SimpleBooleanProperty();
    private final BooleanProperty attentionLetterA = new SimpleBooleanProperty();

    private final BooleanProperty subtract1 = new SimpleBooleanProperty();
    private final BooleanProperty subtract2 = new SimpleBooleanProperty();
    private final BooleanProperty subtract3 = new SimpleBooleanProperty();
    private final BooleanProperty subtract4 = new SimpleBooleanProperty();
    private final BooleanProperty subtract5 = new SimpleBooleanProperty();

    private final BooleanProperty sentence1 = new SimpleBooleanProperty();
    private final BooleanProperty sentence2 = new SimpleBooleanProperty();

    private final BooleanProperty fluency = new SimpleBooleanProperty();

    private final BooleanProperty abstraction1 = new SimpleBooleanProperty();
    private final BooleanProperty abstraction2 = new SimpleBooleanProperty();

    private final BooleanProperty recallFace = new SimpleBooleanProperty();
    private final BooleanProperty recallVelvet = new SimpleBooleanProperty();
    private final BooleanProperty recallChurch = new SimpleBooleanProperty();
    private final BooleanProperty recallDaisy = new SimpleBooleanProperty();
    private final BooleanProperty recallRed = new SimpleBooleanProperty();

    private final BooleanProperty orientationDate = new SimpleBooleanProperty();
    private final BooleanProperty orientationMonth = new SimpleBooleanProperty();
    private final BooleanProperty orientationYear = new SimpleBooleanProperty();
    private final BooleanProperty orientationDay = new SimpleBooleanProperty();
    private final BooleanProperty orientationPlace = new SimpleBooleanProperty();
    private final BooleanProperty orientationCity = new SimpleBooleanProperty();

    private final BooleanProperty lowEducation = new SimpleBooleanProperty();

    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public BooleanProperty trailMakingProperty() { return trailMaking; }
    public BooleanProperty cubeProperty() { return cube; }

    public BooleanProperty clockContourProperty() { return clockContour; }
    public BooleanProperty clockNumbersProperty() { return clockNumbers; }
    public BooleanProperty clockHandsProperty() { return clockHands; }

    public BooleanProperty lionProperty() { return lion; }
    public BooleanProperty rhinoProperty() { return rhino; }
    public BooleanProperty camelProperty() { return camel; }

    public BooleanProperty attentionForwardProperty() { return attentionForward; }
    public BooleanProperty attentionBackwardProperty() { return attentionBackward; }
    public BooleanProperty attentionLetterAProperty() { return attentionLetterA; }

    public BooleanProperty subtract1Property() { return subtract1; }
    public BooleanProperty subtract2Property() { return subtract2; }
    public BooleanProperty subtract3Property() { return subtract3; }
    public BooleanProperty subtract4Property() { return subtract4; }
    public BooleanProperty subtract5Property() { return subtract5; }

    public BooleanProperty sentence1Property() { return sentence1; }
    public BooleanProperty sentence2Property() { return sentence2; }

    public BooleanProperty fluencyProperty() { return fluency; }

    public BooleanProperty abstraction1Property() { return abstraction1; }
    public BooleanProperty abstraction2Property() { return abstraction2; }

    public BooleanProperty recallFaceProperty() { return recallFace; }
    public BooleanProperty recallVelvetProperty() { return recallVelvet; }
    public BooleanProperty recallChurchProperty() { return recallChurch; }
    public BooleanProperty recallDaisyProperty() { return recallDaisy; }
    public BooleanProperty recallRedProperty() { return recallRed; }

    public BooleanProperty orientationDateProperty() { return orientationDate; }
    public BooleanProperty orientationMonthProperty() { return orientationMonth; }
    public BooleanProperty orientationYearProperty() { return orientationYear; }
    public BooleanProperty orientationDayProperty() { return orientationDay; }
    public BooleanProperty orientationPlaceProperty() { return orientationPlace; }
    public BooleanProperty orientationCityProperty() { return orientationCity; }

    public BooleanProperty lowEducationProperty() { return lowEducation; }

    public DoubleProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }

    public void calc() {

        MoCAResult res = MoCACalculator.calc(

                trailMaking.get(),
                cube.get(),

                clockContour.get(),
                clockNumbers.get(),
                clockHands.get(),

                lion.get(),
                rhino.get(),
                camel.get(),

                attentionForward.get(),
                attentionBackward.get(),
                attentionLetterA.get(),

                subtract1.get(),
                subtract2.get(),
                subtract3.get(),
                subtract4.get(),
                subtract5.get(),

                sentence1.get(),
                sentence2.get(),

                fluency.get(),

                abstraction1.get(),
                abstraction2.get(),

                recallFace.get(),
                recallVelvet.get(),
                recallChurch.get(),
                recallDaisy.get(),
                recallRed.get(),

                orientationDate.get(),
                orientationMonth.get(),
                orientationYear.get(),
                orientationDay.get(),
                orientationPlace.get(),
                orientationCity.get(),

                lowEducation.get()
        );

        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}