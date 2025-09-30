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
        String stageStr;
        switch (stage) {
            case 1:
                stageStr = "Степень 1";
                break;
            case 2:
                stageStr = "Степень 2";
                break;
            case 3:
                stageStr = "Степень 3";
                break;
            default:
                stageStr = "Не выявлено";
                break;
        }

        return String.format(
                "AKI: %s\n" +
                        "Креатинин: %.2f → %.2f мг/дл\n" +
                        "Диурез: %.2f мл/кг/ч за %.1f ч\n",
                stageStr, baselineCreatinine, currentCreatinine, urineRate, hours
        );
    }
}
