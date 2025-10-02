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
    private final IntegerProperty score = new SimpleIntegerProperty(); // числовой результат

    // --- getters / setters / properties
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

    public int getScore() { return score.get(); }
    public void setScore(int val) { score.set(val); }
    public IntegerProperty scoreProperty() { return score; }

    // --- расчет
    public void calc() {
        DLCNResult res = DLCNCalculator.calc(
                isFamilyEarlyASCVDorHighLDL(),
                isFamilyTendonXanthomasOrChildHighLDL(),
                isPersonalEarlyCHD(),
                isPersonalEarlyCerebrovascularDisease(),
                isTendonXanthomas(),
                isCornealArcusUnder45()
        );

        setResult(res.getDiagnosis());
        setScore(res.getScore());

    }
}