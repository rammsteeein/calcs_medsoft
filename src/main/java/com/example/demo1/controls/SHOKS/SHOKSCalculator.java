package com.example.demo1.controls.SHOKS;

public class SHOKSCalculator {

    public static SHOKSResult calc(
            int odyshka, int ves, int pereboi, int polozhenie,
            int sheinyeVeny, int hripy, int galop, int pechen,
            int oteki, int SAD) {

        int sum = odyshka + ves + pereboi + polozhenie + sheinyeVeny +
                hripy + galop + pechen + oteki + SAD;

        String functionalClass;
        if (sum <= 3) functionalClass = "I ФК";
        else if (sum <= 6) functionalClass = "II ФК";
        else if (sum <= 9) functionalClass = "III ФК";
        else functionalClass = "IV ФК";

        String result = "Баллы: " + sum + ", ФК: " + functionalClass;
        return new SHOKSResult(result);
    }
}
