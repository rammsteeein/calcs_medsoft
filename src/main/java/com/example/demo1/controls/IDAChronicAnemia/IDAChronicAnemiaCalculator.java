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
            double serumIron,       // мкмоль/л
            double TIBC,            // общая железосвязывающая способность, мкмоль/л
            double transferrinSat,  // % насыщения трансферрина (НТЖ)
            double ferritin         // нг/мл
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("Дифференциальная диагностика:\n");

        if (serumIron < 10.7) sb.append("Сывороточное железо ↓\n");
        else if (serumIron > 32.2) sb.append("Сывороточное железо ↑\n");
        else sb.append("Сывороточное железо N\n");

        if (TIBC > 90) sb.append("ОЖСС ↑\n");
        else if (TIBC < 46) sb.append("ОЖСС ↓\n");
        else sb.append("ОЖСС N\n");

        if (transferrinSat < 17.8) sb.append("НТЖ ↓\n");
        else if (transferrinSat > 43.3) sb.append("НТЖ ↑\n");
        else sb.append("НТЖ N\n");

        if (ferritin < 11.0) sb.append("Ферритин ↓\n");
        else if (ferritin > 306.8) sb.append("Ферритин ↑\n");
        else sb.append("Ферритин N\n");

        if (serumIron < 10.7 && TIBC > 90 && transferrinSat < 17.8 && ferritin < 11.0)
            sb.append("\nВероятна ЖДА");
        else if (serumIron < 10.7 && TIBC <= 90 && transferrinSat >= 17.8 && ferritin >= 11.0)
            sb.append("\nВероятна анемия хронических заболеваний (АХЗ)");
        else
            sb.append("\nНаличие смешанной или неопределённой анемии");

        return new IDAChronicAnemiaResult(sb.toString());
    }
}
