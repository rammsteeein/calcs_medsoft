package com.example.demo1.controls.CDS;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CDSModel {
    private final StringProperty appearance = new SimpleStringProperty();
    private final StringProperty eyes = new SimpleStringProperty();
    private final StringProperty mucous = new SimpleStringProperty();
    private final StringProperty tears = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    private CDSModel(Builder builder) {
        this.appearance.set(builder.appearance);
        this.eyes.set(builder.eyes);
        this.mucous.set(builder.mucous);
        this.tears.set(builder.tears);
        this.result.set(builder.result);
    }

    public StringProperty appearanceProperty() { return appearance; }
    public StringProperty eyesProperty() { return eyes; }
    public StringProperty mucousProperty() { return mucous; }
    public StringProperty tearsProperty() { return tears; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            CDSResult res = CDSCalculator.calc(
                    appearance.get(),
                    eyes.get(),
                    mucous.get(),
                    tears.get()
            );
            result.set(res.toString());
        } catch (Exception ex) {
            result.set("Ошибка: " + ex.getMessage());
        }
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String appearance;
        private String eyes;
        private String mucous;
        private String tears;
        private String result = "";

        public Builder withAppearance(String v) { this.appearance = v; return this; }
        public Builder withEyes(String v) { this.eyes = v; return this; }
        public Builder withMucous(String v) { this.mucous = v; return this; }
        public Builder withTears(String v) { this.tears = v; return this; }
        public Builder withResult(String r) { this.result = r; return this; }

        public CDSModel build() { return new CDSModel(this); }
    }
}
