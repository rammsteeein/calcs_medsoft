package com.example.demo1.controls.POAK_doze;

import com.example.demo1.controls.POAK_doze.POAKResult;

public class POAKCalculator {

    /**
     * Выбор дозы ПОАК в зависимости от клиренса креатинина;
     *
     * Формула:
     * N = клиренс креатинина / 10
     *
     * Условия применения:
     * - Применимо только, если клиренс креатинина < 60 мл/мин
     *
     * Где:
     * клиренс креатинина - скорость клубочковой фильтрации (мл/мин), рассчитанная по CKD-EPI или Cockcroft-Gault
     * N                  - количество месяцев между наблюдениями
     *
     * Примечания:
     * - Формула используется для планирования периодичности наблюдения за пациентами с хронической болезнью почек.
     * - Значение N округляется до целого числа месяцев.
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