package com.example.demo1.controls.Caprini;

import javafx.beans.property.*;

public class CapriniModel {

    // --- Возраст ---
    private final IntegerProperty age = new SimpleIntegerProperty();

    // --- Факторы риска ---
    public BooleanProperty varicoseVeins = new SimpleBooleanProperty();
    public BooleanProperty bmiOver25 = new SimpleBooleanProperty();
    public BooleanProperty minorSurgery = new SimpleBooleanProperty();
    public BooleanProperty sepsis = new SimpleBooleanProperty();
    public BooleanProperty lungDisease = new SimpleBooleanProperty();
    public BooleanProperty hormones = new SimpleBooleanProperty();
    public BooleanProperty pregnancyPostpartum = new SimpleBooleanProperty();
    public BooleanProperty priorMiscarriage = new SimpleBooleanProperty();
    public BooleanProperty acuteMI = new SimpleBooleanProperty();
    public BooleanProperty heartFailure = new SimpleBooleanProperty();
    public BooleanProperty bedRestNonSurgical = new SimpleBooleanProperty();
    public BooleanProperty IBD = new SimpleBooleanProperty();
    public BooleanProperty COPD = new SimpleBooleanProperty();
    public BooleanProperty legEdema = new SimpleBooleanProperty();

    public BooleanProperty arthroscopicSurgery = new SimpleBooleanProperty();
    public BooleanProperty malignancy = new SimpleBooleanProperty();
    public BooleanProperty laparoscopicSurgeryOver60min = new SimpleBooleanProperty();
    public BooleanProperty bedRestOver72h = new SimpleBooleanProperty();
    public BooleanProperty limbImmobilization = new SimpleBooleanProperty();
    public BooleanProperty centralLine = new SimpleBooleanProperty();
    public BooleanProperty majorSurgeryOver45min = new SimpleBooleanProperty();

    public BooleanProperty priorVTE = new SimpleBooleanProperty();
    public BooleanProperty familyVTE = new SimpleBooleanProperty();
    public BooleanProperty factorVLeiden = new SimpleBooleanProperty();
    public BooleanProperty prothrombin20210A = new SimpleBooleanProperty();
    public BooleanProperty hyperhomocysteinemia = new SimpleBooleanProperty();
    public BooleanProperty HIT = new SimpleBooleanProperty();
    public BooleanProperty anticardiolipin = new SimpleBooleanProperty();
    public BooleanProperty lupusAnticoagulant = new SimpleBooleanProperty();
    public BooleanProperty otherThrombophilia = new SimpleBooleanProperty();

    public BooleanProperty recentStroke = new SimpleBooleanProperty();
    public BooleanProperty jointReplacement = new SimpleBooleanProperty();
    public BooleanProperty fracture = new SimpleBooleanProperty();
    public BooleanProperty spinalTrauma = new SimpleBooleanProperty();
    public BooleanProperty multipleTrauma = new SimpleBooleanProperty();

    // --- Результаты ---
    private final IntegerProperty score = new SimpleIntegerProperty();
    private final StringProperty risk = new SimpleStringProperty();

    // --- Свойства ---
    public IntegerProperty ageProperty() { return age; }
    public int getAge() { return age.get(); }
    public void setAge(int value) { age.set(value); }
    public IntegerProperty scoreProperty() { return score; }
    public int getScore() { return score.get(); }
    public StringProperty riskProperty() { return risk; }
    public String getRisk() { return risk.get(); }


    public void calc() {
        int ageScore = 0;
        int patientAge = age.get();

        if (patientAge >= 41 && patientAge <= 60) ageScore = 1;
        else if (patientAge >= 61 && patientAge <= 74) ageScore = 2;
        else if (patientAge >= 75) ageScore = 3;

        int totalScore = ageScore;

        if (varicoseVeins.get()) totalScore += 1;
        if (bmiOver25.get()) totalScore += 1;
        if (minorSurgery.get()) totalScore += 1;
        if (sepsis.get()) totalScore += 1;
        if (lungDisease.get()) totalScore += 1;
        if (hormones.get()) totalScore += 1;
        if (pregnancyPostpartum.get()) totalScore += 1;
        if (priorMiscarriage.get()) totalScore += 1;
        if (acuteMI.get()) totalScore += 1;
        if (heartFailure.get()) totalScore += 1;
        if (bedRestNonSurgical.get()) totalScore += 1;
        if (IBD.get()) totalScore += 1;
        if (COPD.get()) totalScore += 1;
        if (legEdema.get()) totalScore += 1;

        // --- Факторы риска 2 балла ---
        if (arthroscopicSurgery.get()) totalScore += 2;
        if (malignancy.get()) totalScore += 2;
        if (laparoscopicSurgeryOver60min.get()) totalScore += 2;
        if (bedRestOver72h.get()) totalScore += 2;
        if (limbImmobilization.get()) totalScore += 2;
        if (centralLine.get()) totalScore += 2;
        if (majorSurgeryOver45min.get()) totalScore += 2;

        // --- Факторы риска 3 балла ---
        if (priorVTE.get()) totalScore += 3;
        if (familyVTE.get()) totalScore += 3;
        if (factorVLeiden.get()) totalScore += 3;
        if (prothrombin20210A.get()) totalScore += 3;
        if (hyperhomocysteinemia.get()) totalScore += 3;
        if (HIT.get()) totalScore += 3;
        if (anticardiolipin.get()) totalScore += 3;
        if (lupusAnticoagulant.get()) totalScore += 3;
        if (otherThrombophilia.get()) totalScore += 3;

        // --- Факторы риска 5 баллов ---
        if (recentStroke.get()) totalScore += 5;
        if (jointReplacement.get()) totalScore += 5;
        if (fracture.get()) totalScore += 5;
        if (spinalTrauma.get()) totalScore += 5;
        if (multipleTrauma.get()) totalScore += 5;

        // --- Определение риска ---
        String riskCategory;
        if (totalScore < 3) riskCategory = "Низкий";
        else if (totalScore <= 4) riskCategory = "Умеренный";
        else if (totalScore <= 6) riskCategory = "Повышенный";
        else if (totalScore <= 8) riskCategory = "Высокий";
        else riskCategory = "Очень высокий";

        score.set(totalScore);
        risk.set(riskCategory);
    }
}
