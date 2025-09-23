package com.example.demo1.controls.GuptaMICA;

import javafx.beans.property.*;

import java.util.Map;

public class GuptaMICAModel {

    private final DoubleProperty ageYears = new SimpleDoubleProperty();
    private final ObjectProperty<String> functionalStatus = new SimpleObjectProperty<>();
    private final ObjectProperty<String> asaStatus = new SimpleObjectProperty<>();
    private final ObjectProperty<String> creatinine = new SimpleObjectProperty<>();
    private final ObjectProperty<String> surgeryType = new SimpleObjectProperty<>();
    private final StringProperty result = new SimpleStringProperty();

    // Соответствие названия → коэффициент
    public static final Map<String, Double> functionalStatusMap = Map.of(
            "Полностью независимый", 0.0,
            "Частично зависимый", 0.65,
            "Полностью зависимый", 1.03
    );

    public static final Map<String, Double> asaStatusMap = Map.of(
            "ASA I: Здоровый пациент", -5.17,
            "ASA II: Пациент с легким системным заболеванием", -3.29,
            "ASA III: Пациент с тяжелым системным заболеванием", -1.92,
            "ASA IV: Пациент с тяжелым системным заболеванием, угроза жизни", -0.95,
            "ASA V: Умирающий пациент, операция по жизненным показаниям", 0.0
    );

    public static final Map<String, Double> creatinineMap = Map.of(
            "Нормальный (≤1,5 мг/дл)", 0.0,
            "Повышенный (>1,5 мг/дл)", 0.61,
            "Неизвестный", -0.1
    );

    public static final Map<String, Double> surgeryTypeMap = Map.ofEntries(
            Map.entry("Грыжи: вентральные, паховые, бедренные и др.", 0.0),
            Map.entry("Операции на прямой кишке и заднем проходе", -0.16),
            Map.entry("Операции на аорте", 1.6),
            Map.entry("Бариатрические операции", -0.25),
            Map.entry("Операции на головном мозге", 1.4),
            Map.entry("Торакальные операции", -1.61),
            Map.entry("Операции на сердце", 45292.0),
            Map.entry("ЛОР операции, кроме щитовидной железы", 0.71),
            Map.entry("Операции на гепатопанкреатобилиарской системе", 1.39),
            Map.entry("Операции на желчном пузыре, аппендиксе и др.", 0.59),
            Map.entry("Шея, включая щитовидную железу", 0.18),
            Map.entry("Акушерские и гинекологические операции", 0.76),
            Map.entry("Ортопедические операции", 0.8),
            Map.entry("Прочие операции на брюшной полости", 41275.0),
            Map.entry("Операции на периферических сосудах", 0.86),
            Map.entry("Операции на коже", 0.54),
            Map.entry("Операции на позвоночнике", 0.21),
            Map.entry("Торакальная хирургия, кроме пищевода и сердца", 0.4),
            Map.entry("Операции на венах", -1.09),
            Map.entry("Урологические операции", -0.26)
    );
    // Геттеры/сеттеры
    public double getAgeYears() { return ageYears.get(); }
    public void setAgeYears(double val) { ageYears.set(val); }
    public DoubleProperty ageYearsProperty() { return ageYears; }

    public String getFunctionalStatus() { return functionalStatus.get(); }
    public void setFunctionalStatus(String val) { functionalStatus.set(val); }
    public ObjectProperty<String> functionalStatusProperty() { return functionalStatus; }

    public String getAsaStatus() { return asaStatus.get(); }
    public void setAsaStatus(String val) { asaStatus.set(val); }
    public ObjectProperty<String> asaStatusProperty() { return asaStatus; }

    public String getCreatinine() { return creatinine.get(); }
    public void setCreatinine(String val) { creatinine.set(val); }
    public ObjectProperty<String> creatinineProperty() { return creatinine; }

    public String getSurgeryType() { return surgeryType.get(); }
    public void setSurgeryType(String val) { surgeryType.set(val); }
    public ObjectProperty<String> surgeryTypeProperty() { return surgeryType; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        GuptaMICAResult r = GuptaMICACalculator.calc(
                getAgeYears(),
                getFunctionalStatus(),
                getAsaStatus(),
                getCreatinine(),
                getSurgeryType()
        );
        setResult(r.toString());
    }

    // Builder (по примеру CKDEPI)
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private double ageYears;
        private String functionalStatus;
        private String asaStatus;
        private String creatinine;
        private String surgeryType;
        private String result = "";

        public Builder withAgeYears(double val) { this.ageYears = val; return this; }
        public Builder withFunctionalStatus(String val) { this.functionalStatus = val; return this; }
        public Builder withAsaStatus(String val) { this.asaStatus = val; return this; }
        public Builder withCreatinine(String val) { this.creatinine = val; return this; }
        public Builder withSurgeryType(String val) { this.surgeryType = val; return this; }
        public Builder withResult(String val) { this.result = val; return this; }

        public GuptaMICAModel build() {
            GuptaMICAModel m = new GuptaMICAModel();
            m.setAgeYears(ageYears);
            m.setFunctionalStatus(functionalStatus);
            m.setAsaStatus(asaStatus);
            m.setCreatinine(creatinine);
            m.setSurgeryType(surgeryType);
            m.setResult(result);
            return m;
        }
    }
}
