package com.example.demo1.controls.MoCA;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MoCAControl extends StackPane implements AutoCloseable, CalculatorControl {

    private final MoCAModel model;

    private TextArea txtResult;

    public MoCAControl(MoCAModel model) {

        this.model = model;

        initialize();
        bind();
    }

    private void initialize() {

        VBox root = new VBox(15);

        Label lblVisuospatial = new Label(
                "Оптико-пространственная деятельность / исполнительные функции"
        );
        lblVisuospatial.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        CheckBox chkTrailMaking =
                new CheckBox("Чередование: 1-А-2-Б-3-В-4-Г-5-Д");

        CheckBox chkCube =
                new CheckBox("Копирование куба");

        CheckBox chkClockContour =
                new CheckBox("Часы: контур");

        CheckBox chkClockNumbers =
                new CheckBox("Часы: цифры");

        CheckBox chkClockHands =
                new CheckBox("Часы: стрелки");

        VBox section1 = new VBox(
                5,
                lblVisuospatial,
                chkTrailMaking,
                chkCube,
                chkClockContour,
                chkClockNumbers,
                chkClockHands
        );

        bindCheckBox(chkTrailMaking, model.trailMakingProperty());
        bindCheckBox(chkCube, model.cubeProperty());
        bindCheckBox(chkClockContour, model.clockContourProperty());
        bindCheckBox(chkClockNumbers, model.clockNumbersProperty());
        bindCheckBox(chkClockHands, model.clockHandsProperty());

        Label lblNaming = new Label("Называние");
        lblNaming.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        CheckBox chkLion = new CheckBox("Лев");
        CheckBox chkRhino = new CheckBox("Носорог");
        CheckBox chkCamel = new CheckBox("Верблюд");

        VBox section2 = new VBox(
                5,
                lblNaming,
                chkLion,
                chkRhino,
                chkCamel
        );

        bindCheckBox(chkLion, model.lionProperty());
        bindCheckBox(chkRhino, model.rhinoProperty());
        bindCheckBox(chkCamel, model.camelProperty());

        Label lblAttention = new Label("Внимание");
        lblAttention.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        CheckBox chkAttentionForward =
                new CheckBox("Повтор цифр вперед: 2 1 8 5 4");

        CheckBox chkAttentionBackward =
                new CheckBox("Повтор цифр назад: 7 4 2");

        CheckBox chkAttentionLetterA =
                new CheckBox("Реакция на букву А");

        CheckBox chkSubtract1 = new CheckBox("100 - 7 = 93");
        CheckBox chkSubtract2 = new CheckBox("93 - 7 = 86");
        CheckBox chkSubtract3 = new CheckBox("86 - 7 = 79");
        CheckBox chkSubtract4 = new CheckBox("79 - 7 = 72");
        CheckBox chkSubtract5 = new CheckBox("72 - 7 = 65");

        VBox section3 = new VBox(
                5,
                lblAttention,
                chkAttentionForward,
                chkAttentionBackward,
                chkAttentionLetterA,
                new Label("Последовательное вычитание:"),
                chkSubtract1,
                chkSubtract2,
                chkSubtract3,
                chkSubtract4,
                chkSubtract5
        );

        bindCheckBox(chkAttentionForward, model.attentionForwardProperty());
        bindCheckBox(chkAttentionBackward, model.attentionBackwardProperty());
        bindCheckBox(chkAttentionLetterA, model.attentionLetterAProperty());

        bindCheckBox(chkSubtract1, model.subtract1Property());
        bindCheckBox(chkSubtract2, model.subtract2Property());
        bindCheckBox(chkSubtract3, model.subtract3Property());
        bindCheckBox(chkSubtract4, model.subtract4Property());
        bindCheckBox(chkSubtract5, model.subtract5Property());

        Label lblSpeech = new Label("Речь");
        lblSpeech.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        CheckBox chkSentence1 =
                new CheckBox("«Я не знаю ничего, кроме того что Ваня сегодня дежурит»");

        CheckBox chkSentence2 =
                new CheckBox("«Кошка всегда пряталась под диван, когда собака была в комнате»");

        CheckBox chkFluency =
                new CheckBox("Названо ≥ 11 слов на букву К");

        VBox section4 = new VBox(
                5,
                lblSpeech,
                chkSentence1,
                chkSentence2,
                chkFluency
        );

        bindCheckBox(chkSentence1, model.sentence1Property());
        bindCheckBox(chkSentence2, model.sentence2Property());
        bindCheckBox(chkFluency, model.fluencyProperty());

        Label lblAbstract = new Label("Абстрактное мышление");
        lblAbstract.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        CheckBox chkAbstraction1 =
                new CheckBox("Поезд и велосипед");

        CheckBox chkAbstraction2 =
                new CheckBox("Часы и линейка");

        VBox section5 = new VBox(
                5,
                lblAbstract,
                chkAbstraction1,
                chkAbstraction2
        );

        bindCheckBox(chkAbstraction1, model.abstraction1Property());
        bindCheckBox(chkAbstraction2, model.abstraction2Property());

        Label lblRecall = new Label("Отсроченное воспроизведение");
        lblRecall.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        CheckBox chkRecallFace = new CheckBox("ЛИЦО");
        CheckBox chkRecallVelvet = new CheckBox("ВЕЛЬВЕТ");
        CheckBox chkRecallChurch = new CheckBox("ЦЕРКОВЬ");
        CheckBox chkRecallDaisy = new CheckBox("МАРГАРИТКА");
        CheckBox chkRecallRed = new CheckBox("КРАСНЫЙ");

        VBox section6 = new VBox(
                5,
                lblRecall,
                chkRecallFace,
                chkRecallVelvet,
                chkRecallChurch,
                chkRecallDaisy,
                chkRecallRed
        );

        bindCheckBox(chkRecallFace, model.recallFaceProperty());
        bindCheckBox(chkRecallVelvet, model.recallVelvetProperty());
        bindCheckBox(chkRecallChurch, model.recallChurchProperty());
        bindCheckBox(chkRecallDaisy, model.recallDaisyProperty());
        bindCheckBox(chkRecallRed, model.recallRedProperty());

        Label lblOrientation = new Label("Ориентировка");
        lblOrientation.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        CheckBox chkOrientationDate = new CheckBox("Число");
        CheckBox chkOrientationMonth = new CheckBox("Месяц");
        CheckBox chkOrientationYear = new CheckBox("Год");
        CheckBox chkOrientationDay = new CheckBox("День недели");
        CheckBox chkOrientationPlace = new CheckBox("Место");
        CheckBox chkOrientationCity = new CheckBox("Город");

        VBox section7 = new VBox(
                5,
                lblOrientation,
                chkOrientationDate,
                chkOrientationMonth,
                chkOrientationYear,
                chkOrientationDay,
                chkOrientationPlace,
                chkOrientationCity
        );

        bindCheckBox(chkOrientationDate, model.orientationDateProperty());
        bindCheckBox(chkOrientationMonth, model.orientationMonthProperty());
        bindCheckBox(chkOrientationYear, model.orientationYearProperty());
        bindCheckBox(chkOrientationDay, model.orientationDayProperty());
        bindCheckBox(chkOrientationPlace, model.orientationPlaceProperty());
        bindCheckBox(chkOrientationCity, model.orientationCityProperty());

        Label lblEducation = new Label("Дополнительно");
        lblEducation.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        CheckBox chkLowEducation =
                new CheckBox("Образование ≤ 12 лет (+1 балл)");

        VBox section8 = new VBox(
                5,
                lblEducation,
                chkLowEducation
        );

        bindCheckBox(chkLowEducation, model.lowEducationProperty());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(100);

        root.getChildren().addAll(
                section1,
                new Separator(),
                section2,
                new Separator(),
                section3,
                new Separator(),
                section4,
                new Separator(),
                section5,
                new Separator(),
                section6,
                new Separator(),
                section7,
                new Separator(),
                section8,
                new Separator(),
                txtResult
        );

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        getChildren().add(
                new HBox(
                        20,
                        new VBox(
                                10,
                                CalculatorHeader.createHeader(
                                        "Монреальская шкала когнитивной оценки (MoCA)"
                                ),
                                scrollPane
                        ),
                        CalculatorDescription.createDescription(
                                "Монреальская шкала когнитивной оценки (MoCA) — " +
                                        "скрининговый инструмент для выявления лёгких и умеренных " +
                                        "когнитивных нарушений.\n\n" +

                                        "Шкала оценивает:\n" +
                                        "• внимание и концентрацию\n" +
                                        "• память\n" +
                                        "• исполнительные функции\n" +
                                        "• речь\n" +
                                        "• абстрактное мышление\n" +
                                        "• зрительно-пространственные навыки\n" +
                                        "• ориентировку\n\n" +

                                        "Максимальный балл: 30\n" +
                                        "При образовании ≤ 12 лет добавляется 1 балл.\n\n" +

                                        "Интерпретация результатов:\n" +
                                        "• 26–30 — норма\n" +
                                        "• 18–25 — лёгкие когнитивные нарушения\n" +
                                        "• 10–17 — умеренные когнитивные нарушения\n" +
                                        "• <10 — выраженные когнитивные нарушения\n\n" +

                                        "Время выполнения теста: около 10 минут."
                        )
                )
        );
    }

    private void bind() {

        txtResult.textProperty().bindBidirectional(
                model.resultProperty()
        );

        model.resultProperty().addListener((o, ov, nv) -> {
            applyMoCAStyle();
        });
    }

    private void calculate() {

        model.calc();
        applyMoCAStyle();
    }

    private void bindCheckBox(
            CheckBox checkBox,
            BooleanProperty property
    ) {

        checkBox.selectedProperty().addListener((o, ov, nv) -> {
            property.set(nv);
            calculate();
        });

        property.addListener((o, ov, nv) -> {
            if (checkBox.isSelected() != nv) {
                checkBox.setSelected(nv);
            }
        });
    }

    private void applyMoCAStyle() {

        double score = model.resultValueProperty().get();

        if (Double.isNaN(score)) {
            ResultStyler.applyStyle(
                    txtResult,
                    ResultStyler.Zone.ERROR
            );
            return;
        }

        if (score >= 26) {
            ResultStyler.applyStyle(
                    txtResult,
                    ResultStyler.Zone.LOW
            );
        }
        else if (score < 18) {
            ResultStyler.applyStyle(
                    txtResult,
                    ResultStyler.Zone.HIGH
            );
        }
        else {
            ResultStyler.applyStyle(
                    txtResult,
                    ResultStyler.Zone.GRAY
            );
        }
    }

    @Override
    public void close() {

        txtResult.textProperty().unbindBidirectional(
                model.resultProperty()
        );
    }

    @Override
    public double getDefaultWidth() {
        return 800;
    }

    @Override
    public double getDefaultHeight() {
        return 800;
    }
}