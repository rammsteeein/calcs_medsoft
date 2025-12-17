package com.example.demo1.controls.Cognitive;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CognitiveControl extends StackPane implements AutoCloseable, CalculatorControl {

    private CognitiveModel model;

    private CheckBox chkOrientation1, chkOrientation2, chkOrientation3, chkOrientation4, chkOrientation5;
    private CheckBox chkOrientation6, chkOrientation7, chkOrientation8, chkOrientation9, chkOrientation10;

    private CheckBox chkMemoryBus, chkMemoryDoor, chkMemoryRose;

    private CheckBox chkSubtract1, chkSubtract2, chkSubtract3, chkSubtract4, chkSubtract5;

    private CheckBox chkRecallBus, chkRecallDoor, chkRecallRose;

    private CheckBox chkSpeech16, chkSpeech17, chkSpeech18, chkSpeech19;
    private CheckBox chkSpeech20a, chkSpeech20b, chkSpeech20c;
    private CheckBox chkSpeech21, chkSpeech22;

    private TextArea txtResult;

    private final ChangeListener<Boolean> orientation1Listener = (o, ov, nv) -> { model.orientation1Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation2Listener = (o, ov, nv) -> { model.orientation2Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation3Listener = (o, ov, nv) -> { model.orientation3Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation4Listener = (o, ov, nv) -> { model.orientation4Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation5Listener = (o, ov, nv) -> { model.orientation5Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation6Listener = (o, ov, nv) -> { model.orientation6Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation7Listener = (o, ov, nv) -> { model.orientation7Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation8Listener = (o, ov, nv) -> { model.orientation8Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation9Listener = (o, ov, nv) -> { model.orientation9Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> orientation10Listener = (o, ov, nv) -> { model.orientation10Property().set(nv); calculate(); };

    private final ChangeListener<Boolean> memoryBusListener = (o, ov, nv) -> { model.memoryBusProperty().set(nv); calculate(); };
    private final ChangeListener<Boolean> memoryDoorListener = (o, ov, nv) -> { model.memoryDoorProperty().set(nv); calculate(); };
    private final ChangeListener<Boolean> memoryRoseListener = (o, ov, nv) -> { model.memoryRoseProperty().set(nv); calculate(); };

    private final ChangeListener<Boolean> subtract1Listener = (o, ov, nv) -> { model.subtract1Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> subtract2Listener = (o, ov, nv) -> { model.subtract2Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> subtract3Listener = (o, ov, nv) -> { model.subtract3Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> subtract4Listener = (o, ov, nv) -> { model.subtract4Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> subtract5Listener = (o, ov, nv) -> { model.subtract5Property().set(nv); calculate(); };

    private final ChangeListener<Boolean> recallBusListener = (o, ov, nv) -> { model.recallBusProperty().set(nv); calculate(); };
    private final ChangeListener<Boolean> recallDoorListener = (o, ov, nv) -> { model.recallDoorProperty().set(nv); calculate(); };
    private final ChangeListener<Boolean> recallRoseListener = (o, ov, nv) -> { model.recallRoseProperty().set(nv); calculate(); };

    private final ChangeListener<Boolean> speech16Listener = (o, ov, nv) -> { model.speech16Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> speech17Listener = (o, ov, nv) -> { model.speech17Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> speech18Listener = (o, ov, nv) -> { model.speech18Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> speech19Listener = (o, ov, nv) -> { model.speech19Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> speech20aListener = (o, ov, nv) -> { model.speech20aProperty().set(nv); calculate(); };
    private final ChangeListener<Boolean> speech20bListener = (o, ov, nv) -> { model.speech20bProperty().set(nv); calculate(); };
    private final ChangeListener<Boolean> speech20cListener = (o, ov, nv) -> { model.speech20cProperty().set(nv); calculate(); };
    private final ChangeListener<Boolean> speech21Listener = (o, ov, nv) -> { model.speech21Property().set(nv); calculate(); };
    private final ChangeListener<Boolean> speech22Listener = (o, ov, nv) -> { model.speech22Property().set(nv); calculate(); };

    private final ChangeListener<String> resultListener = (o, ov, nv) -> applyCognitiveStyle();

    public CognitiveControl(CognitiveModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        Label lblSectionA = new Label("А. Ориентация (10 баллов)");
        lblSectionA.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        chkOrientation1 = new CheckBox("1. Какой сейчас год?");
        chkOrientation2 = new CheckBox("2. Какое сейчас время года?");
        chkOrientation3 = new CheckBox("3. Какая сегодня дата?");
        chkOrientation4 = new CheckBox("4. Какой сегодня день недели?");
        chkOrientation5 = new CheckBox("5. Какой сейчас месяц?");
        chkOrientation6 = new CheckBox("6. Скажите, где Вы сейчас находитесь? (клиника/больница)");
        chkOrientation7 = new CheckBox("7. В какой стране Вы находитесь?");
        chkOrientation8 = new CheckBox("8. В каком городе Вы находитесь?");
        chkOrientation9 = new CheckBox("9. Назовите адрес того места, где мы сейчас находимся");
        chkOrientation10 = new CheckBox("10. На каком этаже Вы находитесь?");

        VBox sectionA = new VBox(5, lblSectionA,
                chkOrientation1, chkOrientation2, chkOrientation3, chkOrientation4, chkOrientation5,
                chkOrientation6, chkOrientation7, chkOrientation8, chkOrientation9, chkOrientation10);

        Label lblSectionB = new Label("В. Немедленная память (3 балла)");
        lblSectionB.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        Label lblMemoryInstruction = new Label("Повторите три слова: АВТОБУС, ДВЕРЬ, РОЗА");
        lblMemoryInstruction.setWrapText(true);
        
        chkMemoryBus = new CheckBox("АВТОБУС");
        chkMemoryDoor = new CheckBox("ДВЕРЬ");
        chkMemoryRose = new CheckBox("РОЗА");

        VBox sectionB = new VBox(5, lblSectionB, lblMemoryInstruction,
                chkMemoryBus, chkMemoryDoor, chkMemoryRose);

        Label lblSectionC = new Label("С. Внимание и счет (5 баллов)");
        lblSectionC.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        Label lblAttentionInstruction = new Label("Последовательно вычитайте из 100 число 7:");
        chkSubtract1 = new CheckBox("100 - 7 = 93");
        chkSubtract2 = new CheckBox("93 - 7 = 86");
        chkSubtract3 = new CheckBox("86 - 7 = 79");
        chkSubtract4 = new CheckBox("79 - 7 = 72");
        chkSubtract5 = new CheckBox("72 - 7 = 65");

        VBox sectionC = new VBox(5, lblSectionC, lblAttentionInstruction,
                chkSubtract1, chkSubtract2, chkSubtract3, chkSubtract4, chkSubtract5);

        Label lblSectionD = new Label("D. Воспроизведение слов (3 балла)");
        lblSectionD.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        Label lblRecallInstruction = new Label("Назовите те три слова, которые я просил Вас запомнить:");
        lblRecallInstruction.setWrapText(true);
        
        chkRecallBus = new CheckBox("Автобус");
        chkRecallDoor = new CheckBox("Дверь");
        chkRecallRose = new CheckBox("Роза");

        VBox sectionD = new VBox(5, lblSectionD, lblRecallInstruction,
                chkRecallBus, chkRecallDoor, chkRecallRose);

        Label lblSectionE = new Label("Е. Речь (9 баллов)");
        lblSectionE.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        
        chkSpeech16 = new CheckBox("16. (Покажите наручные часы) Как это называется?");
        chkSpeech17 = new CheckBox("17. (Покажите карандаш) Как это называется?");
        chkSpeech18 = new CheckBox("18. Повторите за мной фразу: «Никаких если, и или но». Только одна попытка");
        chkSpeech19 = new CheckBox("19. Прочитайте слова, которые написаны на этом листе, и сделайте то, что написано. На бумаге написано «Закройте глаза»");
        chkSpeech19.setWrapText(true);
        
        Label lblSpeech20Instruction = new Label("20. Возьмите бумагу в правую руку, согните ее пополам двумя руками и положите на колени. (1 балл за каждый компонент)");
        lblSpeech20Instruction.setWrapText(true);
        chkSpeech20a = new CheckBox("20a. Взять бумагу в правую руку");
        chkSpeech20b = new CheckBox("20b. Согнуть пополам двумя руками");
        chkSpeech20c = new CheckBox("20c. Положить на колени");
        
        chkSpeech21 = new CheckBox("21. Напишите на листе бумаги законченное предложение");
        chkSpeech22 = new CheckBox("22. Вот рисунок, пожалуйста, скопируйте его на том же листе бумаги. Правильный ответ засчитывается, если два пятиугольника пересекаются, образуя при этом четырехугольник");
        chkSpeech22.setWrapText(true);

        VBox sectionE = new VBox(5, lblSectionE,
                chkSpeech16, chkSpeech17, chkSpeech18, chkSpeech19,
                lblSpeech20Instruction, chkSpeech20a, chkSpeech20b, chkSpeech20c,
                chkSpeech21, chkSpeech22);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefHeight(100);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(new VBox(15,
                sectionA,
                new Separator(),
                sectionB,
                new Separator(),
                sectionC,
                new Separator(),
                sectionD,
                new Separator(),
                sectionE,
                new Separator(),
                txtResult
        ));

        getChildren().add(new HBox(20,
                new VBox(10,
                        CalculatorHeader.createHeader("Когнитивная оценка"),
                        scrollPane
                ),
                CalculatorDescription.createDescription(
                        "Когнитивная оценка для определения состояния когнитивных функций.\n\n" +
                                "Максимальный балл: 30\n\n" +
                                "Интерпретация:\n" +
                                "24-30 баллов: Нормальная когнитивная функция\n" +
                                "18-23 балла: Лёгкие когнитивные нарушения\n" +
                                "12-17 баллов: Умеренные когнитивные нарушения\n" +
                                "0-11 баллов: Выраженные когнитивные нарушения"
                )
        ));
    }

    private void bind() {
        txtResult.textProperty().bindBidirectional(model.resultProperty());
        model.resultProperty().addListener(resultListener);
    }

    private void addListeners() {
        chkOrientation1.selectedProperty().addListener(orientation1Listener);
        chkOrientation2.selectedProperty().addListener(orientation2Listener);
        chkOrientation3.selectedProperty().addListener(orientation3Listener);
        chkOrientation4.selectedProperty().addListener(orientation4Listener);
        chkOrientation5.selectedProperty().addListener(orientation5Listener);
        chkOrientation6.selectedProperty().addListener(orientation6Listener);
        chkOrientation7.selectedProperty().addListener(orientation7Listener);
        chkOrientation8.selectedProperty().addListener(orientation8Listener);
        chkOrientation9.selectedProperty().addListener(orientation9Listener);
        chkOrientation10.selectedProperty().addListener(orientation10Listener);

        chkMemoryBus.selectedProperty().addListener(memoryBusListener);
        chkMemoryDoor.selectedProperty().addListener(memoryDoorListener);
        chkMemoryRose.selectedProperty().addListener(memoryRoseListener);

        chkSubtract1.selectedProperty().addListener(subtract1Listener);
        chkSubtract2.selectedProperty().addListener(subtract2Listener);
        chkSubtract3.selectedProperty().addListener(subtract3Listener);
        chkSubtract4.selectedProperty().addListener(subtract4Listener);
        chkSubtract5.selectedProperty().addListener(subtract5Listener);

        chkRecallBus.selectedProperty().addListener(recallBusListener);
        chkRecallDoor.selectedProperty().addListener(recallDoorListener);
        chkRecallRose.selectedProperty().addListener(recallRoseListener);

        chkSpeech16.selectedProperty().addListener(speech16Listener);
        chkSpeech17.selectedProperty().addListener(speech17Listener);
        chkSpeech18.selectedProperty().addListener(speech18Listener);
        chkSpeech19.selectedProperty().addListener(speech19Listener);
        chkSpeech20a.selectedProperty().addListener(speech20aListener);
        chkSpeech20b.selectedProperty().addListener(speech20bListener);
        chkSpeech20c.selectedProperty().addListener(speech20cListener);
        chkSpeech21.selectedProperty().addListener(speech21Listener);
        chkSpeech22.selectedProperty().addListener(speech22Listener);

        model.orientation1Property().addListener((o, ov, nv) -> chkOrientation1.setSelected(nv));
        model.orientation2Property().addListener((o, ov, nv) -> chkOrientation2.setSelected(nv));
        model.orientation3Property().addListener((o, ov, nv) -> chkOrientation3.setSelected(nv));
        model.orientation4Property().addListener((o, ov, nv) -> chkOrientation4.setSelected(nv));
        model.orientation5Property().addListener((o, ov, nv) -> chkOrientation5.setSelected(nv));
        model.orientation6Property().addListener((o, ov, nv) -> chkOrientation6.setSelected(nv));
        model.orientation7Property().addListener((o, ov, nv) -> chkOrientation7.setSelected(nv));
        model.orientation8Property().addListener((o, ov, nv) -> chkOrientation8.setSelected(nv));
        model.orientation9Property().addListener((o, ov, nv) -> chkOrientation9.setSelected(nv));
        model.orientation10Property().addListener((o, ov, nv) -> chkOrientation10.setSelected(nv));

        model.memoryBusProperty().addListener((o, ov, nv) -> chkMemoryBus.setSelected(nv));
        model.memoryDoorProperty().addListener((o, ov, nv) -> chkMemoryDoor.setSelected(nv));
        model.memoryRoseProperty().addListener((o, ov, nv) -> chkMemoryRose.setSelected(nv));

        model.subtract1Property().addListener((o, ov, nv) -> chkSubtract1.setSelected(nv));
        model.subtract2Property().addListener((o, ov, nv) -> chkSubtract2.setSelected(nv));
        model.subtract3Property().addListener((o, ov, nv) -> chkSubtract3.setSelected(nv));
        model.subtract4Property().addListener((o, ov, nv) -> chkSubtract4.setSelected(nv));
        model.subtract5Property().addListener((o, ov, nv) -> chkSubtract5.setSelected(nv));

        model.recallBusProperty().addListener((o, ov, nv) -> chkRecallBus.setSelected(nv));
        model.recallDoorProperty().addListener((o, ov, nv) -> chkRecallDoor.setSelected(nv));
        model.recallRoseProperty().addListener((o, ov, nv) -> chkRecallRose.setSelected(nv));

        model.speech16Property().addListener((o, ov, nv) -> chkSpeech16.setSelected(nv));
        model.speech17Property().addListener((o, ov, nv) -> chkSpeech17.setSelected(nv));
        model.speech18Property().addListener((o, ov, nv) -> chkSpeech18.setSelected(nv));
        model.speech19Property().addListener((o, ov, nv) -> chkSpeech19.setSelected(nv));
        model.speech20aProperty().addListener((o, ov, nv) -> chkSpeech20a.setSelected(nv));
        model.speech20bProperty().addListener((o, ov, nv) -> chkSpeech20b.setSelected(nv));
        model.speech20cProperty().addListener((o, ov, nv) -> chkSpeech20c.setSelected(nv));
        model.speech21Property().addListener((o, ov, nv) -> chkSpeech21.setSelected(nv));
        model.speech22Property().addListener((o, ov, nv) -> chkSpeech22.setSelected(nv));
    }

    private void removeListeners() {
        chkOrientation1.selectedProperty().removeListener(orientation1Listener);
        chkOrientation2.selectedProperty().removeListener(orientation2Listener);
        chkOrientation3.selectedProperty().removeListener(orientation3Listener);
        chkOrientation4.selectedProperty().removeListener(orientation4Listener);
        chkOrientation5.selectedProperty().removeListener(orientation5Listener);
        chkOrientation6.selectedProperty().removeListener(orientation6Listener);
        chkOrientation7.selectedProperty().removeListener(orientation7Listener);
        chkOrientation8.selectedProperty().removeListener(orientation8Listener);
        chkOrientation9.selectedProperty().removeListener(orientation9Listener);
        chkOrientation10.selectedProperty().removeListener(orientation10Listener);

        chkMemoryBus.selectedProperty().removeListener(memoryBusListener);
        chkMemoryDoor.selectedProperty().removeListener(memoryDoorListener);
        chkMemoryRose.selectedProperty().removeListener(memoryRoseListener);

        chkSubtract1.selectedProperty().removeListener(subtract1Listener);
        chkSubtract2.selectedProperty().removeListener(subtract2Listener);
        chkSubtract3.selectedProperty().removeListener(subtract3Listener);
        chkSubtract4.selectedProperty().removeListener(subtract4Listener);
        chkSubtract5.selectedProperty().removeListener(subtract5Listener);

        chkRecallBus.selectedProperty().removeListener(recallBusListener);
        chkRecallDoor.selectedProperty().removeListener(recallDoorListener);
        chkRecallRose.selectedProperty().removeListener(recallRoseListener);

        chkSpeech16.selectedProperty().removeListener(speech16Listener);
        chkSpeech17.selectedProperty().removeListener(speech17Listener);
        chkSpeech18.selectedProperty().removeListener(speech18Listener);
        chkSpeech19.selectedProperty().removeListener(speech19Listener);
        chkSpeech20a.selectedProperty().removeListener(speech20aListener);
        chkSpeech20b.selectedProperty().removeListener(speech20bListener);
        chkSpeech20c.selectedProperty().removeListener(speech20cListener);
        chkSpeech21.selectedProperty().removeListener(speech21Listener);
        chkSpeech22.selectedProperty().removeListener(speech22Listener);

        model.resultProperty().removeListener(resultListener);
    }

    private void unbind() {
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {
        model.calc();
        applyCognitiveStyle();
    }

    private void applyCognitiveStyle() {
        double score = model.resultValueProperty().get();
        if (Double.isNaN(score)) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
            return;
        }

        if (score >= 27) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
        } else if (score < 19) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
        } else {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
        }
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
    }

    @Override
    public double getDefaultWidth() {
        return 1100;
    }

    @Override
    public double getDefaultHeight() {
        return 700;
    }
}

