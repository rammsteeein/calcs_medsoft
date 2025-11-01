package com.example.demo1.controls.POAK_doze;

import javafx.beans.property.*;

public class POAKModel {

    private final StringProperty drug = new SimpleStringProperty();
    private final StringProperty clearance = new SimpleStringProperty();
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final StringProperty creatinine = new SimpleStringProperty();
    private final BooleanProperty bleedingRisk = new SimpleBooleanProperty(false);
    private final StringProperty result = new SimpleStringProperty();

    public POAKModel() {
        // Автоматический пересчёт при любом изменении
        drug.addListener((obs, o, n) -> calc());
        clearance.addListener((obs, o, n) -> calc());
        age.addListener((obs, o, n) -> calc());
        weight.addListener((obs, o, n) -> calc());
        creatinine.addListener((obs, o, n) -> calc());
        bleedingRisk.addListener((obs, o, n) -> calc());
    }

    public String getDrug() {
        return drug.get();
    }

    public void setDrug(String value) {
        drug.set(value);
    }

    public StringProperty drugProperty() {
        return drug;
    }

    public String getClearance() {
        return clearance.get();
    }

    public void setClearance(String value) {
        clearance.set(value);
    }

    public StringProperty clearanceProperty() {
        return clearance;
    }

    public String getAge() {
        return age.get();
    }

    public void setAge(String value) {
        age.set(value);
    }

    public StringProperty ageProperty() {
        return age;
    }

    public String getWeight() {
        return weight.get();
    }

    public void setWeight(String value) {
        weight.set(value);
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public String getCreatinine() {
        return creatinine.get();
    }

    public void setCreatinine(String value) {
        creatinine.set(value);
    }

    public StringProperty creatinineProperty() {
        return creatinine;
    }

    public boolean isBleedingRisk() {
        return bleedingRisk.get();
    }

    public void setBleedingRisk(boolean value) {
        bleedingRisk.set(value);
    }

    public BooleanProperty bleedingRiskProperty() {
        return bleedingRisk;
    }

    public String getResult() {
        return result.get();
    }

    public void setResult(String value) {
        result.set(value);
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {
        try {
            String drugVal = getDrug();
            if (drugVal == null || drugVal.isBlank()) {
                setResult("Выберите препарат");
                return;
            }

            double clearanceVal = parseOrZero(getClearance());
            Integer ageVal = parseOrNull(getAge());
            Double weightVal = parseOrNullD(getWeight());
            Double creatinineVal = parseOrNullD(getCreatinine());
            boolean bleeding = isBleedingRisk();

            String res = POAKCalculator.calc(
                    drugVal,
                    clearanceVal,
                    ageVal != null ? ageVal : 0,
                    weightVal,
                    creatinineVal,
                    bleeding
            );

            setResult(res);
        } catch (Exception ex) {
            setResult("Ошибка: " + ex.getMessage());
        }
    }

    private double parseOrZero(String s) {
        if (s == null || s.isBlank()) return 0;
        return Double.parseDouble(s.trim());
    }

    private Integer parseOrNull(String s) {
        if (s == null || s.isBlank()) return null;
        return Integer.parseInt(s.trim());
    }

    private Double parseOrNullD(String s) {
        if (s == null || s.isBlank()) return null;
        return Double.parseDouble(s.trim());
    }
}
