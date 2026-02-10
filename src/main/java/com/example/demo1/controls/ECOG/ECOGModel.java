package com.example.demo1.controls.ECOG;

import javafx.beans.property.*;

public class ECOGModel {

    private final IntegerProperty ecogScore = new SimpleIntegerProperty(-1);
    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public IntegerProperty ecogScoreProperty() {
        return ecogScore;
    }

    public DoubleProperty resultValueProperty() {
        return resultValue;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {
        Integer score = (ecogScore.get() < 0) ? null : ecogScore.get();
        ECOGResult res = ECOGCalculator.calc(score);
        resultValue.set(res.getValue());
        result.set(res.toString());
    }
}





