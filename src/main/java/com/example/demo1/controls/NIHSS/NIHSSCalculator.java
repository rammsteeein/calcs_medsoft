package com.example.demo1.controls.NIHSS;

import java.util.HashMap;
import java.util.Map;

public class NIHSSCalculator {

    public static NIHSSResult calc(Map<String, String> values) {
        int total = 0;
        boolean hasUN = false;

        for (String key : values.keySet()) {
            String val = values.get(key);
            if (val == null) continue;

            if (val.equals("UN")) {
                hasUN = true;
                continue;
            }

            total += mapValue(key, val);
        }

        String interpretation = interpret(total);

        if (hasUN) {
            interpretation += "\n\nВнимание: присутствуют разделы с оценкой UN (исследовать невозможно).";
        }

        return new NIHSSResult(total, interpretation);
    }

    private static int mapValue(String key, String val) {
        switch (key) {

            case "LOC":
                switch (val) {
                    case "0": return 0;
                    case "1": return 1;
                    case "2": return 2;
                    case "3": return 3;
                }

            case "LOC_QUEST":
                return Integer.parseInt(val);

            case "LOC_COMMAND":
                return Integer.parseInt(val);

            case "EYE":
                return Integer.parseInt(val);

            case "VISION":
                return Integer.parseInt(val);

            case "FACE":
                return Integer.parseInt(val);

            case "ARM_L":
            case "ARM_R":
            case "LEG_L":
            case "LEG_R":
                return Integer.parseInt(val);

            case "ATAXIA":
                return Integer.parseInt(val);

            case "SENS":
                return Integer.parseInt(val);

            case "SPEECH":
                return Integer.parseInt(val);

            case "DYSARTHRIA":
                return Integer.parseInt(val);

            case "NEGLECT":
                return Integer.parseInt(val);

            default:
                throw new IllegalArgumentException("Неизвестное поле: " + key);
        }
    }

    private static String interpret(int total) {
        if (total <= 4) return "Очень лёгкий инсульт";
        if (total <= 10) return "Лёгкий инсульт";
        if (total <= 15) return "Умеренный инсульт";
        if (total <= 20) return "Умеренно-тяжёлый инсульт";
        return "Тяжёлый инсульт";
    }
}