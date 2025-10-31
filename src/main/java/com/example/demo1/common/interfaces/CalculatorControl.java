package com.example.demo1.common.interfaces;

public interface CalculatorControl {
    default double getDefaultWidth() {
        return 550;
    }

    default double getDefaultHeight() {
        return 370;
    }
}