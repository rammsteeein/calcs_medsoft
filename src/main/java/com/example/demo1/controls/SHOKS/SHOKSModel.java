package com.example.demo1.controls.SHOKS;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SHOKSModel {

    // явно инициализируем нулями
    private final IntegerProperty odyshka = new SimpleIntegerProperty(0);
    private final IntegerProperty ves = new SimpleIntegerProperty(0);
    private final IntegerProperty pereboi = new SimpleIntegerProperty(0);
    private final IntegerProperty polozhenie = new SimpleIntegerProperty(0);
    private final IntegerProperty sheinyeVeny = new SimpleIntegerProperty(0);
    private final IntegerProperty hripy = new SimpleIntegerProperty(0);
    private final IntegerProperty galop = new SimpleIntegerProperty(0);
    private final IntegerProperty pechen = new SimpleIntegerProperty(0);
    private final IntegerProperty oteki = new SimpleIntegerProperty(0);
    private final IntegerProperty SAD = new SimpleIntegerProperty(0);

    private final StringProperty result = new SimpleStringProperty("");

    public IntegerProperty odyshkaProperty() { return odyshka; }
    public IntegerProperty vesProperty() { return ves; }
    public IntegerProperty pereboiProperty() { return pereboi; }
    public IntegerProperty polozhenieProperty() { return polozhenie; }
    public IntegerProperty sheinyeVenyProperty() { return sheinyeVeny; }
    public IntegerProperty hripyProperty() { return hripy; }
    public IntegerProperty galopProperty() { return galop; }
    public IntegerProperty pechenProperty() { return pechen; }
    public IntegerProperty otekiProperty() { return oteki; }
    public IntegerProperty SADProperty() { return SAD; }

    public StringProperty resultProperty() { return result; }

    // удобный геттер суммы (используем в контроле)
    public int getTotalScore() {
        return odyshka.get() + ves.get() + pereboi.get() + polozhenie.get() +
                sheinyeVeny.get() + hripy.get() + galop.get() + pechen.get() +
                oteki.get() + SAD.get();
    }

    // пишет resultProperty удобным строковым представлением
    public void calc() {
        SHOKSResult res = SHOKSCalculator.calc(
                odyshka.get(), ves.get(), pereboi.get(), polozhenie.get(),
                sheinyeVeny.get(), hripy.get(), galop.get(), pechen.get(),
                oteki.get(), SAD.get()
        );
        result.set(res.toString());
    }
}
