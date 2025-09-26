package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;
import javafx.beans.property.*;

public class CockroftModel {
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final StringProperty kreatinin = new SimpleStringProperty();
    private final ObjectProperty<Unit> creatininUnit = new SimpleObjectProperty<>();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final ObjectProperty<CockroftResult> result = new SimpleObjectProperty<>();

    public ObjectProperty<Gender> genderProperty() { return gender; }
    public StringProperty kreatininProperty() { return kreatinin; }
    public ObjectProperty<Unit> creatininUnitProperty() { return creatininUnit; }
    public StringProperty ageProperty() { return age; }
    public StringProperty weightProperty() { return weight; }
    public ObjectProperty<CockroftResult> resultProperty() { return result; }

    public void calc() {
        try {
            Gender genderValue = gender.get();
            double kreatininValue = Double.parseDouble(kreatinin.get());
            Unit unitValue = creatininUnit.get();
            int ageValue = Integer.parseInt(age.get());
            double weightValue = Double.parseDouble(weight.get());

            double calcValue = CockroftCalculator.calc(genderValue, kreatininValue, weightValue, ageValue);

            result.set(new CockroftResult(
                    calcValue,
                    unitValue,
                    String.format("Клиренс креатинина: %.2f мл/мин", calcValue)
            ));
        } catch (Exception e) {
            result.set(new CockroftResult(Double.NaN, null, "Ошибка: " + e.getMessage()));
        }
    }
}
