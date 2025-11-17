package com.example.demo1.controls.GCS;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GCSControl extends StackPane implements CalculatorControl {

    private final GCSModel model;

    private final ComboBox<String> cmbEyes;
    private final ComboBox<String> cmbVerbal;
    private final ComboBox<String> cmbMotor;
    private final TextArea txtResult;

    public GCSControl(GCSModel model) {
        this.model = model;

        cmbEyes = createCombo(
                "Открытие глаз (E)",
                "4 — спонтанно",
                "3 — на голос",
                "2 — на болевой стимул",
                "1 — нет"
        );

        cmbVerbal = createCombo(
                "Речевая реакция (V)",
                "5 — ориентирован, отвечает адекватно",
                "4 — спутанная речь",
                "3 — отдельные слова",
                "2 — звуки (мычание, стоны)",
                "1 — нет"
        );

        cmbMotor = createCombo(
                "Двигательная реакция (M)",
                "6 — выполняет команды",
                "5 — локализует болевой стимул",
                "4 — отдёргивает конечность",
                "3 — сгибание (децеребрационная реакция)",
                "2 — разгибание (декапитационная реакция)",
                "1 — нет"
        );

        txtResult = new TextArea();
        txtResult.setEditable(false);

        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        VBox inputsBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала комы Глазго (GCS)"),
                cmbEyes, cmbVerbal, cmbMotor,
                txtResult
        );

        HBox container = new HBox(20,
                inputsBox,
                CalculatorDescription.createDescription(
                        "Шкала комы Глазго (Glasgow Coma Scale, GCS) — международно признанный инструмент для объективной оценки уровня сознания у пациентов с черепно-мозговой травмой, инсультом, инфекциями ЦНС, после остановки сердца и в других критических состояниях.\n" +
                                "Интерпретация результата:\n" +
                                "Суммарная оценка: от 3 до 15 баллов.\n" +
                                "\n" +
                                "13–15 — лёгкое нарушение сознания\n" +
                                "9–12 — умеренное нарушение\n" +
                                "≤8 — кома, высокий риск летального исхода, показана интубация\n" +
                                "Клиническое применение:\n" +
                                "GCS применяется для:\n" +
                                "\n" +
                                "Мониторинга неврологического статуса в динамике;\n" +
                                "Стратификации тяжести травмы головы (в том числе в травм-баллах, например, RTS, APACHE II);\n" +
                                "Принятия решений об интенсивной терапии и маршрутизации пациента.\n" +
                                "При невозможности оценки одного из компонентов (например, интубация) это отражается в виде \"NT\" (Not testable), и сумма баллов адаптируется."
                )
        );

        getChildren().add(container);
    }

    private ComboBox<String> createCombo(String prompt, String... items) {
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getItems().addAll(items);
        cmb.setPromptText(prompt);
        cmb.setMaxWidth(Double.MAX_VALUE);
        return cmb;
    }

    private void bind() {
        model.resultProperty().addListener((obs, oldVal, newVal) -> {
            txtResult.setText(newVal);
            ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 3, 15);
        });
    }

    private void addListeners() {
        cmbEyes.valueProperty().addListener((obs, oldVal, newVal) -> model.setEyesText(newVal));
        cmbVerbal.valueProperty().addListener((obs, oldVal, newVal) -> model.setVerbalText(newVal));
        cmbMotor.valueProperty().addListener((obs, oldVal, newVal) -> model.setMotorText(newVal));
    }

    @Override
    public double getDefaultWidth() { return 700; }
    @Override
    public double getDefaultHeight() { return 600; }
}
