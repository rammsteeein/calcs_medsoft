package com.example.demo1.controls.POAK_doze;

public class POAKResult {
    private final int value;
    private final String formatted;

    public POAKResult(int value, String formatted) {
        this.value = value;
        this.formatted = formatted;
    }

    public POAKResult(double value, String formatted) {
        this.value = (int) Math.round(value);
        this.formatted = formatted;
    }

    public int getValue() {
        return value;
    }

    public String getFormatted() {
        return formatted;
    }

    @Override
    public String toString() {
        return formatted;
    }
}
