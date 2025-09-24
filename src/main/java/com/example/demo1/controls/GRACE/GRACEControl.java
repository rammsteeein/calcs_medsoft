package com.example.demo1.controls.GRACE;

import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class GRACEControl extends StackPane implements Closeable {

    private GRACEModel model;

    private TextField txtAge, txtHR, txtSBP, txtCreatinine;
    private ComboBox<String> cmbKillip, cmbOtherPoints;
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
        txtSBP.setPromptText("САД (мм рт. ст.)");

        txtCreatinine = new TextField();
        txtCreatinine.setPromptText("Креатинин (мг/дл)");

        cmbOtherPoints = new ComboBox<>();
        cmbOtherPoints.getItems().addAll("Нет", "Остановка сердца при поступлении",
                "Смещения сегмента ST, инверсия зубца T",
                "Повышенный уровень маркеров некроза миокарда");
        cmbOtherPoints.setPromptText("Другие факторы");

        cmbKillip = new ComboBox<>();
        cmbKillip.getItems().addAll("Нет признаков сердечной недостаточности. Пациент в относительно стабильном состоянии",
                "Лёгкая сердечная недостаточность: хрипы в лёгких, небольшие застойные явления, лёгкие одышка или отёки",
                "Выраженная сердечная недостаточность: крупные хрипы, отёки лёгких, острый лёгочный отёк, выраженная одышка",
                "Кардиогенный шок: низкое АД, холодные конечности, тахикардия, олигурия, признаки гипоперфузии органов");
        cmbKillip.setPromptText("Класс Killip");

        btnCalc = new Button("Рассчитать");
        btnCalc.setOnAction(e -> calculateResult());

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат расчёта");

        getChildren().add(new VBox(10, txtAge, txtHR, txtSBP, cmbKillip,
                txtCreatinine, cmbOtherPoints, btnCalc, txtResult));
    }

    private void bind() {
        model.ageProperty().bindBidirectional(txtAge.textProperty());
        model.hrProperty().bindBidirectional(txtHR.textProperty());
        model.sbpProperty().bindBidirectional(txtSBP.textProperty());
        model.killipClassProperty().bindBidirectional(cmbKillip.valueProperty());
        model.creatinineProperty().bindBidirectional(txtCreatinine.textProperty());
        model.otherPointsProperty().bindBidirectional(cmbOtherPoints.valueProperty());
        model.resultProperty().bindBidirectional(txtResult.textProperty());
    }

    private void unbind() {
        model.ageProperty().unbindBidirectional(txtAge.textProperty());
        model.hrProperty().unbindBidirectional(txtHR.textProperty());
        model.sbpProperty().unbindBidirectional(txtSBP.textProperty());
        model.killipClassProperty().unbindBidirectional(cmbKillip.valueProperty());
        model.creatinineProperty().unbindBidirectional(txtCreatinine.textProperty());
        model.otherPointsProperty().unbindBidirectional(cmbOtherPoints.valueProperty());
        model.resultProperty().unbindBidirectional(txtResult.textProperty());
    }

    private void calculateResult() {
        model.calc();
    }

    @Override
    public void close() {
        unbind();
    }
}
