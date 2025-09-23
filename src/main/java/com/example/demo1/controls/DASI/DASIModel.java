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
    private final StringProperty result = new SimpleStringProperty();

    private DASIModel(Builder builder) {
        this.selfCare.set(builder.selfCare);
        this.walkIndoors.set(builder.walkIndoors);
        this.walk1to2Blocks.set(builder.walk1to2Blocks);
        this.climbStairsOrHill.set(builder.climbStairsOrHill);
        this.runShortDistance.set(builder.runShortDistance);
        this.lightHousework.set(builder.lightHousework);
        this.moderateHousework.set(builder.moderateHousework);
        this.heavyHousework.set(builder.heavyHousework);
        this.yardWork.set(builder.yardWork);
        this.sexualActivity.set(builder.sexualActivity);
        this.moderateRecreation.set(builder.moderateRecreation);
        this.strenuousSports.set(builder.strenuousSports);
        this.result.set(builder.result);
    }

    public boolean isSelfCare() { return selfCare.get(); }
    public void setSelfCare(boolean val) { selfCare.set(val); }
    public BooleanProperty selfCareProperty() { return selfCare; }

    public boolean isWalkIndoors() { return walkIndoors.get(); }
    public void setWalkIndoors(boolean val) { walkIndoors.set(val); }
    public BooleanProperty walkIndoorsProperty() { return walkIndoors; }

    public boolean isWalk1to2Blocks() { return walk1to2Blocks.get(); }
    public void setWalk1to2Blocks(boolean val) { walk1to2Blocks.set(val); }
    public BooleanProperty walk1to2BlocksProperty() { return walk1to2Blocks; }

    public boolean isClimbStairsOrHill() { return climbStairsOrHill.get(); }
    public void setClimbStairsOrHill(boolean val) { climbStairsOrHill.set(val); }
    public BooleanProperty climbStairsOrHillProperty() { return climbStairsOrHill; }

    public boolean isRunShortDistance() { return runShortDistance.get(); }
    public void setRunShortDistance(boolean val) { runShortDistance.set(val); }
    public BooleanProperty runShortDistanceProperty() { return runShortDistance; }

    public boolean isLightHousework() { return lightHousework.get(); }
    public void setLightHousework(boolean val) { lightHousework.set(val); }
    public BooleanProperty lightHouseworkProperty() { return lightHousework; }

    public boolean isModerateHousework() { return moderateHousework.get(); }
    public void setModerateHousework(boolean val) { moderateHousework.set(val); }
    public BooleanProperty moderateHouseworkProperty() { return moderateHousework; }

    public boolean isHeavyHousework() { return heavyHousework.get(); }
    public void setHeavyHousework(boolean val) { heavyHousework.set(val); }
    public BooleanProperty heavyHouseworkProperty() { return heavyHousework; }

    public boolean isYardWork() { return yardWork.get(); }
    public void setYardWork(boolean val) { yardWork.set(val); }
    public BooleanProperty yardWorkProperty() { return yardWork; }

    public boolean isSexualActivity() { return sexualActivity.get(); }
    public void setSexualActivity(boolean val) { sexualActivity.set(val); }
    public BooleanProperty sexualActivityProperty() { return sexualActivity; }

    public boolean isModerateRecreation() { return moderateRecreation.get(); }
    public void setModerateRecreation(boolean val) { moderateRecreation.set(val); }
    public BooleanProperty moderateRecreationProperty() { return moderateRecreation; }

    public boolean isStrenuousSports() { return strenuousSports.get(); }
    public void setStrenuousSports(boolean val) { strenuousSports.set(val); }
    public BooleanProperty strenuousSportsProperty() { return strenuousSports; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        DASIResult res = DASICalculator.calc(
                isSelfCare(),
                isWalkIndoors(),
                isWalk1to2Blocks(),
                isClimbStairsOrHill(),
                isRunShortDistance(),
                isLightHousework(),
                isModerateHousework(),
                isHeavyHousework(),
                isYardWork(),
                isSexualActivity(),
                isModerateRecreation(),
                isStrenuousSports()
        );
        setResult(res.toString());
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private boolean selfCare;
        private boolean walkIndoors;
        private boolean walk1to2Blocks;
        private boolean climbStairsOrHill;
        private boolean runShortDistance;
        private boolean lightHousework;
        private boolean moderateHousework;
        private boolean heavyHousework;
        private boolean yardWork;
        private boolean sexualActivity;
        private boolean moderateRecreation;
        private boolean strenuousSports;
        private String result = "";

        public Builder withSelfCare(boolean val) { this.selfCare = val; return this; }
        public Builder withWalkIndoors(boolean val) { this.walkIndoors = val; return this; }
        public Builder withWalk1to2Blocks(boolean val) { this.walk1to2Blocks = val; return this; }
        public Builder withClimbStairsOrHill(boolean val) { this.climbStairsOrHill = val; return this; }
        public Builder withRunShortDistance(boolean val) { this.runShortDistance = val; return this; }
        public Builder withLightHousework(boolean val) { this.lightHousework = val; return this; }
        public Builder withModerateHousework(boolean val) { this.moderateHousework = val; return this; }
        public Builder withHeavyHousework(boolean val) { this.heavyHousework = val; return this; }
        public Builder withYardWork(boolean val) { this.yardWork = val; return this; }
        public Builder withSexualActivity(boolean val) { this.sexualActivity = val; return this; }
        public Builder withModerateRecreation(boolean val) { this.moderateRecreation = val; return this; }
        public Builder withStrenuousSports(boolean val) { this.strenuousSports = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public DASIModel build() { return new DASIModel(this); }
    }
}
