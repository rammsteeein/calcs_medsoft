package com.example.demo1.controls.NIHSS;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class NIHSSControl extends StackPane implements AutoCloseable, CalculatorControl {

    private final NIHSSModel model;
    private TextArea txtResult;
    private final List<ChangeListener<Boolean>> listeners = new ArrayList<>();
    private final List<RadioButton> radioButtons = new ArrayList<>();

    public NIHSSControl(NIHSSModel model) {
        this.model = model;
        initialize();
        addListeners();
    }

    private void initialize() {
        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setWrapText(true);
        txtResult.setPrefHeight(80);
        txtResult.setPromptText("Результат");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(10));

        int col = 0, row = 0;

        grid.add(createSection("1a. Уровень бодрствования", model.get("LOC"),
                new String[]{"0 - Ясное сознание", "1 - Оглушение", "2 - Сопор", "3 - Атония"},
                "Общая оценка уровня сознания пациента"), col++, row);
        grid.add(createSection("1b. Уровень бодрствования: ответы на вопросы", model.get("LOC_QUEST"),
                new String[]{"0 - Оба ответа верны", "1 - Один ответ", "2 - Нет ответа"},
                "Пациента просят назвать текущий месяц и возраст"), col++, row);
        grid.add(createSection("1c. Уровень бодрствования: выполнение команд", model.get("LOC_COMMAND"),
                new String[]{"0 - Обе команды", "1 - Одну", "2 - Ни одной"},
                "Пациента просят открыть/закрыть глаза и сжать/разжать кулак"), col++, row);
        grid.add(createSection("2. Движения глазных яблок", model.get("EYE"),
                new String[]{"0 - Норма", "1 - Частичный парез", "2 - Полный паралич"},
                "Оценивается паралич или отклонение глазных яблок"), col++, row);

        row++; col = 0;
        grid.add(createSection("3. Поля зрения", model.get("VISION"),
                new String[]{"0 - Нет нарушения", "1 - Частичная", "2 - Полная", "3 - Слепота"},
                "Проверяется потеря половины поля зрения"), col++, row);
        grid.add(createSection("4. Нарушение функции лицевого нерва", model.get("FACE"),
                new String[]{"0 - Норма", "1 - Легкий парез", "2 - Умеренный парез", "3 - Паралич"},
                "Оценивается симметрия лица при улыбке/поднятии бровей"), col++, row);
        grid.add(createSection("5a. Сила мышц левой руки", model.get("ARM_L"),
                new String[]{"0 - Не опускается", "1 - Начинает опускаться", "2 - Удерживается", "3 - Сразу падает", "4 - исследовать невозможно"},
                "Пациент поднимает руку на 90° и удерживает"), col++, row);
        grid.add(createSection("5b. Сила мышц правой руки", model.get("ARM_R"),
                new String[]{"0 - Не опускается", "1 - Начинает опускаться", "2 - Удерживается", "3 - Сразу падает", "4 - исследовать невозможно"},
                "Пациент поднимает руку на 90° и удерживает"), col++, row);

        row++; col = 0;
        grid.add(createSection("6a. Сила мышц левой ноги", model.get("LEG_L"),
                new String[]{"0 - Не опускается", "1 - Начинает опускаться", "2 - Удерживается", "3 - Сразу падает", "4 - исследовать невозможно"},
                "Пациент поднимает ногу и удерживает"), col++, row);
        grid.add(createSection("6b. Сила мышц правой ноги", model.get("LEG_R"),
                new String[]{"0 - Не опускается", "1 - Начинает опускаться", "2 - Удерживается", "3 - Сразу падает", "4 - исследовать невозможно"},
                "Пациент поднимает ногу и удерживает"), col++, row);
        grid.add(createSection("7. Атаксия в конечностях", model.get("ATAXIA"),
                new String[]{"0 - Нет", "1 - В одной конечности", "2 - В двух/исследовать невозможно"},
                "Оценивается координация движений рук и ног"), col++, row);
        grid.add(createSection("8. Чувствительность", model.get("SENS"),
                new String[]{"0 - Норма", "1 - Легкая/умеренная", "2 - Тяжелая"},
                "Проверяется способность ощущать прикосновения и боль"), col++, row);

        HBox bottomRow = new HBox(20);
        bottomRow.setPadding(new Insets(5,0,0,0));
        bottomRow.getChildren().addAll(
                createSection("9. Речь", model.get("SPEECH"),
                        new String[]{"0 - Норма", "1 - Легкая/умеренная", "2 - Тяжелая", "3 - Мутизм"},
                        "Оценивается способность говорить и понимать"),
                createSection("10. Дизартрия", model.get("DYSARTHRIA"),
                        new String[]{"0 - Норма", "1 - Легкая/умеренная", "2 - Грубая/UN"},
                        "Оценивается чёткость произношения"),
                createSection("11. Гемиигнорирование (неглект)", model.get("NEGLECT"),
                        new String[]{"0 - Норма", "1 - Один вид", "2 - Более одного вида"},
                        "Оценивается игнорирование одной стороны пространства")
        );

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала NIHSS"),
                grid, bottomRow, txtResult
        );

        HBox mainBox = new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "NIHSS (National Institutes of Health Stroke Scale) — шкала оценки тяжести инсульта," +
                                " разработанная Национальным институтом здоровья США. \n\n" +
                                "Назначение: инструмент помогает зафиксировать исходную степень дефицита, " +
                                "отслеживать динамику состояния пациента и прогнозировать исход заболевания.\n\n" +
                                "Баллы рассчитываются по 11 разделам.\n\n" +
                                "Интерпретация:\n" +
                                "0–4 — очень лёгкий инсульт\n" +
                                "5–10 — лёгкий инсульт\n" +
                                "11–15 — умеренный инсульт\n" +
                                "16–20 — умеренно-тяжёлый инсульт\n" +
                                "21+ — тяжёлый инсульт\n\n" +
                                "*Для получения более детальной информации, наведитесь на заголовок критерия"
                )
        );
        mainBox.setPadding(new Insets(10));
        this.getChildren().add(mainBox);
    }

    private VBox createSection(String title, StringProperty property, String[] options, String tooltipText) {
        VBox box = new VBox(5);

        Label lbl = new Label(title);
        if (tooltipText != null && !tooltipText.isEmpty()) {
            Tooltip tooltip = new Tooltip(tooltipText);
            tooltip.setWrapText(true);
            tooltip.setMaxWidth(300);
            lbl.setTooltip(tooltip);
        }
        box.getChildren().add(lbl);

        ToggleGroup group = new ToggleGroup();
        for (String option : options) {
            RadioButton rb = new RadioButton(option);
            rb.setToggleGroup(group);
            rb.setWrapText(true);
            rb.setMaxWidth(Double.MAX_VALUE);

            final String value = option.split(" ")[0];
            ChangeListener<Boolean> listener = (obs, oldVal, newVal) -> {
                if (newVal) {
                    property.set(value);
                    model.calc();
                    txtResult.setText(model.resultProperty().get());
                    ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 10.5, 15.5);
                }
            };
            rb.selectedProperty().addListener(listener);
            listeners.add(listener);
            radioButtons.add(rb);

            box.getChildren().add(rb);
        }

        box.setPadding(new Insets(5));
        box.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5; -fx-border-width: 1; -fx-padding: 5;");
        box.setPrefWidth(200);
        return box;
    }

    private void addListeners() {
        model.resultProperty().addListener((obs, oldVal, newVal) -> txtResult.setText(newVal));
    }

    private void removeListeners() {
        for (int i = 0; i < radioButtons.size(); i++) {
            radioButtons.get(i).selectedProperty().removeListener(listeners.get(i));
        }
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultWidth() { return 1200; }

    @Override
    public double getDefaultHeight() { return 750; }
}
