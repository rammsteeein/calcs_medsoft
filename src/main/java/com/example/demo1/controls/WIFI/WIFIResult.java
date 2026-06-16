package com.example.demo1.controls.WIFI;

public class WIFIResult {

    private final int wound;
    private final int ischemia;
    private final int infection;
    private final int amputationStage;
    private final int revascularizationStage;
    private final String woundDescription;
    private final String ischemiaDescription;
    private final String infectionDescription;
    private final String amputationRisk;
    private final String revascularizationBenefit;
    private final boolean complete;

    public WIFIResult(int wound, int ischemia, int infection,
                      int amputationStage, int revascularizationStage,
                      String woundDescription, String ischemiaDescription, String infectionDescription,
                      String amputationRisk, String revascularizationBenefit, boolean complete) {
        this.wound = wound;
        this.ischemia = ischemia;
        this.infection = infection;
        this.amputationStage = amputationStage;
        this.revascularizationStage = revascularizationStage;
        this.woundDescription = woundDescription;
        this.ischemiaDescription = ischemiaDescription;
        this.infectionDescription = infectionDescription;
        this.amputationRisk = amputationRisk;
        this.revascularizationBenefit = revascularizationBenefit;
        this.complete = complete;
    }

    public int getWound() {
        return wound;
    }

    public int getIschemia() {
        return ischemia;
    }

    public int getInfection() {
        return infection;
    }

    public int getAmputationStage() {
        return amputationStage;
    }

    public int getRevascularizationStage() {
        return revascularizationStage;
    }

    public boolean isComplete() {
        return complete;
    }

    @Override
    public String toString() {
        if (!complete) {
            return "Выберите баллы по всем трём компонентам: W (рана), I (ишемия), fL (инфекция)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Классификация WIfL: W").append(wound)
                .append(" I").append(ischemia)
                .append(" fI").append(infection).append('\n');
        sb.append("══════════════════════════════════════\n\n");

        sb.append("W (глубина поражения): ").append(wound).append('\n');
        sb.append(woundDescription).append("\n\n");

        sb.append("I (ишемия): ").append(ischemia).append('\n');
        sb.append(ischemiaDescription).append("\n\n");

        sb.append("fI (инфекция стопы): ").append(infection).append('\n');
        sb.append(infectionDescription).append("\n\n");

        sb.append("──────────────────────────────────────\n");
        sb.append("Клиническая стадия (риск ампутации в течение 1 года): ")
                .append(amputationStage).append(" — ").append(amputationRisk).append('\n');
        sb.append("Клиническая стадия (потребность в реваскуляризации): ")
                .append(revascularizationStage).append(" — ").append(revascularizationBenefit).append('\n');

        return sb.toString();
    }
}
