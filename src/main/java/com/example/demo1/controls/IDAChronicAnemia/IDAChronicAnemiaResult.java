package com.example.demo1.controls.IDAChronicAnemia;

public class IDAChronicAnemiaResult {
    private final String interpretation;
    private final String conclusion;

    public IDAChronicAnemiaResult(String interpretation, String conclusion) {
        this.interpretation = interpretation;
        this.conclusion = conclusion;
    }

    public String getInterpretation() { return interpretation; }
    public String getConclusion() { return conclusion; }

    @Override
    public String toString() {
        return interpretation + "\nЗаключение: " + conclusion;
    }
}
