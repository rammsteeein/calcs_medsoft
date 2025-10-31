package com.example.demo1.controls.Friedwald;

/**
 * Расчёт ХС ЛПНП по формуле Фридвальда
 *
 * Формула:
 * LDL = Общий холестерин - (Триглицериды / 2.2) - ХС ЛПВП
 *
 * Где:
 * - LDL — липопротеины низкой плотности ("плохой" холестерин)
 * - Триглицериды — в ммоль/л
 * - ХС ЛПВП — липопротеины высокой плотности ("хороший" холестерин)
 * - Общий холестерин — суммарное значение всех липидных фракций
 *
 * Примечания:
 * - Применяется при уровне триглицеридов < 4.5 ммоль/л
 * - Значение ХС ЛПНП > 3.0 ммоль/л считается повышенным
 */
public class FriedwaldCalculator {

    public static FriedwaldResult calc(double totalChol, double triglycerides, double hdl) {
        if (totalChol <= 0 || triglycerides <= 0 || hdl <= 0)
            return new FriedwaldResult(-1, "Ошибка: значения должны быть положительными");

        if (triglycerides >= 4.5)
            return new FriedwaldResult(-1, "Ошибка: формула Фридвальда неприменима при ТГ ≥ 4.5 ммоль/л");

        double ldl = totalChol - (triglycerides / 2.2) - hdl;

        String interpretation;
        if (ldl < 1.8)
            interpretation = "Оптимальный уровень ЛПНП";
        else if (ldl < 2.6)
            interpretation = "Целевой уровень (умеренный риск)";
        else if (ldl < 3.0)
            interpretation = "Погранично высокий уровень";
        else
            interpretation = "Высокий уровень ЛПНП";

        return new FriedwaldResult(ldl, interpretation);
    }
}
