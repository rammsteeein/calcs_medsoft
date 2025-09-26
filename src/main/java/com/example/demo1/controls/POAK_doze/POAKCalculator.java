package com.example.demo1.controls.POAK_doze;

/**
 * Выбор дозы ПОАК в зависимости от клиренса креатинина.
 *
 * Формула:
 * N = клиренс креатинина / 10
 *
 * Условия применения:
 * - Используется только при клиренсе креатинина < 60 мл/мин
 *
 * Где:
 * клиренс креатинина — скорость клубочковой фильтрации (мл/мин),
 *                      рассчитанная по CKD-EPI или Cockcroft-Gault
 * N                  — количество месяцев между наблюдениями
 *
 * Примечания:
 * - Значение N округляется до ближайшего целого числа.
 * - Формула нужна для планирования периодичности наблюдения
 *   за пациентами с ХБП.
 */
public class POAKCalculator {

    public static POAKResult calc(double clearance) {
        if (clearance > 60) {
            return new POAKResult(Double.NaN, "Некорректные входные данные");
        }

        double raw = clearance / 10;
        int rounded = (int) Math.round(raw);

        String formatted = String.format("1 раз в %d месяца(ев)", rounded);
        return new POAKResult(rounded, formatted);
    }
}
