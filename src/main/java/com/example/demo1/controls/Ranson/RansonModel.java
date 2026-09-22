package com.example.demo1.controls.Ranson;

import javafx.beans.property.*;
public class RansonModel {
    private final StringProperty[] criteria = new StringProperty[11];
    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();
    public RansonModel() { for (int i = 0; i < criteria.length; i++) criteria[i] = new SimpleStringProperty(); }
    public StringProperty criterionProperty(int index) { return criteria[index]; }
    public DoubleProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }
    public void calc() { String[] values = new String[criteria.length];
        for (int i=0;i<criteria.length;i++) values[i]=criteria[i].get();
        RansonResult r=RansonCalculator.calc(values); resultValue.set(r.getValue());
        result.set(r.toString()); }
}
