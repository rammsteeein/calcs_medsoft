package com.example.demo1.controls.CTSI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CTSIControl extends StackPane
        implements AutoCloseable, CalculatorControl {

    private final CTSIModel model;

    private TextArea result;

    private static final String[] BALTHAZAR_OPTIONS = {

            "0|Стадия A — нормальная поджелудочная железа",

            "1|Стадия B — увеличение железы, легкая гетерогенность паренхимы, небольшие (< 3 см) интрапанкреатические скопления жидкости",

            "2|Стадия C — изменения стадии B + небольшие воспалительные изменения перипанкреатических тканей",

            "3|Стадия D — изменения стадии C + более выраженные перипанкреатические воспалительные изменения, не более одного скопления жидкости",

            "4|Стадия E — множественные и распространенные перипанкреатические скопления жидкости или абсцессы"
    };

    private static final String[] NECROSIS_OPTIONS = {

            "0|Норма — нет некроза, равномерное контрастное усиление",

            "2|Мягкий некроз — менее 30% панкреатической паренхимы",

            "4|Умеренный некроз — 30–50% панкреатической паренхимы",

            "6|Распространенный некроз — более 50% панкреатической паренхимы"
    };

    public CTSIControl(CTSIModel model) {
        this.model = model;
        initialize();
    }

    private void initialize() {

        VBox left = new VBox(
                10,
                CalculatorHeader.createHeader(
                        "КТ-оценка тяжести острого панкреатита (CTSI)"
                )
        );

        ComboBox<String> balthazar = new ComboBox<>();

        balthazar.getItems().addAll(BALTHAZAR_OPTIONS);

        balthazar.setPromptText(
                "Оценка острого воспалительного процесса (по Balthazar)"
        );

        balthazar.setMaxWidth(Double.MAX_VALUE);

        balthazar.valueProperty().addListener((o, a, b) -> {
            model.balthazarProperty().set(b);
            calculate();
        });

        left.getChildren().add(balthazar);

        ComboBox<String> necrosis = new ComboBox<>();

        necrosis.getItems().addAll(NECROSIS_OPTIONS);

        necrosis.setPromptText(
                "Оценка панкреонекроза"
        );

        necrosis.setMaxWidth(Double.MAX_VALUE);

        necrosis.valueProperty().addListener((o, a, b) -> {
            model.necrosisProperty().set(b);
            calculate();
        });

        left.getChildren().add(necrosis);

        result = new TextArea();

        result.setEditable(false);
        result.setWrapText(true);

        result.textProperty().bindBidirectional(
                model.resultProperty()
        );

        left.getChildren().add(result);

        ScrollPane scroll = new ScrollPane(left);

        scroll.setFitToWidth(true);

        String description =
                "CTSI (CT Severity Index, КТ-индекс тяжести острого панкреатита) " +
                        "— составной индекс, предложенный Balthazar и соавторами для " +
                        "КТ-оценки тяжести острого панкреатита.\n\n" +

                        "Индекс складывается из двух компонентов:\n" +
                        "1. Оценки тяжести воспалительного процесса по Balthazar — " +
                        "от 0 до 4 баллов.\n" +
                        "2. Оценки панкреонекроза — от 0 до 6 баллов.\n\n" +

                        "Максимальный суммарный показатель CTSI составляет 10 баллов.\n\n" +

                        "Оценка воспалительного процесса по Balthazar:\n" +
                        "• Стадия A — 0 баллов: нормальная поджелудочная железа.\n" +
                        "• Стадия B — 1 балл: изменения внутри поджелудочной железы — " +
                        "фокальное или диффузное увеличение, легкая гетерогенность " +
                        "паренхимы, небольшие интрапанкреатические скопления жидкости " +
                        "менее 3 см.\n" +
                        "• Стадия C — 2 балла: изменения стадии B с небольшими " +
                        "воспалительными изменениями перипанкреатических тканей.\n" +
                        "• Стадия D — 3 балла: изменения стадии C с более выраженными " +
                        "перипанкреатическими воспалительными изменениями и не более " +
                        "одного скопления жидкости.\n" +
                        "• Стадия E — 4 балла: множественные и распространенные " +
                        "перипанкреатические скопления жидкости или абсцессы.\n\n" +

                        "Оценка панкреонекроза:\n" +
                        "• 0 баллов — некроза нет, равномерное контрастное усиление.\n" +
                        "• 2 балла — менее 30% панкреатической паренхимы.\n" +
                        "• 4 балла — 30–50% панкреатической паренхимы.\n" +
                        "• 6 баллов — более 50% панкреатической паренхимы.\n\n" +

                        "Интерпретация суммарного CTSI:\n" +
                        "• 0–1 балл — осложнения и летальность не отмечались в исходных " +
                        "данных исследования.\n" +
                        "• 2 балла — летальность 0%, осложнения примерно в 4% случаев.\n" +
                        "• 3–6 баллов — промежуточная степень тяжести, риск осложнений " +
                        "возрастает с увеличением индекса.\n" +
                        "• 7–10 баллов — тяжелое поражение; в исходных данных " +
                        "летальность около 17%, осложнения около 92%.\n\n" +

                        "CTSI используется как инструмент КТ-оценки тяжести острого " +
                        "панкреатита и не заменяет клиническую оценку состояния пациента.";

        getChildren().add(
                new HBox(
                        20,
                        scroll,
                        CalculatorDescription.createDescription(
                                description
                        )
                )
        );
    }

    private void calculate() {

        model.calc();

        ResultStyler.applyStyleForValue(
                result,
                model.resultValueProperty().get(),
                4,
                7
        );
    }

    @Override
    public void close() {

        result.textProperty().unbindBidirectional(
                model.resultProperty()
        );
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