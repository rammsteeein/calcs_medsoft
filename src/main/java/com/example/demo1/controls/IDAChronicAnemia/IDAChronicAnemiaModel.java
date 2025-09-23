package com.example.demo1.controls.IDAChronicAnemia;

import javafx.beans.property.*;

public class IDAChronicAnemiaModel {

    private final DoubleProperty serumIron = new SimpleDoubleProperty();
    private final DoubleProperty TIBC = new SimpleDoubleProperty();
    private final DoubleProperty transferrinSat = new SimpleDoubleProperty();
    private final DoubleProperty ferritin = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public double getSerumIron() { return serumIron.get(); }
    public void setSerumIron(double val) { serumIron.set(val); }
    public DoubleProperty serumIronProperty() { return serumIron; }

    public double getTIBC() { return TIBC.get(); }
    public void setTIBC(double val) { TIBC.set(val); }
    public DoubleProperty TIBCProperty() { return TIBC; }

    public double getTransferrinSat() { return transferrinSat.get(); }
    public void setTransferrinSat(double val) { transferrinSat.set(val); }
    public DoubleProperty transferrinSatProperty() { return transferrinSat; }

    public double getFerritin() { return ferritin.get(); }
    public void setFerritin(double val) { ferritin.set(val); }
    public DoubleProperty ferritinProperty() { return ferritin; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        IDAChronicAnemiaResult res = IDAChronicAnemiaCalculator.calc(
                getSerumIron(),
                getTIBC(),
                getTransferrinSat(),
                getFerritin()
        );
        setResult(res.toString());
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private double serumIron, TIBC, transferrinSat, ferritin;
        private String result = "";

        public Builder withSerumIron(double val) { serumIron = val; return this; }
        public Builder withTIBC(double val) { TIBC = val; return this; }
        public Builder withTransferrinSat(double val) { transferrinSat = val; return this; }
        public Builder withFerritin(double val) { ferritin = val; return this; }
        public Builder withResult(String val) { result = val; return this; }

        public IDAChronicAnemiaModel build() {
            IDAChronicAnemiaModel m = new IDAChronicAnemiaModel();
            m.setSerumIron(serumIron);
            m.setTIBC(TIBC);
            m.setTransferrinSat(transferrinSat);
            m.setFerritin(ferritin);
            m.setResult(result);
            return m;
        }
    }
}
