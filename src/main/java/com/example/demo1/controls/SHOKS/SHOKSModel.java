package com.example.demo1.controls.SHOKS;

import javafx.beans.property.*;

public class SHOKSModel {

    private final IntegerProperty odyshka = new SimpleIntegerProperty();
    private final IntegerProperty ves = new SimpleIntegerProperty();
    private final IntegerProperty pereboi = new SimpleIntegerProperty();
    private final IntegerProperty polozhenie = new SimpleIntegerProperty();
    private final IntegerProperty sheinyeVeny = new SimpleIntegerProperty();
    private final IntegerProperty hripy = new SimpleIntegerProperty();
    private final IntegerProperty galop = new SimpleIntegerProperty();
    private final IntegerProperty pechen = new SimpleIntegerProperty();
    private final IntegerProperty oteki = new SimpleIntegerProperty();
    private final IntegerProperty SAD = new SimpleIntegerProperty();

    private final StringProperty result = new SimpleStringProperty();

    public int getOdyshka() { return odyshka.get(); }
    public void setOdyshka(int val) { odyshka.set(val); }
    public IntegerProperty odyshkaProperty() { return odyshka; }

    public int getVes() { return ves.get(); }
    public void setVes(int val) { ves.set(val); }
    public IntegerProperty vesProperty() { return ves; }

    public int getPereboi() { return pereboi.get(); }
    public void setPereboi(int val) { pereboi.set(val); }
    public IntegerProperty pereboiProperty() { return pereboi; }

    public int getPolozhenie() { return polozhenie.get(); }
    public void setPolozhenie(int val) { polozhenie.set(val); }
    public IntegerProperty polozhenieProperty() { return polozhenie; }

    public int getSheinyeVeny() { return sheinyeVeny.get(); }
    public void setSheinyeVeny(int val) { sheinyeVeny.set(val); }
    public IntegerProperty sheinyeVenyProperty() { return sheinyeVeny; }

    public int getHripy() { return hripy.get(); }
    public void setHripy(int val) { hripy.set(val); }
    public IntegerProperty hripyProperty() { return hripy; }

    public int getGalop() { return galop.get(); }
    public void setGalop(int val) { galop.set(val); }
    public IntegerProperty galopProperty() { return galop; }

    public int getPechen() { return pechen.get(); }
    public void setPechen(int val) { pechen.set(val); }
    public IntegerProperty pechenProperty() { return pechen; }

    public int getOteki() { return oteki.get(); }
    public void setOteki(int val) { oteki.set(val); }
    public IntegerProperty otekiProperty() { return oteki; }

    public int getSAD() { return SAD.get(); }
    public void setSAD(int val) { SAD.set(val); }
    public IntegerProperty SADProperty() { return SAD; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            SHOKSResult res = SHOKSCalculator.calc(
                    getOdyshka(),
                    getVes(),
                    getPereboi(),
                    getPolozhenie(),
                    getSheinyeVeny(),
                    getHripy(),
                    getGalop(),
                    getPechen(),
                    getOteki(),
                    getSAD()
            );
            setResult(res.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

}
