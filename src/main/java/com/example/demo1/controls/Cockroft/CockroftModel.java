package com.example.demo1.controls.Cockroft;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;
import javafx.beans.property.*;

public class CockroftModel {
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final StringProperty kreatinin = new SimpleStringProperty();
    private final ObjectProperty<Unit> creatininUnit = new SimpleObjectProperty<>(Unit.MKMOL);
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final ObjectProperty<CockroftResult> result = new SimpleObjectProperty<>(new CockroftResult(Double.NaN, null, ""));
    private final StringProperty resultString = new SimpleStringProperty();

    public ObjectProperty<Gender> genderProperty() { return gender; }
    public StringProperty kreatininProperty() { return kreatinin; }
    public ObjectProperty<Unit> creatininUnitProperty() { return creatininUnit; }
    public StringProperty ageProperty() { return age; }
    public StringProperty weightProperty() { return weight; }
    public ObjectProperty<CockroftResult> resultProperty() { return result; }
    public StringProperty resultStringProperty() { return resultString; }

    public void calc() {
        try {
            String kreatStr = kreatinin.get();
            String ageStr = age.get();
            String weightStr = weight.get();

            if (!isNumber(kreatStr) || !isNumber(ageStr) || !isNumber(weightStr)) {
                resultString.set("");
                return;
            }

            Gender genderValue = gender.get();
            if (genderValue == null) {
                resultString.set("");
                return;
            }

            double kreatininValue = Double.parseDouble(kreatStr);
            int ageValue = Integer.parseInt(ageStr);
            double weightValue = Double.parseDouble(weightStr);
            Unit unitValue = creatininUnit.get();

            if (unitValue == Unit.MKMOL) {
                kreatininValue /= 88.4;
            }

            double calcValue = CockroftCalculator.calc(genderValue, kreatininValue, weightValue, ageValue);

            String resultText = String.format("Клиренс креатинина: %.2f мл/мин", calcValue);
            result.set(new CockroftResult(calcValue, Unit.MGDL, resultText));
            resultString.set(resultText);

        } catch (Exception e) {
            resultString.set("Ошибка: " + e.getMessage());
        }
    }

    private boolean isNumber(String s) {
        if (s == null || s.isBlank()) return false;
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
