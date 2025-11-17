package com.example.demo1.controls.CKDEPI;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.enums.Unit;
import javafx.beans.property.*;

public class CKDEPIModel {

    private final StringProperty kreatinin = new SimpleStringProperty();
    private final ObjectProperty<Unit> creatininUnit = new SimpleObjectProperty<>(Unit.MKMOL);
    private final ObjectProperty<Gender> gender = new SimpleObjectProperty<>();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty("Введите все данные для расчёта");

    // ===== Расчёты =====
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

    public void tryCalcAuto() {
        if (gender.get() == null ||
                creatininUnit.get() == null ||
                kreatinin.get() == null || kreatinin.get().isEmpty() ||
                age.get() == null || age.get().isEmpty()) {
            result.set("Введите все поля для расчёта");
            return;
        }

        try {
            Double.parseDouble(kreatinin.get());
            Integer.parseInt(age.get());
        } catch (NumberFormatException e) {
            result.set("Некорректный ввод чисел");
            return;
        }

        try {
            calc();
        } catch (Exception ex) {
            result.set("Ошибка: " + ex.getMessage());
        }
    }

    // ===== Геттеры и сеттеры =====

    // Gender
    public Gender getGender() { return gender.get(); }
    public void setGender(Gender value) { gender.set(value); }
    public ObjectProperty<Gender> genderProperty() { return gender; }

    // Creatinin Unit
    public Unit getCreatininUnit() { return creatininUnit.get(); }
    public void setCreatininUnit(Unit value) { creatininUnit.set(value); }
    public ObjectProperty<Unit> creatininUnitProperty() { return creatininUnit; }

    // Kreatinin
    public String getKreatinin() { return kreatinin.get(); }
    public void setKreatinin(String value) { kreatinin.set(value); }
    public StringProperty kreatininProperty() { return kreatinin; }

    // Age
    public String getAge() { return age.get(); }
    public void setAge(String value) { age.set(value); }
    public StringProperty ageProperty() { return age; }

    // Result
    public String getResult() { return result.get(); }
    public void setResult(String value) { result.set(value); }
    public StringProperty resultProperty() { return result; }
}
