package com.example.demo1.controls.GRACE;

public class GRACEResult {
    private final String resultText;

    public GRACEResult(String resultText) {
        this.resultText = resultText;
    }

    @Override
    public String toString() {
        return resultText;
    }
}