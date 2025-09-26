package com.example.demo1.controls.CDS;

public class CDSResult {
    private final String resultText;

    public CDSResult(String resultText) {
        this.resultText = resultText;
    }

    @Override
    public String toString() {
        return resultText;
    }
}
