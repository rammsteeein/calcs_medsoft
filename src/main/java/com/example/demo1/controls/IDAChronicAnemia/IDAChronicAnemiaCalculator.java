package com.example.demo1.controls.IDAChronicAnemia;

public class IDAChronicAnemiaCalculator {

    /**
     * Дифференциальная диагностика железодефицитной анемии (ЖДА) и анемии хронических заболеваний (АХЗ)
     *
     * Показатели и их интерпретация:
     * 1. Сывороточное железо (10,7–32,2 мкмоль/л)
     *    - ЖДА: снижено (↓)
     *    - АХЗ: снижено или нормальное (↓ N)
     * 2. Общая железосвязывающая способность сыворотки (ОЖСС, 46–90 мкмоль/л)
     *    - ЖДА: повышена (↑)
     *    - АХЗ: нормальная или снижена (N или ↓)
     * 3. Насыщение трансферрина железом (НТЖ, 17,8–43,3%)
     *    - ЖДА: снижено (↓)
     *    - АХЗ: может быть нормальным, сниженным или повышенным (N↓↑)
     * 4. Ферритин сыворотки (11,0–306,8 нг/мл)
     *    - ЖДА: снижено (↓)
     *    - АХЗ: нормальное или повышенное (N или ↑)
     *
     * Примечания:
     * - N – нормальное значение показателя
     * - ↓ – снижение показателя
     * - ↑ – повышение показателя
     * - Эти показатели используются для различения ЖДА и АХЗ при лабораторной диагностике.
     */


    public static IDAChronicAnemiaResult calc(
            double serumIron,
            double TIBC,
            double transferrinSat,
            double ferritin
    ) {
        StringBuilder interpretation = new StringBuilder();
        String conclusion;

        interpretation.append("Интерпретация показателей:\n");

        // Сывороточное железо
        if (serumIron < 10.7) interpretation.append("Сывороточное железо ↓\n");
        else if (serumIron > 32.2) interpretation.append("Сывороточное железо ↑\n");
        else interpretation.append("Сывороточное железо N\n");

        // ОЖСС
        if (TIBC > 90) interpretation.append("ОЖСС ↑\n");
        else if (TIBC < 46) interpretation.append("ОЖСС ↓\n");
        else interpretation.append("ОЖСС N\n");

        // НТЖ
        if (transferrinSat < 17.8) interpretation.append("НТЖ ↓\n");
        else if (transferrinSat > 43.3) interpretation.append("НТЖ ↑\n");
        else interpretation.append("НТЖ N\n");

        // Ферритин
        if (ferritin < 11.0) interpretation.append("Ферритин ↓\n");
        else if (ferritin > 306.8) interpretation.append("Ферритин ↑\n");
        else interpretation.append("Ферритин N\n");

        // Заключение
        if (serumIron < 10.7 && TIBC > 90 && transferrinSat < 17.8 && ferritin < 11.0)
            conclusion = "Вероятна железодефицитная анемия (ЖДА)";
        else if (serumIron < 10.7 && TIBC <= 90 && ferritin >= 11.0)
            conclusion = "Вероятна анемия хронических заболеваний (АХЗ)";
        else
            conclusion = "Возможна смешанная или неопределённая анемия";

        return new IDAChronicAnemiaResult(interpretation.toString(), conclusion);
    }
}