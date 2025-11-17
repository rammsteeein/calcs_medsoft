package com.example.demo1.controls.rGENEVA;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.Closeable;

public class rGENEVAControl extends StackPane implements CalculatorControl, Closeable {

    private rGENEVAModel model;

    private CheckBox chkPrevPEorDVT, chkSurgeryOrFracture, chkHemoptysis;
    private CheckBox chkActiveCancer, chkLegPain, chkPainAndSwelling;
    private TextField txtHeartRate, txtAge;
    private TextArea txtResult;

    private final ChangeListener<Boolean> prevPEorDVTListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> surgeryOrFractureListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> hemoptysisListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> activeCancerListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> legPainListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> painAndSwellingListener = (obs, oldVal, newVal) -> model.calc();

    private final ChangeListener<String> heartRateListener = (obs, oldVal, newVal) -> {
        try { model.heartRateProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        model.calc();
    };
    private final ChangeListener<String> ageListener = (obs, oldVal, newVal) -> {
        try { model.ageProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        model.calc();
    };

    public rGENEVAControl(rGENEVAModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
        model.calc();
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
                                "- Активное злокачественное новообразование: 2\n" +
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
        // Конвертер: 0 -> пусто
        StringConverter<Number> zeroToEmptyConverter = new StringConverter<>() {
            @Override
            public String toString(Number object) {
                if (object == null || object.intValue() == 0) return "";
                return object.toString();
            }
            @Override
            public Number fromString(String string) {
                try { return Integer.parseInt(string); }
                catch (Exception e) { return 0; }
            }
        };

        txtHeartRate.textProperty().bindBidirectional(model.heartRateProperty(), zeroToEmptyConverter);
        txtAge.textProperty().bindBidirectional(model.ageProperty(), zeroToEmptyConverter);

        chkPrevPEorDVT.selectedProperty().bindBidirectional(model.prevPEorDVTProperty());
        chkSurgeryOrFracture.selectedProperty().bindBidirectional(model.surgeryOrFractureProperty());
        chkHemoptysis.selectedProperty().bindBidirectional(model.hemoptysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkLegPain.selectedProperty().bindBidirectional(model.legPainProperty());
        chkPainAndSwelling.selectedProperty().bindBidirectional(model.painAndSwellingProperty());

        txtResult.textProperty().bind(model.resultProperty());
    }

    private void unbind() {
        chkPrevPEorDVT.selectedProperty().unbindBidirectional(model.prevPEorDVTProperty());
        chkSurgeryOrFracture.selectedProperty().unbindBidirectional(model.surgeryOrFractureProperty());
        chkHemoptysis.selectedProperty().unbindBidirectional(model.hemoptysisProperty());
        chkActiveCancer.selectedProperty().unbindBidirectional(model.activeCancerProperty());
        chkLegPain.selectedProperty().unbindBidirectional(model.legPainProperty());
        chkPainAndSwelling.selectedProperty().unbindBidirectional(model.painAndSwellingProperty());
        txtHeartRate.textProperty().unbindBidirectional(model.heartRateProperty());
        txtAge.textProperty().unbindBidirectional(model.ageProperty());
        txtResult.textProperty().unbind();
    }

    private void addListeners() {
        chkPrevPEorDVT.selectedProperty().addListener(prevPEorDVTListener);
        chkSurgeryOrFracture.selectedProperty().addListener(surgeryOrFractureListener);
        chkHemoptysis.selectedProperty().addListener(hemoptysisListener);
        chkActiveCancer.selectedProperty().addListener(activeCancerListener);
        chkLegPain.selectedProperty().addListener(legPainListener);
        chkPainAndSwelling.selectedProperty().addListener(painAndSwellingListener);
        txtHeartRate.textProperty().addListener(heartRateListener);
        txtAge.textProperty().addListener(ageListener);
    }

    private void removeListeners() {
        chkPrevPEorDVT.selectedProperty().removeListener(prevPEorDVTListener);
        chkSurgeryOrFracture.selectedProperty().removeListener(surgeryOrFractureListener);
        chkHemoptysis.selectedProperty().removeListener(hemoptysisListener);
        chkActiveCancer.selectedProperty().removeListener(activeCancerListener);
        chkLegPain.selectedProperty().removeListener(legPainListener);
        chkPainAndSwelling.selectedProperty().removeListener(painAndSwellingListener);
        txtHeartRate.textProperty().removeListener(heartRateListener);
        txtAge.textProperty().removeListener(ageListener);
    }

    @Override
    public void close() {
        removeListeners();
        unbind();
    }

    @Override
    public double getDefaultWidth() { return 700; }

    @Override
    public double getDefaultHeight() { return 430; }
}
