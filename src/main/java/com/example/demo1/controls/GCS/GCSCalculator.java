package com.example.demo1.controls.GCS;

public class GCSCalculator {

    public static GCSResult calc(int eyes, int verbal, int motor) {
        int total = eyes + verbal + motor;

        String interpretation;
        if (total >= 13) {
            interpretation = "Лёгкое нарушение сознания";
        } else if (total >= 9) {
            interpretation = "Умеренное нарушение сознания";
        } else {
            interpretation = "Кома, высокий риск летального исхода, показана интубация";
        }

        return new GCSResult(total, interpretation);
    }
}
