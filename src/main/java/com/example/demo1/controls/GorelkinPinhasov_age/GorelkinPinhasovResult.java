package com.example.demo1.controls.GorelkinPinhasov_age;

public class GorelkinPinhasovResult {

    private final double kss;
    private final double biologicalAge;
    private final String formatted;

    public GorelkinPinhasovResult(double kss, double biologicalAge, String formatted) {
        this.kss = kss;
        this.biologicalAge = biologicalAge;
        this.formatted = formatted;
    }

    public double getKss() {
        return kss;
    }

    public double getBiologicalAge() {
        return biologicalAge;
    }

    public String getFormatted() {
        return formatted;
    }

    @Override
    public String toString() {
        return formatted;
    }
}