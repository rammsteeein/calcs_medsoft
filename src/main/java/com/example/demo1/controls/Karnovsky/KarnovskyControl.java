package com.example.demo1.controls.Karnovsky;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class KarnovskyControl extends StackPane implements AutoCloseable, CalculatorControl {

    private KarnovskyModel model;

    private ToggleGroup statusGroup;
    private List<RadioButton> radioButtons;
    private TextArea txtResult;

    private final ChangeListener<Object> statusListener = (o, ov, nv) -> {
        RadioButton selected = (RadioButton) statusGroup.getSelectedToggle();
        if (selected != null) {
            model.statusProperty().set(selected.getText());
            calculate();
        }
    };
    private final ChangeListener<String> resultListener = (o, ov, nv) -> applyKarnofskyStyle();

    public KarnovskyControl(KarnovskyModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        statusGroup = new ToggleGroup();
        radioButtons = new ArrayList<>();
        
        String[] statuses = {
                "Состояние нормальное, жалоб нет",
                "Способен к нормальной деятельности, есть незначительные симптомы",
                "Нормальная активность возможна с усилием, умеренные симптомы",
                "Самообслуживание сохранено, но не способен к нормальной работе",
                "Иногда требуется помощь, большую часть потребностей удовлетворяет самостоятельно",
                "Требует значительной помощи и медицинского обслуживания",
                "Инвалид, нуждается в регулярной специализированной помощи",
                "Тяжёлая инвалидность, показана госпитализация, смерть не неизбежна",
                "Тяжёлый больной, требуется госпитальный уход и активная терапия",
                "Терминальное состояние (агония), стремительное ухудшение",
                "Смерть"
        };

        VBox radioBox = new VBox(5);
        for (String status : statuses) {
            RadioButton rb = new RadioButton(status);
            rb.setToggleGroup(statusGroup);
            rb.setWrapText(true);
            rb.setMaxWidth(Double.MAX_VALUE);
            radioButtons.add(rb);
            radioBox.getChildren().add(rb);
        }

        ScrollPane scrollPane = new ScrollPane(radioBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(400);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(100);

        VBox left = new VBox(10,
                CalculatorHeader.createHeader("Индекс Карновского"),
                scrollPane,
                txtResult
        );

        getChildren().add(new HBox(20,
                left,
                CalculatorDescription.createDescription(
                        "Индекс Карновского (Karnofsky Performance Status) — шкала оценки функционального состояния пациента.\n\n" +
                                "Клиническое применение:\n" +
                                "• Прогнозирование выживаемости и качества жизни\n" +
                                "• Оценка переносимости агрессивного лечения\n" +
                                "• Критерий включения в клинические исследования\n" +
                                "• Мониторинг динамики состояния\n\n" +
                                "Интерпретация:\n" +
                                "70–100% — пациент активен, возможна интенсивная терапия\n" +
                                "40–60% — ограниченная активность, требуется помощь\n" +
                                "10–30% — тяжёлое состояние, паллиативный подход\n" +
                                "0% — смерть\n\n" +
                                "Примечание: шкала служит дополнением к клиническому осмотру и не заменяет врачебное суждение."
                )
        ));
    }

    private void bind() {
        txtResult.textProperty().bindBidirectional(model.resultProperty());
        model.resultProperty().addListener(resultListener);
    }

    private void addListeners() {
        statusGroup.selectedToggleProperty().addListener(statusListener);
        
        // Bind model to UI - when model changes, select corresponding radio button
        model.statusProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                for (RadioButton rb : radioButtons) {
                    if (rb.getText().equals(nv)) {
                        rb.setSelected(true);
                        break;
                    }
                }
            }
        });
    }

    private void removeListeners() {
        statusGroup.selectedToggleProperty().removeListener(statusListener);
        model.resultProperty().removeListener(resultListener);
    }

    private void unbind() {
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {
        model.calc();
        applyKarnofskyStyle();
    }

    private void applyKarnofskyStyle() {
        double score = model.resultValueProperty().get();
        if (Double.isNaN(score)) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
            return;
        }

        // Reversed logic: high scores (70-100) = green (good), low scores (<40) = red (bad)
        if (score >= 70) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW); // Green for good scores
        } else if (score < 40) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH); // Red for bad scores
        } else {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY); // Yellow for medium scores
        }
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
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

