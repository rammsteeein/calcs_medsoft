package com.example.demo1.controls.GCS;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class GCSModel {

    private final IntegerProperty eyes = new SimpleIntegerProperty(0);
    private final IntegerProperty verbal = new SimpleIntegerProperty(0);
    private final IntegerProperty motor = new SimpleIntegerProperty(0);
    private final StringProperty result = new SimpleStringProperty("Выберите значения");
    private final IntegerProperty resultValue = new SimpleIntegerProperty(0);

    private final Map<String, Integer> eyesMap = new HashMap<>();
    private final Map<String, Integer> verbalMap = new HashMap<>();
    private final Map<String, Integer> motorMap = new HashMap<>();

    public GCSModel() {
        eyesMap.put("4 — спонтанно", 4);
        eyesMap.put("3 — на голос", 3);
        eyesMap.put("2 — на болевой стимул", 2);
        eyesMap.put("1 — нет", 1);

        verbalMap.put("5 — ориентирован, отвечает адекватно", 5);
        verbalMap.put("4 — спутанная речь", 4);
        verbalMap.put("3 — отдельные слова", 3);
        verbalMap.put("2 — звуки (мычание, стоны)", 2);
        verbalMap.put("1 — нет", 1);

        motorMap.put("6 — выполняет команды", 6);
        motorMap.put("5 — локализует болевой стимул", 5);
        motorMap.put("4 — отдёргивает конечность", 4);
        motorMap.put("3 — сгибание (децеребрационная реакция)", 3);
        motorMap.put("2 — разгибание (декапитационная реакция)", 2);
        motorMap.put("1 — нет", 1);
    }

    public void setEyesText(String text) { eyes.set(eyesMap.getOrDefault(text, 0)); calc(); }
    public void setVerbalText(String text) { verbal.set(verbalMap.getOrDefault(text, 0)); calc(); }
    public void setMotorText(String text) { motor.set(motorMap.getOrDefault(text, 0)); calc(); }

    public void calc() {
        int total = eyes.get() + verbal.get() + motor.get();
        resultValue.set(total);
        result.set("Сумма баллов: " + total);
    }

    public IntegerProperty eyesProperty() { return eyes; }
    public IntegerProperty verbalProperty() { return verbal; }
    public IntegerProperty motorProperty() { return motor; }
    public StringProperty resultProperty() { return result; }
    public IntegerProperty resultValueProperty() { return resultValue; }
}
