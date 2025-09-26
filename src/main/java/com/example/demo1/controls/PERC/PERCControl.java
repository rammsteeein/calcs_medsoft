package com.example.demo1.controls.PERC;

import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PERCControl extends BorderPane {

    private final PERCModel model;

    private TextField txtAge, txtHeartRate, txtOxygen;
    private CheckBox chkUnilateralLegEdema, chkHemoptysis,
            chkRecentSurgeryOrTrauma, chkSurgeryWithin4Weeks, chkPrevPEorDVT, chkHormoneUse;
    private TextArea txtResult;

    public PERCControl(PERCModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст (годы)");
        txtHeartRate = new TextField(); txtHeartRate.setPromptText("ЧСС (уд/мин)");
        txtOxygen = new TextField(); txtOxygen.setPromptText("O₂ (%)");

        chkUnilateralLegEdema = new CheckBox("Односторонний отек ног");
        chkHemoptysis = new CheckBox("Кровохарканье");
        chkRecentSurgeryOrTrauma = new CheckBox("Недавняя операция или травма");
        chkSurgeryWithin4Weeks = new CheckBox("Операция/травма ≤4 недель");
        chkPrevPEorDVT = new CheckBox("Предшествующая ТЭЛА или ТГВ");
        chkHormoneUse = new CheckBox("Использование гормонов / ОК / заместительная терапия");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");
        txtResult.setPrefHeight(100);

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("PERC"),
                txtAge, txtHeartRate, txtOxygen,
                chkUnilateralLegEdema, chkHemoptysis,
                chkRecentSurgeryOrTrauma, chkSurgeryWithin4Weeks,
                chkPrevPEorDVT, chkHormoneUse, txtResult
        );
        leftBox.setPrefSize(400, 600);

        Label lblDescription = CalculatorDescription.createDescription(
                "Шкала PERC:\n\n" +
                        "Используется для исключения риска ТЭЛА у пациентов с низкой вероятностью.\n" +
                        "0 баллов — крайне низкий риск, дополнительные тесты не нужны.\n" +
                        "≥1 балла — требуется дальнейшее обследование (D-димер, КТ-ангиография)."
        );

        this.setLeft(leftBox);
        this.setCenter(lblDescription);
        this.setPrefSize(700, 600);
    }

    private void bind() {
        ChangeListener<Object> listener = (obs, oldVal, newVal) -> model.calc();

        chkUnilateralLegEdema.selectedProperty().bindBidirectional(model.unilateralLegEdemaProperty());
        chkHemoptysis.selectedProperty().bindBidirectional(model.hemoptysisProperty());
        chkRecentSurgeryOrTrauma.selectedProperty().bindBidirectional(model.recentSurgeryOrTraumaProperty());
        chkSurgeryWithin4Weeks.selectedProperty().bindBidirectional(model.surgeryWithin4WeeksProperty());
        chkPrevPEorDVT.selectedProperty().bindBidirectional(model.prevPEorDVTProperty());
        chkHormoneUse.selectedProperty().bindBidirectional(model.hormoneUseProperty());

        chkUnilateralLegEdema.selectedProperty().addListener(listener);
        chkHemoptysis.selectedProperty().addListener(listener);
        chkRecentSurgeryOrTrauma.selectedProperty().addListener(listener);
        chkSurgeryWithin4Weeks.selectedProperty().addListener(listener);
        chkPrevPEorDVT.selectedProperty().addListener(listener);
        chkHormoneUse.selectedProperty().addListener(listener);

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.ageProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtHeartRate.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.heartRateProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });
        txtOxygen.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.oxygenProperty().set(Double.parseDouble(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
