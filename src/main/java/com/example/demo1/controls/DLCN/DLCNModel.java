package com.example.demo1.controls.DLCN;

import javafx.beans.property.*;

public class DLCNModel {

    private final BooleanProperty familyEarlyASCVDorHighLDL = new SimpleBooleanProperty();
    private final BooleanProperty familyTendonXanthomasOrChildHighLDL = new SimpleBooleanProperty();
    private final BooleanProperty personalEarlyCHD = new SimpleBooleanProperty();
    private final BooleanProperty personalEarlyCerebrovascularDisease = new SimpleBooleanProperty();
    private final BooleanProperty tendonXanthomas = new SimpleBooleanProperty();
    private final BooleanProperty cornealArcusUnder45 = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    private DLCNModel(Builder builder) {
        this.familyEarlyASCVDorHighLDL.set(builder.familyEarlyASCVDorHighLDL);
        this.familyTendonXanthomasOrChildHighLDL.set(builder.familyTendonXanthomasOrChildHighLDL);
        this.personalEarlyCHD.set(builder.personalEarlyCHD);
        this.personalEarlyCerebrovascularDisease.set(builder.personalEarlyCerebrovascularDisease);
        this.tendonXanthomas.set(builder.tendonXanthomas);
        this.cornealArcusUnder45.set(builder.cornealArcusUnder45);
        this.result.set(builder.result);
    }

    // --- Геттеры / Сеттеры ---
    public boolean isFamilyEarlyASCVDorHighLDL() { return familyEarlyASCVDorHighLDL.get(); }
    public void setFamilyEarlyASCVDorHighLDL(boolean val) { familyEarlyASCVDorHighLDL.set(val); }
    public BooleanProperty familyEarlyASCVDorHighLDLProperty() { return familyEarlyASCVDorHighLDL; }

    public boolean isFamilyTendonXanthomasOrChildHighLDL() { return familyTendonXanthomasOrChildHighLDL.get(); }
    public void setFamilyTendonXanthomasOrChildHighLDL(boolean val) { familyTendonXanthomasOrChildHighLDL.set(val); }
    public BooleanProperty familyTendonXanthomasOrChildHighLDLProperty() { return familyTendonXanthomasOrChildHighLDL; }

    public boolean isPersonalEarlyCHD() { return personalEarlyCHD.get(); }
    public void setPersonalEarlyCHD(boolean val) { personalEarlyCHD.set(val); }
    public BooleanProperty personalEarlyCHDProperty() { return personalEarlyCHD; }

    public boolean isPersonalEarlyCerebrovascularDisease() { return personalEarlyCerebrovascularDisease.get(); }
    public void setPersonalEarlyCerebrovascularDisease(boolean val) { personalEarlyCerebrovascularDisease.set(val); }
    public BooleanProperty personalEarlyCerebrovascularDiseaseProperty() { return personalEarlyCerebrovascularDisease; }

    public boolean isTendonXanthomas() { return tendonXanthomas.get(); }
    public void setTendonXanthomas(boolean val) { tendonXanthomas.set(val); }
    public BooleanProperty tendonXanthomasProperty() { return tendonXanthomas; }

    public boolean isCornealArcusUnder45() { return cornealArcusUnder45.get(); }
    public void setCornealArcusUnder45(boolean val) { cornealArcusUnder45.set(val); }
    public BooleanProperty cornealArcusUnder45Property() { return cornealArcusUnder45; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        DLCNResult res = DLCNCalculator.calc(
                isFamilyEarlyASCVDorHighLDL(),
                isFamilyTendonXanthomasOrChildHighLDL(),
                isPersonalEarlyCHD(),
                isPersonalEarlyCerebrovascularDisease(),
                isTendonXanthomas(),
                isCornealArcusUnder45()
        );
        setResult(res.toString());
    }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private boolean familyEarlyASCVDorHighLDL = false;
        private boolean familyTendonXanthomasOrChildHighLDL = false;
        private boolean personalEarlyCHD = false;
        private boolean personalEarlyCerebrovascularDisease = false;
        private boolean tendonXanthomas = false;
        private boolean cornealArcusUnder45 = false;
        private String result = "";

        public Builder withFamilyEarlyASCVDorHighLDL(boolean val) { this.familyEarlyASCVDorHighLDL = val; return this; }
        public Builder withFamilyTendonXanthomasOrChildHighLDL(boolean val) { this.familyTendonXanthomasOrChildHighLDL = val; return this; }
        public Builder withPersonalEarlyCHD(boolean val) { this.personalEarlyCHD = val; return this; }
        public Builder withPersonalEarlyCerebrovascularDisease(boolean val) { this.personalEarlyCerebrovascularDisease = val; return this; }
        public Builder withTendonXanthomas(boolean val) { this.tendonXanthomas = val; return this; }
        public Builder withCornealArcusUnder45(boolean val) { this.cornealArcusUnder45 = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public DLCNModel build() { return new DLCNModel(this); }
    }
}
