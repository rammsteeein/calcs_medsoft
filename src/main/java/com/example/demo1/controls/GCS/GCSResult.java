package com.example.demo1.controls.GCS;

public class GCSResult {
    private final int total;
    private final String interpretation;

    public GCSResult(int total, String interpretation) {
        this.total = total;
        this.interpretation = interpretation;
    }

    public int getTotal() { return total; }
    public String getInterpretation() { return interpretation; }

    @Override
    public String toString() {
        return total + " баллов — " + interpretation;
    }
}
