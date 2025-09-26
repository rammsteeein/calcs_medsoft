package com.example.demo1.controls.Wells;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WellsControl extends StackPane {

    private final WellsModel model;

    private CheckBox chkPrevPEorDVT, chkTachycardia, chkSurgeryOrImmobilization;
    private CheckBox chkHemoptysis, chkActiveCancer, chkClinicalDVT, chkAlternativeLessLikely;
    private TextArea txtResult;

    public WellsControl(WellsModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkPrevPEorDVT = new CheckBox("Предшествующие ТЭЛА или тромбозы глубоких вен");
        chkTachycardia = new CheckBox("ЧСС > 100 в минуту");
        chkSurgeryOrImmobilization = new CheckBox("Хирургические операции или иммобилизация последние 4 недели");
        chkHemoptysis = new CheckBox("Кровохарканье");
        chkActiveCancer = new CheckBox("Активное злокачественное новообразование");
        chkClinicalDVT = new CheckBox("Клинические признаки тромбоза глубоких вен");
        chkAlternativeLessLikely = new CheckBox("Альтернативный диагноз менее вероятен, чем ТЭЛА");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Индекс Wells"),
                chkPrevPEorDVT, chkTachycardia, chkSurgeryOrImmobilization,
                chkHemoptysis, chkActiveCancer, chkClinicalDVT, chkAlternativeLessLikely, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала Wells используется для оценки вероятности тромбоэмболии лёгочной артерии (ТЭЛА).\n\n" +
                                "Критерии начисления баллов:\n" +
                                "- Предшествующие ТЭЛА или DVT: 1.5\n" +
                                "- ЧСС > 100 уд/мин: 1.5\n" +
                                "- Хирургия/иммобилизация: 1.5\n" +
                                "- Кровохарканье: 1\n" +
                                "- Активное злокачественное новообразование: 1\n" +
                                "- Клинические признаки DVT: 3\n" +
                                "- Альтернативный диагноз менее вероятен: 3\n\n" +
                                "Интерпретация:\n" +
                                "Трёхуровневая: низкая (0–1), средняя (2–6), высокая (>7)\n" +
                                "Двухуровневая: ТЭЛА маловероятна (0–4), ТЭЛА вероятна (≥5)"
                )
        ));
    }

    private void bind() {
        ChangeListener<Object> listener = (obs, oldVal, newVal) -> model.calc();

        chkPrevPEorDVT.selectedProperty().bindBidirectional(model.prevPEorDVTProperty());
        chkTachycardia.selectedProperty().bindBidirectional(model.tachycardiaProperty());
        chkSurgeryOrImmobilization.selectedProperty().bindBidirectional(model.surgeryOrImmobilizationProperty());
        chkHemoptysis.selectedProperty().bindBidirectional(model.hemoptysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkClinicalDVT.selectedProperty().bindBidirectional(model.clinicalDVTProperty());
        chkAlternativeLessLikely.selectedProperty().bindBidirectional(model.alternativeLessLikelyProperty());

        chkPrevPEorDVT.selectedProperty().addListener(listener);
        chkTachycardia.selectedProperty().addListener(listener);
        chkSurgeryOrImmobilization.selectedProperty().addListener(listener);
        chkHemoptysis.selectedProperty().addListener(listener);
        chkActiveCancer.selectedProperty().addListener(listener);
        chkClinicalDVT.selectedProperty().addListener(listener);
        chkAlternativeLessLikely.selectedProperty().addListener(listener);

        txtResult.textProperty().bind(model.resultProperty());
    }
}
