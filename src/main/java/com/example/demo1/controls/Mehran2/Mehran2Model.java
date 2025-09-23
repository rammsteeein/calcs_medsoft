package com.example.demo1.controls.Mehran2;

import javafx.beans.property.*;

public class Mehran2Model {

    private final BooleanProperty hypotension = new SimpleBooleanProperty();
    private final BooleanProperty balloonPump = new SimpleBooleanProperty();
    private final BooleanProperty heartFailure = new SimpleBooleanProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final BooleanProperty anemia = new SimpleBooleanProperty();
    private final BooleanProperty diabetes = new SimpleBooleanProperty();
    private final DoubleProperty contrastVolume = new SimpleDoubleProperty();
    private final DoubleProperty gfr = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    private Mehran2Model(Builder builder) {
        this.hypotension.set(builder.hypotension);
        this.balloonPump.set(builder.balloonPump);
        this.heartFailure.set(builder.heartFailure);
        this.age.set(builder.age);
        this.anemia.set(builder.anemia);
        this.diabetes.set(builder.diabetes);
        this.contrastVolume.set(builder.contrastVolume);
        this.gfr.set(builder.gfr);
        this.result.set(builder.result);
    }

    // --- Геттеры и свойства ---
    public boolean isHypotension() { return hypotension.get(); }
    public void setHypotension(boolean val) { hypotension.set(val); }
    public BooleanProperty hypotensionProperty() { return hypotension; }

    public boolean isBalloonPump() { return balloonPump.get(); }
    public void setBalloonPump(boolean val) { balloonPump.set(val); }
    public BooleanProperty balloonPumpProperty() { return balloonPump; }

    public boolean isHeartFailure() { return heartFailure.get(); }
    public void setHeartFailure(boolean val) { heartFailure.set(val); }
    public BooleanProperty heartFailureProperty() { return heartFailure; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public boolean isAnemia() { return anemia.get(); }
    public void setAnemia(boolean val) { anemia.set(val); }
    public BooleanProperty anemiaProperty() { return anemia; }

    public boolean isDiabetes() { return diabetes.get(); }
    public void setDiabetes(boolean val) { diabetes.set(val); }
    public BooleanProperty diabetesProperty() { return diabetes; }

    public double getContrastVolume() { return contrastVolume.get(); }
    public void setContrastVolume(double val) { contrastVolume.set(val); }
    public DoubleProperty contrastVolumeProperty() { return contrastVolume; }

    public double getGfr() { return gfr.get(); }
    public void setGfr(double val) { gfr.set(val); }
    public DoubleProperty gfrProperty() { return gfr; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        Mehran2Result res = Mehran2Calculator.calc(
                isHypotension(),
                isBalloonPump(),
                isHeartFailure(),
                getAge(),
                isAnemia(),
                isDiabetes(),
                getContrastVolume(),
                getGfr()
        );
        setResult(res.toString());
    }

    // --- Builder ---
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private boolean hypotension;
        private boolean balloonPump;
        private boolean heartFailure;
        private int age;
        private boolean anemia;
        private boolean diabetes;
        private double contrastVolume;
        private double gfr;
        private String result = "";

        public Builder withHypotension(boolean val) { this.hypotension = val; return this; }
        public Builder withBalloonPump(boolean val) { this.balloonPump = val; return this; }
        public Builder withHeartFailure(boolean val) { this.heartFailure = val; return this; }
        public Builder withAge(int val) { this.age = val; return this; }
        public Builder withAnemia(boolean val) { this.anemia = val; return this; }
        public Builder withDiabetes(boolean val) { this.diabetes = val; return this; }
        public Builder withContrastVolume(double val) { this.contrastVolume = val; return this; }
        public Builder withGfr(double val) { this.gfr = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public Mehran2Model build() { return new Mehran2Model(this); }
    }
}
