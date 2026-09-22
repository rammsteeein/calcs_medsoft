package com.example.demo1.controls.CTSI;

public final class CTSICalculator {

    private CTSICalculator() {
    }

    public static CTSIResult calc(String balthazar, String necrosis) {

        int total = 0;
        int selected = 0;

        if (balthazar != null) {
            selected++;
            total += Integer.parseInt(
                    balthazar.substring(0, balthazar.indexOf('|'))
            );
        }

        if (necrosis != null) {
            selected++;
            total += Integer.parseInt(
                    necrosis.substring(0, necrosis.indexOf('|'))
            );
        }

        if (selected == 0) {
            return new CTSIResult(0, "Выберите хотя бы один параметр");
        }

        if (selected < 2) {
            return new CTSIResult(
                    total,
                    String.format(
                            "Промежуточный результат (%d из 2 критериев): %d балл(ов)",
                            selected,
                            total
                    )
            );
        }

        String severity;
        String complications;
        String mortality;

        if (total <= 1) {
            severity = "Легкая степень тяжести";
            complications = "Осложнения не ожидаются";
            mortality = "Летальность: 0%";
        } else if (total == 2) {
            severity = "Легкая степень тяжести";
            complications = "Осложнения: около 4%";
            mortality = "Летальность: 0%";
        } else if (total <= 6) {
            severity = "Средняя степень тяжести";
            complications = "Риск осложнений повышается с увеличением балла";
            mortality = "Летальность зависит от клинического состояния пациента";
        } else {
            severity = "Тяжелая степень тяжести";
            complications = "Осложнения: около 92%";
            mortality = "Летальность: около 17%";
        }

        return new CTSIResult(
                total,
                severity
                        + "\n"
                        + complications
                        + "\n"
                        + mortality
        );
    }
}