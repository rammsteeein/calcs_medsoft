package com.example.demo1.common.services;

import javafx.scene.control.TextInputControl;


public final class ResultStyler {
    public enum Zone { LOW, GRAY, HIGH, ERROR }

    private ResultStyler() {}

    private static final String PALE_GREEN = "#ccffcc";
    private static final String PALE_RED   = "#ffcccc";
    private static final String PALE_YELLOW= "#fff2cc";
    private static final String PALE_GRAY  = "#f0f0f0";

    private static void applyColor(TextInputControl control, String color) {
        if (control == null) return;
        // Применяем обо две CSS-свойства — на всякий случай для разных скинов
        control.setStyle(String.format("-fx-control-inner-background: %s; -fx-background-color: %s;", color, color));
    }

    public static void applyStyle(TextInputControl control, Zone zone) {
        if (control == null) return;
        switch (zone) {
            case LOW:   applyColor(control, PALE_GREEN); break;
            case HIGH:  applyColor(control, PALE_RED);   break;
            case GRAY:  applyColor(control, PALE_YELLOW);break;
            default:    applyColor(control, PALE_GRAY);  break;
        }
    }

    public static Zone zoneFromValue(double value, double lowThreshold, double highThreshold) {
        if (Double.isNaN(value)) return Zone.ERROR;
        if (value < lowThreshold) return Zone.LOW;
        if (value > highThreshold) return Zone.HIGH;
        return Zone.GRAY;
    }

    public static void applyStyleForValue(TextInputControl control, double value, double lowThreshold, double highThreshold) {
        applyStyle(control, zoneFromValue(value, lowThreshold, highThreshold));
    }
}