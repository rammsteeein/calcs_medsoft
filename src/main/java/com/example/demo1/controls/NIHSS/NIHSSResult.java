package com.example.demo1.controls.NIHSS;

public class NIHSSResult {
    private final int value;
    private final String text;

    public NIHSSResult(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() { return value; }
    public String getText() { return text; }
}
