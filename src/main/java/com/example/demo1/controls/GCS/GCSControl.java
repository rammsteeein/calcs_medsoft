package com.example.demo1.controls.GCS;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;

public class GCSControl extends StackPane implements CalculatorControl {

    private final GCSModel model;

    private final ToggleGroup grpEyes = new ToggleGroup();
    private final ToggleGroup grpVerbal = new ToggleGroup();
    private final ToggleGroup grpMotor = new ToggleGroup();

    private final TextArea txtResult;

    public GCSControl(GCSModel model) {
        this.model = model;

        txtResult = new TextArea();
        txtResult.setEditable(false);

        initialize();
        bind();
        addListeners();
    }

    private void initialize() {

        VBox header = CalculatorHeader.createHeader("Шкала комы Глазго (GCS)");

        VBox eyesBox = createCategoryBox(
                "Открытие глаз (E)",
                grpEyes,
                "4 — спонтанно",
                "3 — на голос",
                "2 — на болевой стимул",
                "1 — нет"
        );

        VBox verbalBox = createCategoryBox(
                "Речевая реакция (V)",
                grpVerbal,
                "5 — ориентирован, отвечает адекватно",
                "4 — спутанная речь",
                "3 — отдельные слова",
                "2 — звуки (мычание, стоны)",
                "1 — нет"
        );

        VBox motorBox = createCategoryBox(
                "Двигательная реакция (M)",
                grpMotor,
                "6 — выполняет команды",
                "5 — локализует болевой стимул",
                "4 — отдергивает конечность",
                "3 — сгибание (децеребрационная)",
                "2 — разгибание (декапитационная)",
                "1 — нет"
        );

        VBox left = new VBox(15,
                header,
                eyesBox,
                verbalBox,
                motorBox,
                txtResult
        );
        left.setPadding(new Insets(10));

        getChildren().add(new HBox(20,
                left,
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
        ));
    }

    private VBox createCategoryBox(String title, ToggleGroup group, String... options) {
        Label lbl = new Label(title);
        lbl.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        VBox box = new VBox(5);
        box.getChildren().add(lbl);

        for (String opt : options) {
            RadioButton rb = new RadioButton(opt);
            rb.setToggleGroup(group);
            rb.setStyle("-fx-font-size: 11;");
            rb.setWrapText(true);
            box.getChildren().add(rb);
        }

        box.setPadding(new Insets(5, 0, 5, 0));
        return box;
    }

    private void bind() {
        model.resultProperty().addListener((obs, oldVal, newVal) -> {
            txtResult.setText(newVal);
            ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 8, 12);
        });
    }

    private void addListeners() {

        ChangeListener<Object> eyesListener = (o, ov, nv) -> {
            RadioButton rb = (RadioButton) grpEyes.getSelectedToggle();
            if (rb != null) model.setEyesText(rb.getText());
        };

        ChangeListener<Object> verbalListener = (o, ov, nv) -> {
            RadioButton rb = (RadioButton) grpVerbal.getSelectedToggle();
            if (rb != null) model.setVerbalText(rb.getText());
        };

        ChangeListener<Object> motorListener = (o, ov, nv) -> {
            RadioButton rb = (RadioButton) grpMotor.getSelectedToggle();
            if (rb != null) model.setMotorText(rb.getText());
        };

        grpEyes.selectedToggleProperty().addListener(eyesListener);
        grpVerbal.selectedToggleProperty().addListener(verbalListener);
        grpMotor.selectedToggleProperty().addListener(motorListener);
    }

    @Override
    public double getDefaultWidth() { return 700; }

    @Override
    public double getDefaultHeight() { return 600; }
}
