package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;
import javafx.beans.property.*;

public class CKDEPIModel {
    private final StringProperty kreatinin = new SimpleStringProperty();
    private final ObjectProperty<Unit> creatininUnit = new SimpleObjectProperty<>(Unit.MKMOL);
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();


    public void calc() {
        double scr = Double.parseDouble(kreatinin.get());
        int ageVal = Integer.parseInt(age.get());
        CKDEPIResult res = CKDEPICalculator.calc(
                gender.get(),
                scr,
                creatininUnit.get(),
                ageVal
        );
        result.set(res.getFormatted() + "\n" + res.getInterpretation());
    }

    public StringProperty kreatininProperty() { return kreatinin; }
    public ObjectProperty<Unit> creatininUnitProperty() { return creatininUnit; }
    public ObjectProperty<Gender> genderProperty() { return gender; }
    public StringProperty ageProperty() { return age; }
    public StringProperty resultProperty() { return result; }
}
