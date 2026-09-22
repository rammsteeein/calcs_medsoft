package com.example.demo1.controls.Ranson;

public final class RansonCalculator {
    private RansonCalculator() { }
    public static RansonResult calc(String[] values) {
        int selected = 0, total = 0;
        for (String value : values) if (value != null) { selected++; if ("Да".equals(value)) total++; }
        if (selected == 0) return new RansonResult(0, "Выберите хотя бы один параметр");
        String text;
        if (selected < values.length) text = String.format("Промежуточный результат (%d из %d): %d балл(ов)",
                selected, values.length, total);
        else if (total < 3) text = "Ориентировочная летальность 1%";
        else if (total <= 4) text = "Ориентировочная летальность 16%";
        else if (total <= 6) text = "Ориентировочная летальность 40%";
        else text = "Ориентировочная летальность до 100%";
        return new RansonResult(total, text);
    }
}
