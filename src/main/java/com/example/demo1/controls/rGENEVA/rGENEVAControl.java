package com.example.demo1.controls.rGENEVA;

import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class rGENEVAControl extends StackPane {

    private final rGENEVAModel model;

    private CheckBox chkPrevPEorDVT, chkSurgeryOrFracture, chkHemoptysis;
    private CheckBox chkActiveCancer, chkLegPain, chkPainAndSwelling;
    private TextField txtHeartRate, txtAge;
    private TextArea txtResult;

    public rGENEVAControl(rGENEVAModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkPrevPEorDVT = new CheckBox("Предшествующие ТЭЛА или тромбозы глубоких вен");
        txtHeartRate = new TextField(); txtHeartRate.setPromptText("ЧСС (уд/мин)");
        chkSurgeryOrFracture = new CheckBox("Хирургические операции или переломы (1 мес.)");
        chkHemoptysis = new CheckBox("Кровохарканье");
        chkActiveCancer = new CheckBox("Активное злокачественное новообразование");
        chkLegPain = new CheckBox("Боль в 1 нижней конечности");
        chkPainAndSwelling = new CheckBox("Боль при пальпации глубоких вен и односторонний отёк");
        txtAge = new TextField(); txtAge.setPromptText("Возраст");

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("rGENEVA"),
                chkPrevPEorDVT, txtHeartRate, chkSurgeryOrFracture,
                chkHemoptysis, chkActiveCancer, chkLegPain, chkPainAndSwelling, txtAge, txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала rGENEVA (упрощённая Женева) — оценка вероятности тромбоэмболии лёгочной артерии.\n\n" +
                                "Критерии начисления баллов:\n" +
                                "- Предшествующие ТЭЛА или DVT: 3\n" +
                                "- ЧСС 75–94: 3, ≥95: 5\n" +
                                "- Хирургия/перелом 1 мес.: 2\n" +
                                "- Кровохарканье: 2\n" +
                                "- Активное злокачественное новообразование: 3\n" +
                                "- Боль в одной ноге: 3\n" +
                                "- Боль + односторонний отёк: 4\n" +
                                "- Возраст >65: 1\n\n" +
                                "Интерпретация:\n" +
                                "Трёхуровневая: низкая (0–1), средняя (2–6), высокая (≥7)\n" +
                                "Двухуровневая: ТЭЛА маловероятна (0–4), ТЭЛА вероятна (≥5)"
                )
        ));
    }

    private void bind() {
        ChangeListener<Object> listener = (obs, oldVal, newVal) -> model.calc();

        chkPrevPEorDVT.selectedProperty().bindBidirectional(model.prevPEorDVTProperty());
        chkSurgeryOrFracture.selectedProperty().bindBidirectional(model.surgeryOrFractureProperty());
        chkHemoptysis.selectedProperty().bindBidirectional(model.hemoptysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkLegPain.selectedProperty().bindBidirectional(model.legPainProperty());
        chkPainAndSwelling.selectedProperty().bindBidirectional(model.painAndSwellingProperty());

        chkPrevPEorDVT.selectedProperty().addListener(listener);
        chkSurgeryOrFracture.selectedProperty().addListener(listener);
        chkHemoptysis.selectedProperty().addListener(listener);
        chkActiveCancer.selectedProperty().addListener(listener);
        chkLegPain.selectedProperty().addListener(listener);
        chkPainAndSwelling.selectedProperty().addListener(listener);

        txtHeartRate.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.heartRateProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.ageProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            model.calc();
        });

        txtResult.textProperty().bind(model.resultProperty());
    }
}
