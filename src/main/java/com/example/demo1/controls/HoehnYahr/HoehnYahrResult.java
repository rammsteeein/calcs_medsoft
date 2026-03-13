package com.example.demo1.controls.HoehnYahr;

public class HoehnYahrResult {

    private final int stage;
    private final String interpretation;

    public HoehnYahrResult(int stage, String interpretation) {
        this.stage = stage;
        this.interpretation = interpretation;
    }

    public int getStage() {
        return stage;
    }

    public String getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return String.format(
                "Стадия по шкале Хен и Яра: %d%nИнтерпретация: %s",
                stage,
                interpretation
        );
    }
}