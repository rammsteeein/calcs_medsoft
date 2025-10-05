package com.example.demo1.controls.DLCN;

import javafx.beans.property.*;

public class DLCNModel {

    private final BooleanProperty familyEarlyASCVDorHighLDL = new SimpleBooleanProperty();
    private final BooleanProperty familyTendonXanthomasOrChildHighLDL = new SimpleBooleanProperty();
    private final BooleanProperty personalEarlyCHD = new SimpleBooleanProperty();
    private final BooleanProperty personalEarlyCerebrovascularDisease = new SimpleBooleanProperty();
    private final BooleanProperty tendonXanthomas = new SimpleBooleanProperty();
    private final BooleanProperty cornealArcusUnder45 = new SimpleBooleanProperty();

    private final StringProperty result = new SimpleStringProperty("FH маловероятна");
    private final IntegerProperty score = new SimpleIntegerProperty(0);

    public BooleanProperty familyEarlyASCVDorHighLDLProperty() { return familyEarlyASCVDorHighLDL; }
    public BooleanProperty familyTendonXanthomasOrChildHighLDLProperty() { return familyTendonXanthomasOrChildHighLDL; }
    public BooleanProperty personalEarlyCHDProperty() { return personalEarlyCHD; }
    public BooleanProperty personalEarlyCerebrovascularDiseaseProperty() { return personalEarlyCerebrovascularDisease; }
    public BooleanProperty tendonXanthomasProperty() { return tendonXanthomas; }
    public BooleanProperty cornealArcusUnder45Property() { return cornealArcusUnder45; }

    public StringProperty resultProperty() { return result; }
    public IntegerProperty scoreProperty() { return score; }

    public String getResult() { return result.get(); }
    public int getScore() { return score.get(); }

    public void calc() {
        DLCNResult res = DLCNCalculator.calc(
                familyEarlyASCVDorHighLDL.get(),
                familyTendonXanthomasOrChildHighLDL.get(),
                personalEarlyCHD.get(),
                personalEarlyCerebrovascularDisease.get(),
                tendonXanthomas.get(),
                cornealArcusUnder45.get()
        );

        result.set(res.getDiagnosis());
        score.set(res.getScore());
    }
}
