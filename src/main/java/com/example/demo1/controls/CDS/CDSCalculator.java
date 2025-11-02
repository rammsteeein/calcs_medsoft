package com.example.demo1.controls.CDS;

public class CDSCalculator {

    /**
     * Шкала CDS (Clinical Dehydration Scale) для оценки степени дегидратации
     *
     * Критерии и баллы:
     * 1. Внешний вид:
     *    - Нормальный: 1 балл
     *    - Жажда, беспокойство, раздражительность: 2 балла
     *    - Вялость, сонливость: 3 балла
     *
     * 2. Глазные яблоки:
     *    - Тургор нормальный: 1 балл
     *    - Слегка запавшие: 2 балла
     *    - Запавшие: 3 балла
     *
     * 3. Слизистые оболочки:
     *    - Влажные: 1 балл
     *    - Липкие, суховатые: 2 балла
     *    - Сухие: 3 балла
     *
     * 4. Слезы:
     *    - Слезоотделение в норме: 1 балл
     *    - Слезоотделение снижено: 2 балла
     *    - Слезы отсутствуют: 3 балла
     *
     * Интерпретация суммарного балла:
     * - 4 балла: дегидратация отсутствует
     * - 5–8 баллов: лёгкая дегидратация
     * - 9–12 баллов: средняя или тяжёлая дегидратация
     */

    public static CDSResult calc(String appearance, String eyes, String mucous, String tears) {
        int total = 0;
        int count = 0;

        if (appearance != null) {
            total += mapAppearance(appearance);
            count++;
        }
        if (eyes != null) {
            total += mapEyes(eyes);
            count++;
        }
        if (mucous != null) {
            total += mapMucous(mucous);
            count++;
        }
        if (tears != null) {
            total += mapTears(tears);
            count++;
        }

        if (count == 0) {
            return new CDSResult(0, "Выберите хотя бы один параметр");
        }

        String interpretation;

        if (count < 4) {
            interpretation = String.format(
                    "Промежуточный результат (%d из 4): %d балл(ов)",
                    count, total
            );
        } else {
            if (total <= 4) {
                interpretation = "Дегидратация отсутствует";
            } else if (total <= 8) {
                interpretation = "Лёгкая дегидратация";
            } else {
                interpretation = "Средняя или тяжёлая дегидратация";
            }
        }

        return new CDSResult(total, interpretation);
    }

    private static int mapAppearance(String value) {
        switch (value) {
            case "Нормальный": return 0;
            case "Жажда, беспокойство, раздражительность": return 1;
            case "Вялость, сонливость": return 2;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }

    private static int mapEyes(String value) {
        switch (value) {
            case "Тургор нормальный": return 0;
            case "Слегка запавшие": return 1;
            case "Запавшие": return 2;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }

    private static int mapMucous(String value) {
        switch (value) {
            case "Влажные": return 0;
            case "Липкие, суховатые": return 1;
            case "Сухие": return 2;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }

    private static int mapTears(String value) {
        switch (value) {
            case "Слезоотделение в норме": return 0;
            case "Слезоотделение снижено": return 1;
            case "Слёзы отсутствуют": return 2;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }
}