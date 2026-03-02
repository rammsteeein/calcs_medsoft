package com.example.demo1.controls.Pokrovsky;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class PokrovskyControl extends StackPane implements CalculatorControl, AutoCloseable {

    private final PokrovskyModel model;

    private TextArea txtResult;

    private ComboBox<String> cmbStool;
    private ComboBox<String> cmbVomiting;
    private ComboBox<String> cmbThirst;
    private ComboBox<String> cmbDiuresis;
    private ComboBox<String> cmbCramps;
    private ComboBox<String> cmbCondition;
    private ComboBox<String> cmbEyes;
    private ComboBox<String> cmbMucous;
    private ComboBox<String> cmbBreathing;
    private ComboBox<String> cmbCyanosis;
    private ComboBox<String> cmbTurgor;
    private ComboBox<String> cmbPulse;
    private ComboBox<String> cmbPressure;
    private ComboBox<String> cmbVoice;

    public PokrovskyControl(PokrovskyModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {

        cmbStool = createCombo("Стул",
                "До 10 раз", "До 20 раз", "Более 20 раз", "Без счёта");

        cmbVomiting = createCombo("Рвота",
                "До 5 раз", "До 10 раз", "До 20 раз", "Неукротимая");

        cmbThirst = createCombo("Жажда",
                "Слабо", "Умеренно выраженная", "Резко выраженная", "Неутолимая / не может пить");

        cmbDiuresis = createCombo("Диурез",
                "Норма", "Снижен", "Олигурия", "Анурия");

        cmbCramps = createCombo("Судороги",
                "Нет", "Икроножные кратковременные", "Продолжительные болезненные", "Генерализованные клонические");

        cmbCondition = createCombo("Состояние",
                "Удовлетворительное", "Средней тяжести", "Тяжёлое", "Очень тяжёлое");

        cmbEyes = createCombo("Глазные яблоки",
                "Норма", "Запавшие", "Резко запавшие");

        cmbMucous = createCombo("Слизистые",
                "Влажные", "Суховатые", "Сухие", "Сухие резко гиперемированы");

        cmbBreathing = createCombo("Дыхание",
                "Норма", "Умеренное тахипноэ", "Тахипноэ");

        cmbCyanosis = createCombo("Цианоз",
                "Нет", "Носогубный треугольник", "Акроцианоз", "Диффузный");

        cmbTurgor = createCombo("Тургор кожи",
                "Норма", "Складка >1 сек", "Складка >2 сек");

        cmbPulse = createCombo("Пульс",
                "Норма", "До 100", "До 120", "Более 120, нитевидный");

        cmbPressure = createCombo("АД систолическое",
                "Норма", "До 100", "60–100", "Менее 60");

        cmbVoice = createCombo("Голос",
                "Сохранено", "Осиплость", "Афония");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefRowCount(4);

        VBox left = new VBox(8,
                CalculatorHeader.createHeader("Оценка дегидратации по В.И. Покровскому"),
                cmbStool, cmbVomiting, cmbThirst, cmbDiuresis,
                cmbCramps, cmbCondition, cmbEyes, cmbMucous,
                cmbBreathing, cmbCyanosis, cmbTurgor,
                cmbPulse, cmbPressure, cmbVoice,
                txtResult
        );

        ScrollPane scroll = new ScrollPane(left);
        scroll.setFitToWidth(true);

        getChildren().add(new HBox(20,
                scroll,
                CalculatorDescription.createDescription(
                        "Определение степени обезвоживания по клиническим признакам.\n\n" +
                                "1–3% — лёгкая\n" +
                                "4–6% — средняя\n" +
                                "7–9% — тяжёлая\n" +
                                "≥10% — крайне тяжёлая"
                )
        ));
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());

        cmbStool.valueProperty().bindBidirectional(model.stoolProperty());
        cmbVomiting.valueProperty().bindBidirectional(model.vomitingProperty());
        cmbThirst.valueProperty().bindBidirectional(model.thirstProperty());
        cmbDiuresis.valueProperty().bindBidirectional(model.diuresisProperty());
        cmbCramps.valueProperty().bindBidirectional(model.crampsProperty());
        cmbCondition.valueProperty().bindBidirectional(model.conditionProperty());
        cmbEyes.valueProperty().bindBidirectional(model.eyesProperty());
        cmbMucous.valueProperty().bindBidirectional(model.mucousProperty());
        cmbBreathing.valueProperty().bindBidirectional(model.breathingProperty());
        cmbCyanosis.valueProperty().bindBidirectional(model.cyanosisProperty());
        cmbTurgor.valueProperty().bindBidirectional(model.turgorProperty());
        cmbPulse.valueProperty().bindBidirectional(model.pulseProperty());
        cmbPressure.valueProperty().bindBidirectional(model.pressureProperty());
        cmbVoice.valueProperty().bindBidirectional(model.voiceProperty());
    }

    private void addListeners() {
        ChangeListener<String> listener = (o, ov, nv) -> calculate();

        cmbStool.valueProperty().addListener(listener);
        cmbVomiting.valueProperty().addListener(listener);
        cmbThirst.valueProperty().addListener(listener);
        cmbDiuresis.valueProperty().addListener(listener);
        cmbCramps.valueProperty().addListener(listener);
        cmbCondition.valueProperty().addListener(listener);
        cmbEyes.valueProperty().addListener(listener);
        cmbMucous.valueProperty().addListener(listener);
        cmbBreathing.valueProperty().addListener(listener);
        cmbCyanosis.valueProperty().addListener(listener);
        cmbTurgor.valueProperty().addListener(listener);
        cmbPulse.valueProperty().addListener(listener);
        cmbPressure.valueProperty().addListener(listener);
        cmbVoice.valueProperty().addListener(listener);
    }

    private void calculate() {
        model.calc();

        double degree = model.resultValueProperty().get();

        if (degree == 0) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
            return;
        }

        ResultStyler.applyStyleForValue(
                txtResult,
                degree,
                1.5,
                2.5
        );
    }

    private ComboBox<String> createCombo(String prompt, String... items) {
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getItems().addAll(items);
        cmb.setPromptText(prompt);
        cmb.setMaxWidth(Double.MAX_VALUE);
        return cmb;
    }

    @Override
    public void close() {
        txtResult.textProperty().unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 900;
    }

    @Override
    public double getDefaultHeight() {
        return 650;
    }
}