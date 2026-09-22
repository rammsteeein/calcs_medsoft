package com.example.demo1.controls.APACHEII;
import javafx.beans.property.*;
public class APACHEIIModel {
    private final StringProperty[] criteria=new StringProperty[14];
    private final StringProperty surgery=new SimpleStringProperty();
    private final DoubleProperty resultValue=new SimpleDoubleProperty();
    private final StringProperty result=new SimpleStringProperty();
    public APACHEIIModel(){for(int i=0;i<criteria.length;i++)criteria[i]=new SimpleStringProperty();
    }
    public StringProperty criterionProperty(int i){
        return criteria[i];
    }
    public StringProperty surgeryProperty(){
        return surgery;
    }
    public DoubleProperty resultValueProperty(){
        return resultValue;
    }
    public StringProperty resultProperty(){
        return result;
    }
    public void calc(){
        String[] v=new String[criteria.length];
        for(int i=0;i<v.length;i++)v[i]=criteria[i].get();APACHEIIResult r=APACHEIICalculator.calc(v,surgery.get());
        resultValue.set(r.getValue());result.set(r.toString());
    }
}
