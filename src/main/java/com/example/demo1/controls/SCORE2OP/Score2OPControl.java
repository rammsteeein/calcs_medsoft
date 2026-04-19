package com.example.demo1.controls.SCORE2OP;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;
import java.util.Map;

public class Score2OPControl extends StackPane implements AutoCloseable, CalculatorControl {

    private Score2OPModel model;

    private ToggleGroup genderGroup;
    private RadioButton rbMale;
    private RadioButton rbFemale;

    private ComboBox<String> cmbSysAd;
    private ComboBox<String> cmbCholesterol;

    private ComboBox<String> cmbSmoking;

    private TextField txtAge;

    private TextArea txtResult;

    private final Map<String, Integer> sysAdMap = new HashMap<>();
    private final Map<String, Integer> cholesterolMap = new HashMap<>();

    public Score2OPControl(Score2OPModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {

        txtAge = new TextField();
        txtAge.setPromptText("Возраст");

        genderGroup = new ToggleGroup();

        rbMale = new RadioButton("М");
        rbFemale = new RadioButton("Ж");

        rbMale.setToggleGroup(genderGroup);
        rbFemale.setToggleGroup(genderGroup);

        HBox genderBox = new HBox(10, new Label("Пол:"), rbMale, rbFemale);

        cmbSmoking = new ComboBox<>();
        cmbSmoking.getItems().addAll("Нет", "Да");
        cmbSmoking.setPromptText("Курение");

        sysAdMap.put("100–119", 100);
        sysAdMap.put("120–139", 120);
        sysAdMap.put("140–159", 140);
        sysAdMap.put("160–179", 160);

        cmbSysAd = new ComboBox<>();
        cmbSysAd.getItems().addAll(sysAdMap.keySet());
        cmbSysAd.setPromptText("Систолическое АД");

        cholesterolMap.put("3.0–3.9", 3);
        cholesterolMap.put("4.0–4.9", 4);
        cholesterolMap.put("5.0–5.9", 5);
        cholesterolMap.put("6.0–6.9", 6);

        cmbCholesterol = new ComboBox<>();
        cmbCholesterol.getItems().addAll(cholesterolMap.keySet());
        cmbCholesterol.setPromptText("Холестерин");

        txtResult = new TextArea();
        txtResult.setEditable(false);

        VBox left = new VBox(10,
                CalculatorHeader.createHeader("SCORE2-OP"),
                txtAge,
                genderBox,
                cmbSmoking,
                cmbSysAd,
                cmbCholesterol,
                txtResult
        );

        getChildren().add(new HBox(20,
                left,
                CalculatorDescription.createDescription(
                        "SCORE2-OP — это шкала оценки 10-летнего риска развития фатальных и нефатальных" +
                                " сердечно-сосудистых событий у людей старше 70 лет, разработанная Европейским обществом" +
                                " кардиологов (ESC).\n\n" +
                                "Модель используется для прогнозирования вероятности таких событий, как инфаркт миокарда" +
                                " и инсульт, с учётом ключевых факторов риска.\n\n" +
                                "В расчёте учитываются следующие параметры:\n" +
                                "- Возраст (старшие возрастные группы имеют существенно более высокий базовый риск)\n" +
                                "- Пол (мужчины и женщины имеют разные базовые кривые риска)\n" +
                                "- Курение (наличие активного курения значительно повышает риск)\n" +
                                "- Систолическое артериальное давление (уровень нагрузки на сосудистую систему)\n" +
                                "- Общий уровень холестерина (маркер атерогенного риска)\n\n" +
                                "Результат интерпретируется как процент вероятности развития сердечно-сосудистого" +
                                " события в течение ближайших 10 лет и дополнительно классифицируется по категориям" +
                                " риска: низкий, умеренный, высокий и очень высокий."
                )
        ));
    }

    private void bind() {
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void addListeners() {
        txtAge.textProperty().addListener((o, ov, nv) -> {
            try {
                if (!nv.isEmpty()) {
                    model.ageProperty().set(Integer.parseInt(nv));
                    calculate();
                }
            } catch (NumberFormatException ignored) {}
        });

        genderGroup.selectedToggleProperty().addListener((o, ov, nv) -> {
            if (nv == rbMale) model.genderProperty().set("М");
            else if (nv == rbFemale) model.genderProperty().set("Ж");
            calculate();
        });

        cmbSmoking.valueProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                model.smokingProperty().set("Да".equals(nv));
            }
            calculate();
        });

        cmbSysAd.valueProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                model.sysAdProperty().set(sysAdMap.get(nv));
            }
            calculate();
        });

        cmbCholesterol.valueProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                model.cholesterolProperty().set(cholesterolMap.get(nv));
            }
            calculate();
        });

    }

    private void calculate() {
        model.calc();
        ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 5, 15);
    }

    @Override
    public void close() {
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    @Override
    public double getDefaultWidth() {
        return 750;
    }

    @Override
    public double getDefaultHeight() {
        return 580;
    }
}