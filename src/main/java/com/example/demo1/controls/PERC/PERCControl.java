package com.example.demo1.controls.PERC;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PERCControl extends BorderPane {

    private final PERCModel model;

    private TextField txtAge;
    private TextField txtHeartRate;
    private TextField txtOxygen;
    private CheckBox chkUnilateralLegEdema;
    private CheckBox chkHemoptysis;
    private CheckBox chkRecentSurgeryOrTrauma;
    private CheckBox chkSurgeryWithin4Weeks;
    private CheckBox chkPrevPEorDVT;
    private CheckBox chkHormoneUse;
    private TextArea txtResult;
    private Label lblDescription;

    public PERCControl(PERCModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        // Левая часть (калькулятор)
        txtAge = new TextField(); txtAge.setPromptText("Возраст (годы)");
        txtHeartRate = new TextField(); txtHeartRate.setPromptText("ЧСС (уд/мин)");
        txtOxygen = new TextField(); txtOxygen.setPromptText("O₂ (%)");

        chkUnilateralLegEdema = new CheckBox("Односторонний отек ног");
        chkHemoptysis = new CheckBox("Кровохарканье");
        chkRecentSurgeryOrTrauma = new CheckBox("Недавняя операция или травма");
        chkSurgeryWithin4Weeks = new CheckBox("Операция или травма ≤4 недель назад");
        chkPrevPEorDVT = new CheckBox("Предшествующая ТЭЛА или ТГВ");
        chkHormoneUse = new CheckBox("Использование гормонов / ОК / заместительная терапия");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");
        txtResult.setPrefHeight(100);

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала PERC"),
                txtAge, txtHeartRate, txtOxygen,
                chkUnilateralLegEdema, chkHemoptysis,
                chkRecentSurgeryOrTrauma, chkSurgeryWithin4Weeks,
                chkPrevPEorDVT, chkHormoneUse, txtResult
        );
        leftBox.setPrefSize(400, 600); // делаем шире и выше

        Label lblDescription = CalculatorDescription.createDescription(
                "Описание шкалы PERC:\n\n" +
                        "Используется для исключения риска ТЭЛА у пациентов с низкой вероятностью.\n" +
                        "Если все критерии отрицательные (0 баллов), можно отказаться от дополнительных тестов.\n" +
                        "Если ≥1 балла — требуется дальнейшее обследование."
        );

// Раскладываем
        this.setLeft(leftBox);
        this.setCenter(lblDescription);

        this.setPrefSize(700, 600);
    }

    private void bind() {
        ChangeListener<Object> recalcListener = (obs, oldVal, newVal) -> model.calc();

        chkUnilateralLegEdema.selectedProperty().bindBidirectional(model.unilateralLegEdemaProperty());
        chkHemoptysis.selectedProperty().bindBidirectional(model.hemoptysisProperty());
        chkRecentSurgeryOrTrauma.selectedProperty().bindBidirectional(model.recentSurgeryOrTraumaProperty());
        chkSurgeryWithin4Weeks.selectedProperty().bindBidirectional(model.surgeryWithin4WeeksProperty());
        chkPrevPEorDVT.selectedProperty().bindBidirectional(model.prevPEorDVTProperty());
        chkHormoneUse.selectedProperty().bindBidirectional(model.hormoneUseProperty());

        chkUnilateralLegEdema.selectedProperty().addListener(recalcListener);
        chkHemoptysis.selectedProperty().addListener(recalcListener);
        chkRecentSurgeryOrTrauma.selectedProperty().addListener(recalcListener);
        chkSurgeryWithin4Weeks.selectedProperty().addListener(recalcListener);
        chkPrevPEorDVT.selectedProperty().addListener(recalcListener);
        chkHormoneUse.selectedProperty().addListener(recalcListener);

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setAge(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtHeartRate.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setHeartRate(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtOxygen.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setOxygen(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }

    public void setDescription(String text) {
        lblDescription.setText(text);
    }
}
