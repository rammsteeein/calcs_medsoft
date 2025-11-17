package com.example.demo1.controls.GRACE;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GRACEControl extends StackPane implements CalculatorControl, Closeable {

    private final GRACEModel model;

    private TextField txtAge, txtHR, txtSBP, txtCreatinine;
    private ComboBox<String> cmbUnit;
    private ComboBox<String> cmbKillip;
    private TextArea txtResult;
    private List<CheckBox> otherChecks;

    private final ChangeListener<String> ageListener = (obs, o, n) -> calculate();
    private final ChangeListener<String> hrListener = (obs, o, n) -> calculate();
    private final ChangeListener<String> sbpListener = (obs, o, n) -> calculate();
    private final ChangeListener<String> creatinineListener = (obs, o, n) -> calculate();
    private final ChangeListener<String> unitListener = (obs, o, n) -> calculate();
    private final ChangeListener<String> killipListener = (obs, o, n) -> calculate();
    private final ChangeListener<String> resultListener = (obs, o, n) -> updateResult(n);

    public GRACEControl(GRACEModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        txtAge = new TextField(); txtAge.setPromptText("Возраст (лет)");
        txtHR = new TextField(); txtHR.setPromptText("ЧСС (уд/мин)");
        txtSBP = new TextField(); txtSBP.setPromptText("САД (мм рт.ст.)");
        txtCreatinine = new TextField(); txtCreatinine.setPromptText("Креатинин");

        cmbUnit = new ComboBox<>();
        cmbUnit.getItems().addAll("мг/дл", "мкмоль/л");
        cmbUnit.setValue("мкмоль/л");
        cmbUnit.setPrefWidth(100);

        HBox creatinineBox = new HBox(5, txtCreatinine, cmbUnit);

        cmbKillip = new ComboBox<>();
        cmbKillip.getItems().addAll("I", "II", "III", "IV");
        cmbKillip.setPromptText("Killip");
        Map<String, String> killipHints = new HashMap<>();
        killipHints.put("I", "Нет признаков сердечной недостаточности");
        killipHints.put("II", "Хрипы в лёгких, признаки застоя");
        killipHints.put("III", "Отёк лёгких");
        killipHints.put("IV", "Кардиогенный шок");
        cmbKillip.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); setTooltip(null); }
                else { setText(item); setTooltip(new Tooltip(killipHints.get(item))); }
            }
        });
        cmbKillip.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) { setText(null); }
                else { setText(item); setTooltip(new Tooltip(killipHints.get(item))); }
            }
        });

        VBox otherBox = createOtherFactorsBox();

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Шкала GRACE"),
                txtAge, txtHR, txtSBP,
                creatinineBox,
                cmbKillip,
                otherBox,
                txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала GRACE (Global Registry of Acute Coronary Events) – инструмент оценки риска смерти " +
                                "и инфаркта миокарда у пациентов с ОКС."
                )
        ));
    }

    private VBox createOtherFactorsBox() {
        otherChecks = new ArrayList<>();
        String[] options = { "Остановка сердца при поступлении", "Смещение ST / инверсия T", "Повышенные маркеры некроза" };
        VBox box = new VBox(5, new Label("Другие факторы:"));
        for (String opt : options) {
            CheckBox cb = new CheckBox(opt);
            otherChecks.add(cb);
            box.getChildren().add(cb);
        }
        return box;
    }

    private void bind() {
        model.resultProperty().addListener(resultListener);
    }

    private void addListeners() {
        txtAge.textProperty().addListener(ageListener);
        txtHR.textProperty().addListener(hrListener);
        txtSBP.textProperty().addListener(sbpListener);
        txtCreatinine.textProperty().addListener(creatinineListener);
        cmbUnit.valueProperty().addListener(unitListener);
        cmbKillip.valueProperty().addListener(killipListener);
        for (CheckBox cb : otherChecks) cb.selectedProperty().addListener((obs,o,n) -> calculate());
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener(ageListener);
        txtHR.textProperty().removeListener(hrListener);
        txtSBP.textProperty().removeListener(sbpListener);
        txtCreatinine.textProperty().removeListener(creatinineListener);
        cmbUnit.valueProperty().removeListener(unitListener);
        cmbKillip.valueProperty().removeListener(killipListener);
        for (CheckBox cb : otherChecks) cb.selectedProperty().removeListener((obs,o,n) -> calculate());
        model.resultProperty().removeListener(resultListener);
    }

    private void updateResult(String newVal) {
        txtResult.setText(newVal != null ? newVal : "");
        if (newVal == null) return;
        if (newVal.contains("Низкий риск")) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.LOW);
        else if (newVal.contains("Умеренный риск")) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
        else if (newVal.contains("Высокий риск")) ResultStyler.applyStyle(txtResult, ResultStyler.Zone.HIGH);
        else ResultStyler.applyStyle(txtResult, ResultStyler.Zone.ERROR);
    }

    private void calculate() {
        model.ageProperty().set(txtAge.getText());
        model.hrProperty().set(txtHR.getText());
        model.sbpProperty().set(txtSBP.getText());
        model.creatinineProperty().set(txtCreatinine.getText());
        model.unitProperty().set(cmbUnit.getValue());
        model.killipProperty().set(cmbKillip.getValue());

        List<String> selected = new ArrayList<>();
        for (CheckBox cb : otherChecks) if (cb.isSelected()) selected.add(cb.getText());
        model.otherListProperty().setAll(selected);

        model.calc();
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultWidth() { return 800; }

    @Override
    public double getDefaultHeight() { return 500; }
}
