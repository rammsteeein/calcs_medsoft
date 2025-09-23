package com.example.demo1.controls.IDAChronicAnemia;

public class IDAChronicAnemiaCalculator {

    public static IDAChronicAnemiaResult calc(
            double serumIron,       // мкмоль/л
            double TIBC,            // общая железосвязывающая способность, мкмоль/л
            double transferrinSat,  // % насыщения трансферрина (НТЖ)
            double ferritin         // нг/мл
    ) {
        StringBuilder sb = new StringBuilder();
        sb.append("Дифференциальная диагностика:\n");

        // Сывороточное железо
        if (serumIron < 10.7) sb.append("Сывороточное железо ↓\n");
        else if (serumIron > 32.2) sb.append("Сывороточное железо ↑\n");
        else sb.append("Сывороточное железо N\n");

        // ОЖСС / TIBC
        if (TIBC > 90) sb.append("ОЖСС ↑\n");
        else if (TIBC < 46) sb.append("ОЖСС ↓\n");
        else sb.append("ОЖСС N\n");

        // НТЖ
        if (transferrinSat < 17.8) sb.append("НТЖ ↓\n");
        else if (transferrinSat > 43.3) sb.append("НТЖ ↑\n");
        else sb.append("НТЖ N\n");

        // Ферритин
        if (ferritin < 11.0) sb.append("Ферритин ↓\n");
        else if (ferritin > 306.8) sb.append("Ферритин ↑\n");
        else sb.append("Ферритин N\n");

        // Определение вероятной анемии
        if (serumIron < 10.7 && TIBC > 90 && transferrinSat < 17.8 && ferritin < 11.0)
            sb.append("\nВероятна ЖДА");
        else if (serumIron < 10.7 && TIBC <= 90 && transferrinSat >= 17.8 && ferritin >= 11.0)
            sb.append("\nВероятна анемия хронических заболеваний (АХЗ)");
        else
            sb.append("\nНаличие смешанной или неопределённой анемии");

        return new IDAChronicAnemiaResult(sb.toString());
    }
}
