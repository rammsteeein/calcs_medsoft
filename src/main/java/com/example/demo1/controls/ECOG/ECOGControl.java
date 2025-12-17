package com.example.demo1.controls.ECOG;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ECOGControl extends StackPane implements CalculatorControl {

    private ECOGModel model;

    private final ToggleGroup ecogGroup = new ToggleGroup();
    private TextArea txtResult;

    private final ChangeListener<Object> ecogListener = (o, ov, nv) -> {
        RadioButton rb = (RadioButton) ecogGroup.getSelectedToggle();
        if (rb != null && rb.getUserData() instanceof Integer) {
            model.ecogScoreProperty().set((Integer) rb.getUserData());
            calculate();
        }
    };

    private final ChangeListener<String> resultListener = (o, ov, nv) -> applyStyle();

    public ECOGControl(ECOGModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        VBox radioBox = new VBox(5);

        addRadio(radioBox, 0,
                "0 — Пациент полностью активен, способен выполнять все, как и до заболевания");
        addRadio(radioBox, 1,
                "1 — Неспособен выполнять тяжёлую, но может выполнять лёгкую или сидячую работу");
        addRadio(radioBox, 2,
                "2 — Лечится амбулаторно, способен к самообслуживанию, но не может выполнять работу.\n" +
                        "Более 50% времени бодрствования проводит активно, в вертикальном положении");
        addRadio(radioBox, 3,
                "3 — Способен лишь к ограниченному самообслуживанию, проводит в кресле или постели");
        addRadio(radioBox, 4,
                "4 — Инвалид, совершенно не способен к самообслуживанию, прикован к креслу или постели\n");
        addRadio(radioBox, 5,
                "5 — Смерть");

        ScrollPane scrollPane = new ScrollPane(radioBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(350);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(120);

        VBox left = new VBox(10,
                CalculatorHeader.createHeader("Шкала ECOG (0–5 баллов)"),
                scrollPane,
                txtResult
        );

        getChildren().add(new HBox(20,
                left,
                CalculatorDescription.createDescription(
                        "Шкала ECOG оценивает функциональное состояние пациента от 0 (полностью активен) до 5 (смерть).\n\n" +
                                "Интерпретация (общее клиническое значение):\n" +
                                "0–1 — хорошее функциональное состояние, возможна активная терапия\n" +
                                "2–3 — выраженное ограничение, требуется индивидуальный подбор терапии\n" +
                                "4–5 — крайне тяжёлое состояние, как правило, паллиативный или симптоматический подход"
                )
        ));
    }

    private void addRadio(VBox parent, int score, String text) {
        RadioButton rb = new RadioButton(text);
        rb.setToggleGroup(ecogGroup);
        rb.setWrapText(true);
        rb.setMaxWidth(Double.MAX_VALUE);
        rb.setUserData(score);
        parent.getChildren().add(rb);
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());
        model.resultProperty().addListener(resultListener);
    }

    private void addListeners() {
        ecogGroup.selectedToggleProperty().addListener(ecogListener);
    }

    private void calculate() {
        model.calc();
        applyStyle();
    }

    private void applyStyle() {
        double v = model.resultValueProperty().get();
        if (Double.isNaN(v) || v < 0) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
            return;
        }
        ResultStyler.applyStyleForValue(txtResult, v, 1.5, 3.5);
    }

    @Override
    public double getDefaultWidth() {
        return 900;
    }

    @Override
    public double getDefaultHeight() {
        return 500;
    }
}


