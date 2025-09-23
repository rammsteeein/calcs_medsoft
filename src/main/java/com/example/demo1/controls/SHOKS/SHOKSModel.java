package com.example.demo1.controls.SHOKS;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SHOKSModel {

    private final StringProperty odyshka = new SimpleStringProperty();
    private final StringProperty ves = new SimpleStringProperty();
    private final StringProperty pereboi = new SimpleStringProperty();
    private final StringProperty polozhenie = new SimpleStringProperty();
    private final StringProperty sheinyeVeny = new SimpleStringProperty();
    private final StringProperty hripy = new SimpleStringProperty();
    private final StringProperty galop = new SimpleStringProperty();
    private final StringProperty pechen = new SimpleStringProperty();
    private final StringProperty oteki = new SimpleStringProperty();
    private final StringProperty SAD = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public void calc() {
        try {
            int sum = Integer.parseInt(getOdyshka()) +
                    Integer.parseInt(getVes()) +
                    Integer.parseInt(getPereboi()) +
                    Integer.parseInt(getPolozhenie()) +
                    Integer.parseInt(getSheinyeVeny()) +
                    Integer.parseInt(getHripy()) +
                    Integer.parseInt(getGalop()) +
                    Integer.parseInt(getPechen()) +
                    Integer.parseInt(getOteki()) +
                    Integer.parseInt(getSAD());

            String functionalClass;
            if (sum <= 3) functionalClass = "I ФК";
            else if (sum <= 6) functionalClass = "II ФК";
            else if (sum <= 9) functionalClass = "III ФК";
            else functionalClass = "IV ФК";

            setResult("Баллы: " + sum + ", ФК: " + functionalClass);
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    // Геттеры и сеттеры
    public String getOdyshka() { return odyshka.get(); }
    public void setOdyshka(String value) { odyshka.set(value); }
    public StringProperty odyshkaProperty() { return odyshka; }

    public String getVes() { return ves.get(); }
    public void setVes(String value) { ves.set(value); }
    public StringProperty vesProperty() { return ves; }

    public String getPereboi() { return pereboi.get(); }
    public void setPereboi(String value) { pereboi.set(value); }
    public StringProperty pereboiProperty() { return pereboi; }

    public String getPolozhenie() { return polozhenie.get(); }
    public void setPolozhenie(String value) { polozhenie.set(value); }
    public StringProperty polozhenieProperty() { return polozhenie; }

    public String getSheinyeVeny() { return sheinyeVeny.get(); }
    public void setSheinyeVeny(String value) { sheinyeVeny.set(value); }
    public StringProperty sheinyeVenyProperty() { return sheinyeVeny; }

    public String getHripy() { return hripy.get(); }
    public void setHripy(String value) { hripy.set(value); }
    public StringProperty hripyProperty() { return hripy; }

    public String getGalop() { return galop.get(); }
    public void setGalop(String value) { galop.set(value); }
    public StringProperty galopProperty() { return galop; }

    public String getPechen() { return pechen.get(); }
    public void setPechen(String value) { pechen.set(value); }
    public StringProperty pechenProperty() { return pechen; }

    public String getOteki() { return oteki.get(); }
    public void setOteki(String value) { oteki.set(value); }
    public StringProperty otekiProperty() { return oteki; }

    public String getSAD() { return SAD.get(); }
    public void setSAD(String value) { SAD.set(value); }
    public StringProperty SADProperty() { return SAD; }

    public String getResult() { return result.get(); }
    public void setResult(String value) { result.set(value); }
    public StringProperty resultProperty() { return result; }
}