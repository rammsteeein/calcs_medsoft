package com.example.demo1.controls.FIB4;

public class FIB4Calculator {
    public static FIB4Result calc(int age, double ast, double alt, double platelets) {
        if (alt <= 0 || platelets <= 0) {
            return new FIB4Result("Ошибка: некорректные данные");
        }

        double fib4 = (age * ast) / (platelets * Math.sqrt(alt));
        String result = String.format("FIB-4 = %.3f", fib4);

        return new FIB4Result(result);
    }
}