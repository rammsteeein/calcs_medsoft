package com.example.demo1.controls.DASI;

import javafx.beans.property.*;

public class DASIModel {

    private final BooleanProperty selfCare = new SimpleBooleanProperty();
    private final BooleanProperty walkIndoors = new SimpleBooleanProperty();
    private final BooleanProperty walk1to2Blocks = new SimpleBooleanProperty();
    private final BooleanProperty climbStairsOrHill = new SimpleBooleanProperty();
    private final BooleanProperty runShortDistance = new SimpleBooleanProperty();
    private final BooleanProperty lightHousework = new SimpleBooleanProperty();
    private final BooleanProperty moderateHousework = new SimpleBooleanProperty();
    private final BooleanProperty heavyHousework = new SimpleBooleanProperty();
    private final BooleanProperty yardWork = new SimpleBooleanProperty();
    private final BooleanProperty sexualActivity = new SimpleBooleanProperty();
    private final BooleanProperty moderateRecreation = new SimpleBooleanProperty();
    private final BooleanProperty strenuousSports = new SimpleBooleanProperty();

    private final ObjectProperty<DASIResult> result = new SimpleObjectProperty<>();

    public BooleanProperty selfCareProperty() { return selfCare; }
    public BooleanProperty walkIndoorsProperty() { return walkIndoors; }
    public BooleanProperty walk1to2BlocksProperty() { return walk1to2Blocks; }
    public BooleanProperty climbStairsOrHillProperty() { return climbStairsOrHill; }
    public BooleanProperty runShortDistanceProperty() { return runShortDistance; }
    public BooleanProperty lightHouseworkProperty() { return lightHousework; }
    public BooleanProperty moderateHouseworkProperty() { return moderateHousework; }
    public BooleanProperty heavyHouseworkProperty() { return heavyHousework; }
    public BooleanProperty yardWorkProperty() { return yardWork; }
    public BooleanProperty sexualActivityProperty() { return sexualActivity; }
    public BooleanProperty moderateRecreationProperty() { return moderateRecreation; }
    public BooleanProperty strenuousSportsProperty() { return strenuousSports; }

    public ObjectProperty<DASIResult> resultProperty() { return result; }

    public void calc() {
        DASIResult res = DASICalculator.calc(
                selfCare.get(),
                walkIndoors.get(),
                walk1to2Blocks.get(),
                climbStairsOrHill.get(),
                runShortDistance.get(),
                lightHousework.get(),
                moderateHousework.get(),
                heavyHousework.get(),
                yardWork.get(),
                sexualActivity.get(),
                moderateRecreation.get(),
                strenuousSports.get()
        );
        result.set(res);
    }
}
