package com.example.demo1.controls.GRACE;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GRACEControl extends StackPane {

    private final GRACEModel model;

    private TextField txtAge, txtHR, txtSBP, txtCreatinine;
    private ComboBox<String> cmbKillip, cmbOther;
    private Button btnCalc;
    private TextArea txtResult;

    public GRACEControl(GRACEModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        txtHR = new TextField();
        txtHR.setPromptText("ЧСС (уд/мин)");

        txtSBP = new TextField();
        txtSBP.setPromptText("САД (мм рт.ст.)");

        txtCreatinine = new TextField();
        txtCreatinine.setPromptText("Креатинин (мг/дл)");

        cmbKillip = new ComboBox<>();
        cmbKillip.getItems().addAll("I","II","III","IV");
        cmbKillip.setPromptText("Killip");

        cmbOther = new ComboBox<>();
        cmbOther.getItems().addAll(
                "Нет",
                "Остановка сердца при поступлении",
                "Смещение ST / инверсия T",
                "Повышенные маркеры некроза"
        );
        cmbOther.setPromptText("Другие факторы");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculate());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала GRACE"),
                txtAge, txtHR, txtSBP, txtCreatinine,
                cmbKillip, cmbOther,
                btnCalc,
                txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала GRACE (Global Registry of Acute Coronary Events) – это инструмент оценки риска смерти" +
                                " и инфаркта миокарда у пациентов с острым коронарным синдромом (ОКС)," +
                                " позволяющий определить тяжесть состояния пациента и выбрать оптимальную стратегию лечения." +
                                " Шкала основана на клинических параметрах, ЭКГ-изменениях и биохимических маркерах," +
                                " и ее результаты помогают стратифицировать пациентов на группы низкого, среднего и" +
                                " высокого риска для прогнозирования летального исхода на госпитальном этапе и в течение" +
                                " 6 месяцев после выписки. " +
                                "\n" +
                                "\n" +
                                "Факторы риска: Шкала учитывает такие факторы, как возраст, частота сердечных сокращений," +
                                " систолическое артериальное давление, степень сердечной недостаточности (классификация Killip)," +
                                " наличие остановки сердца, изменения на ЭКГ и уровень кардиоспецифических ферментов и креатинина.\n" +
                                "Интерпретация результатов:\n" +
                                "Низкий риск: Менее 1% смертности (менее 109 баллов).\n" +
                                "Средний риск: От 1 до 3% смертности (109–140 баллов).\n" +
                                "Высокий риск: Более 3% смертности (более 140 баллов)."
                )
        ));
    }

    private void bind() {
        model.resultProperty().addListener((obs, oldV, newV) -> {
            txtResult.setText(newV);

            if (newV == null) return;

            if (newV.contains("Низкий риск")) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
            } else if (newV.contains("Умеренный риск")) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
            } else if (newV.contains("Высокий риск")) {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
            } else {
                ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
            }
        });
    }

    private void calculate() {
        model.ageProperty().set(txtAge.getText());
        model.hrProperty().set(txtHR.getText());
        model.sbpProperty().set(txtSBP.getText());
        model.creatinineProperty().set(txtCreatinine.getText());
        model.killipProperty().set(cmbKillip.getValue());
        model.otherProperty().set(cmbOther.getValue());
        model.calc();
    }
}
