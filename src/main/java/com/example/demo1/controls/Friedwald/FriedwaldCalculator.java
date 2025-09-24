package com.example.demo1.controls.Friedwald;

public class FriedwaldCalculator {

    /**
     * Расчет холестерина ЛПНП по формуле Фридевальда
     *
     * Формула:
     * LDL = Общий холестерин - (Триглицериды / 2.2) - HDL
     *
     * Где:
     * Общий холестерин  - общий уровень холестерина в крови (ммоль/л)
     * Триглицериды      - уровень триглицеридов (ммоль/л)
     * HDL                - уровень холестерина ЛПВП (ммоль/л)
     * LDL                - уровень холестерина ЛПНП (ммоль/л), рассчитанный
     *
     * Примечания:
     * - Формула применима при концентрации триглицеридов < 4.5 ммоль/л (400 мг/дл)
     * - Для более высоких значений триглицеридов расчет по Фридевальду может быть неточным.
     */

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