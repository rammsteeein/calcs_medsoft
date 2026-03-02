package com.example.demo1.controls.Pokrovsky;

public class PokrovskyResult {

    private final int degree;
    private final String interpretation;

    public PokrovskyResult(int degree, String interpretation) {
        this.degree = degree;
        this.interpretation = interpretation;
    }

    public int getDegree() {
        return degree;
    }

    public String getInterpretation() {
        return interpretation;
    }

    @Override
    public String toString() {
        return interpretation;
    }
}