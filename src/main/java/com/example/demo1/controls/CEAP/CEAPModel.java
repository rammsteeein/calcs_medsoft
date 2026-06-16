package com.example.demo1.controls.CEAP;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.LinkedHashSet;
import java.util.Set;

public class CEAPModel {

    private final LegData rightLeg = new LegData();
    private final LegData leftLeg = new LegData();
    private final StringProperty result = new SimpleStringProperty();

    public LegData getRightLeg() {
        return rightLeg;
    }

    public LegData getLeftLeg() {
        return leftLeg;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public void calc() {
        CEAPResult res = CEAPCalculator.calc(rightLeg, leftLeg);
        result.set(res.toString());
    }

    public static class LegData {

        private final Set<String> clinical = new LinkedHashSet<>();
        private String symptoms;
        private String etiology;
        private String pathophysiology;
        private final Set<String> superficialVeins = new LinkedHashSet<>();
        private final Set<String> perforatingVeins = new LinkedHashSet<>();
        private final Set<String> deepVeins = new LinkedHashSet<>();
        private String diagnosticLevel;

        public Set<String> getClinical() {
            return clinical;
        }

        public String getSymptoms() {
            return symptoms;
        }

        public void setSymptoms(String symptoms) {
            this.symptoms = symptoms;
        }

        public String getEtiology() {
            return etiology;
        }

        public void setEtiology(String etiology) {
            this.etiology = etiology;
        }

        public String getPathophysiology() {
            return pathophysiology;
        }

        public void setPathophysiology(String pathophysiology) {
            this.pathophysiology = pathophysiology;
        }

        public Set<String> getSuperficialVeins() {
            return superficialVeins;
        }

        public Set<String> getPerforatingVeins() {
            return perforatingVeins;
        }

        public Set<String> getDeepVeins() {
            return deepVeins;
        }

        public String getDiagnosticLevel() {
            return diagnosticLevel;
        }

        public void setDiagnosticLevel(String diagnosticLevel) {
            this.diagnosticLevel = diagnosticLevel;
        }
    }
}
