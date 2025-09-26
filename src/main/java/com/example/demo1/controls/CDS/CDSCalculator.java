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
     * - 0 баллов: дегидратация отсутствует
     * - 1–4 балла: лёгкая дегидратация
     * - 5–8 баллов: средняя или тяжёлая дегидратация
     *
     * Примечания:
     * - Каждый параметр оценивается отдельно, затем баллы суммируются.
     * - Шкала позволяет быстро определить степень дегидратации у пациента, особенно у детей.
     * - Результат может использоваться для принятия решения о необходимости восполнения жидкости.
     */

        public static CDSResult calc(String appearance, String eyes, String mucous, String tears) {
            if (appearance == null || eyes == null || mucous == null || tears == null) {
                return new CDSResult("Ошибка: заполните все поля");
            }

            int total = mapAppearance(appearance)
                    + mapEyes(eyes)
                    + mapMucous(mucous)
                    + mapTears(tears);

            String interpretation;
            if (total == 0) {
                interpretation = "Дегидратация отсутствует";
            } else if (total <= 4) {
                interpretation = "Лёгкая дегидратация";
            } else {
                interpretation = "Средняя или тяжёлая дегидратация";
            }

            String result = String.format("Баллы: %d\nИнтерпретация: %s", total, interpretation);
            return new CDSResult(result);
        }

        private static int mapAppearance(String value) {
            switch (value) {
                case "Нормальный": return 0;
                case "Жажда, беспокойство, раздражительность": return 2;
                case "Вялость, сонливость": return 3;
                default: throw new IllegalArgumentException("Неизвестное значение: " + value);
            }
        }

        private static int mapEyes(String value) {
            switch (value) {
                case "Тургор нормальный": return 0;
                case "Слегка запавшие": return 2;
                case "Запавшие": return 3;
                default: throw new IllegalArgumentException("Неизвестное значение: " + value);
            }
        }

        private static int mapMucous(String value) {
            switch (value) {
                case "Влажные": return 0;
                case "Липкие, суховатые": return 2;
                case "Сухие": return 3;
                default: throw new IllegalArgumentException("Неизвестное значение: " + value);
            }
        }

        private static int mapTears(String value) {
            switch (value) {
                case "Слезоотделение в норме": return 0;
                case "Слезоотделение снижено": return 2;
                case "Слёзы отсутствуют": return 3;
                default: throw new IllegalArgumentException("Неизвестное значение: " + value);
            }
        }
    }