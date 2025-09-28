package com.example.demo1.controls.AKI;

public class AKIResult {
    private final int stage;
    private final double baselineCreatinine;
    private final double currentCreatinine;
    private final double urineRate;
    private final double hours;

    public AKIResult(int stage, double baselineCreatinine, double currentCreatinine,
                     double urineRate, double hours) {
        this.stage = stage;
        this.baselineCreatinine = baselineCreatinine;
        this.currentCreatinine = currentCreatinine;
        this.urineRate = urineRate;
        this.hours = hours;
    }

    @Override
    public String toString() {
        String stageStr = switch (stage) {
            case 1 -> "Степень 1";
            case 2 -> "Степень 2";
            case 3 -> "Степень 3";
            default -> "Не выявлено";
        };

        return String.format("""
                AKI: %s
                Креатинин: %.2f → %.2f мг/дл
                Диурез: %.2f мл/кг/ч за %.1f ч
                """, stageStr, baselineCreatinine, currentCreatinine, urineRate, hours);
    }
}
