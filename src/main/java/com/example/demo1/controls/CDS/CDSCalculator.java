package com.example.demo1.controls.CDS;

public class CDSCalculator {

    public static CDSResult calc(String appearance, String eyes, String mucous, String tears) {
        if (appearance == null || eyes == null || mucous == null || tears == null) {
            return new CDSResult("Ошибка: заполните все поля");
        }

        int scoreAppearance = mapAppearance(appearance) - 1;
        int scoreEyes = mapEyes(eyes) - 1;
        int scoreMucous = mapMucous(mucous) - 1;
        int scoreTears = mapTears(tears) - 1;

        int total = scoreAppearance + scoreEyes + scoreMucous + scoreTears;

        String interpretation;
        if (total == 0) {
            interpretation = "Дегидратация отсутствует";
        } else if (total >= 1 && total <= 4) {
            interpretation = "Лёгкая дегидратация";
        } else {
            interpretation = "Дегидратация средней или тяжёлой степени";
        }

        String result = String.format("Баллы (0–8): %d\nИнтерпретация: %s", total, interpretation);
        return new CDSResult(result);
    }

    private static int mapAppearance(String value) {
        switch (value) {
            case "Нормальный": return 1;
            case "Жажда, беспокойство, раздражительность": return 2;
            case "Вялость, сонливость": return 3;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }

    private static int mapEyes(String value) {
        switch (value) {
            case "Тургор нормальный": return 1;
            case "Слегка запавшие": return 2;
            case "Запавшие": return 3;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }

    private static int mapMucous(String value) {
        switch (value) {
            case "Влажные": return 1;
            case "Липкие, суховатые": return 2;
            case "Сухие": return 3;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }

    private static int mapTears(String value) {
        switch (value) {
            case "Слезоотделение в норме": return 1;
            case "Слезоотделение снижено": return 2;
            case "Слёзы отсутствуют": return 3;
            default: throw new IllegalArgumentException("Неизвестное значение: " + value);
        }
    }
}