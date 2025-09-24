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

    private SHOKSModel(Builder builder) {
        this.odyshka.set(builder.odyshka);
        this.ves.set(builder.ves);
        this.pereboi.set(builder.pereboi);
        this.polozhenie.set(builder.polozhenie);
        this.sheinyeVeny.set(builder.sheinyeVeny);
        this.hripy.set(builder.hripy);
        this.galop.set(builder.galop);
        this.pechen.set(builder.pechen);
        this.oteki.set(builder.oteki);
        this.SAD.set(builder.SAD);
        this.result.set(builder.result);
    }

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

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private int odyshka = 0;
        private int ves = 0;
        private int pereboi = 0;
        private int polozhenie = 0;
        private int sheinyeVeny = 0;
        private int hripy = 0;
        private int galop = 0;
        private int pechen = 0;
        private int oteki = 0;
        private int SAD = 0;
        private String result = "";

        public Builder withOdyshka(int val) { this.odyshka = val; return this; }
        public Builder withVes(int val) { this.ves = val; return this; }
        public Builder withPereboi(int val) { this.pereboi = val; return this; }
        public Builder withPolozhenie(int val) { this.polozhenie = val; return this; }
        public Builder withSheinyeVeny(int val) { this.sheinyeVeny = val; return this; }
        public Builder withHripy(int val) { this.hripy = val; return this; }
        public Builder withGalop(int val) { this.galop = val; return this; }
        public Builder withPechen(int val) { this.pechen = val; return this; }
        public Builder withOteki(int val) { this.oteki = val; return this; }
        public Builder withSAD(int val) { this.SAD = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public SHOKSModel build() { return new SHOKSModel(this); }
    }
}
