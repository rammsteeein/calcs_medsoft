package com.example.demo1.controls.POAK_doze;

import com.example.demo1.controls.POAK_doze.POAKResult;

public class POAKCalculator {

    public static POAKResult calc(double kreatinin) {
        if (kreatinin > 60) {
            return new POAKResult("Некорректные входные данные");
        }
        double poak = kreatinin / 10;
        int poakInt = (int) Math.round(poak);
        String result = String.format("1 раз в %d месяца(ев)", poakInt);
        return new POAKResult(result);
    }
}