package com.example.demo1.controls.GRACE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GRACEModel {

    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty hr = new SimpleStringProperty();
    private final StringProperty sbp = new SimpleStringProperty();
    private final StringProperty killip = new SimpleStringProperty();
    private final StringProperty creatinine = new SimpleStringProperty();
    private final StringProperty other = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty unit = new SimpleStringProperty();
    private final ObservableList<String> otherList = FXCollections.observableArrayList();

    public StringProperty unitProperty() { return unit; }
    public ObservableList<String> otherListProperty() { return otherList; }

    public StringProperty ageProperty() { return age; }
    public StringProperty hrProperty() { return hr; }
    public StringProperty sbpProperty() { return sbp; }
    public StringProperty killipProperty() { return killip; }
    public StringProperty creatinineProperty() { return creatinine; }
    public StringProperty otherProperty() { return other; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            int ageVal = Integer.parseInt(age.get());
            int hrVal = Integer.parseInt(hr.get());
            int sbpVal = Integer.parseInt(sbp.get());
            double creatVal = Double.parseDouble(creatinine.get());

            if ("мкмоль/л".equals(unit.get())) {
                creatVal = creatVal / 88.4;
            }

            int totalOtherPoints = 0;
            for (String f : otherList) {
                totalOtherPoints += GRACECalculator.mapOtherPoints(f);
            }

            GRACEResult res = GRACECalculator.calc(
                    ageVal, hrVal, sbpVal, killip.get(), creatVal, null
            );

            int totalPoints = res.getTotalPoints() + totalOtherPoints;
            String interpretation;
            if (totalPoints <= 108) interpretation = "Низкий риск (<1%)";
            else if (totalPoints <= 140) interpretation = "Умеренный риск (1–3%)";
            else interpretation = "Высокий риск (>3%)";

            result.set(
                    res.toString() + "\nДополнительные факторы: +" + totalOtherPoints + " баллов\n" +
                            "Суммарно скорректировано: " + totalPoints + " баллов\n" +
                            "Риск: " + interpretation
            );
        } catch (Exception e) {
            result.set("Ошибка: " + e.getMessage());
        }
    }
}
