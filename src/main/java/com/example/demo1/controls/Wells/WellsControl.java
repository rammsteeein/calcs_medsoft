package com.example.demo1.controls.Wells;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WellsControl extends StackPane implements CalculatorControl, AutoCloseable {

    private final WellsModel model;

    private CheckBox chkPrevPEorDVT, chkTachycardia, chkSurgeryOrImmobilization;
    private CheckBox chkHemoptysis, chkActiveCancer, chkClinicalDVT, chkAlternativeLessLikely;
    private TextArea txtResult;

    private ChangeListener<Boolean> checkListener;
    private ChangeListener<Number> scoreListener;

    public WellsControl(WellsModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
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
        checkListener = (obs, oldVal, newVal) -> model.calc();
        scoreListener = (obs, oldVal, newVal) ->
                ResultStyler.applyStyleForValue(txtResult, newVal.doubleValue(), 1.5, 7);

        chkPrevPEorDVT.selectedProperty().bindBidirectional(model.prevPEorDVTProperty());
        chkTachycardia.selectedProperty().bindBidirectional(model.tachycardiaProperty());
        chkSurgeryOrImmobilization.selectedProperty().bindBidirectional(model.surgeryOrImmobilizationProperty());
        chkHemoptysis.selectedProperty().bindBidirectional(model.hemoptysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkClinicalDVT.selectedProperty().bindBidirectional(model.clinicalDVTProperty());
        chkAlternativeLessLikely.selectedProperty().bindBidirectional(model.alternativeLessLikelyProperty());

        txtResult.textProperty().bind(model.resultProperty());
        model.scoreProperty().addListener(scoreListener);
    }

    private void addListeners() {
        chkPrevPEorDVT.selectedProperty().addListener(checkListener);
        chkTachycardia.selectedProperty().addListener(checkListener);
        chkSurgeryOrImmobilization.selectedProperty().addListener(checkListener);
        chkHemoptysis.selectedProperty().addListener(checkListener);
        chkActiveCancer.selectedProperty().addListener(checkListener);
        chkClinicalDVT.selectedProperty().addListener(checkListener);
        chkAlternativeLessLikely.selectedProperty().addListener(checkListener);
    }

    private void removeListeners() {
        chkPrevPEorDVT.selectedProperty().removeListener(checkListener);
        chkTachycardia.selectedProperty().removeListener(checkListener);
        chkSurgeryOrImmobilization.selectedProperty().removeListener(checkListener);
        chkHemoptysis.selectedProperty().removeListener(checkListener);
        chkActiveCancer.selectedProperty().removeListener(checkListener);
        chkClinicalDVT.selectedProperty().removeListener(checkListener);
        chkAlternativeLessLikely.selectedProperty().removeListener(checkListener);
        model.scoreProperty().removeListener(scoreListener);
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultWidth() {
        return 700;
    }

    @Override
    public double getDefaultHeight() {
        return 430;
    }
}
