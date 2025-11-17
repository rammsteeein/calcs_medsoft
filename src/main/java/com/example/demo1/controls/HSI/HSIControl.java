package com.example.demo1.controls.HSI;

import com.example.demo1.common.enums.Gender;
import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HSIControl extends StackPane implements CalculatorControl, AutoCloseable {

    private HSIModel model;

    private TextField txtALT;
    private TextField txtAST;
    private TextField txtBMI;
    private ComboBox<Gender> cmbGender;
    private CheckBox chkDiabetes;
    private TextArea txtResult;

    private final ChangeListener<String> altListener = (obs, oldVal, newVal) -> updateDouble(model::setAlt, newVal);
    private final ChangeListener<String> astListener = (obs, oldVal, newVal) -> updateDouble(model::setAst, newVal);
    private final ChangeListener<String> bmiListener = (obs, oldVal, newVal) -> updateDouble(model::setBmi, newVal);
    private final ChangeListener<Gender> genderListener = (obs, oldVal, newVal) -> model.calc();
    private final ChangeListener<Boolean> diabetesListener = (obs, oldVal, newVal) -> model.calc();

    public HSIControl(HSIModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        txtALT = new TextField();
        txtALT.setPromptText("Введите АЛТ (Ед/л)");

        txtAST = new TextField();
        txtAST.setPromptText("Введите АСТ (Ед/л)");

        txtBMI = new TextField();
        txtBMI.setPromptText("Введите ИМТ");

        cmbGender = new ComboBox<>();
        cmbGender.getItems().addAll(Gender.values());
        cmbGender.setPromptText("Пол");
        cmbGender.setValue(Gender.MALE);

        chkDiabetes = new CheckBox("Сахарный диабет");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");
        model.setResultControl(txtResult);

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Индекс стеатоза печени (HSI)"),
                new VBox(new Label("АЛТ (Ед/л)"), txtALT),
                new VBox(new Label("АСТ (Ед/л)"), txtAST),
                new VBox(new Label("ИМТ"), txtBMI),
                new VBox(new Label("Пол"), cmbGender),
                chkDiabetes,
                txtResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "HSI (Hepatic Steatosis Index) — простой показатель для оценки " +
                                "вероятности неалкогольной жировой болезни печени (NAFLD).\n\n" +
                                "Формула:\n" +
                                "HSI = 8 × (АЛТ / АСТ) + ИМТ\n" +
                                "    + 2, если пациент женщина\n" +
                                "    + 2, если у пациента сахарный диабет\n\n" +
                                "Интерпретация:\n" +
                                "- Низкий риск: 0–2 балла\n" +
                                "- Средний риск: 3–4 балла\n" +
                                "- Высокий риск: 5–6 баллов"
                )
        ));
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());

        txtALT.textProperty().addListener(altListener);
        txtAST.textProperty().addListener(astListener);
        txtBMI.textProperty().addListener(bmiListener);
        cmbGender.valueProperty().bindBidirectional(model.genderProperty());
        cmbGender.valueProperty().addListener(genderListener);
        chkDiabetes.selectedProperty().bindBidirectional(model.hasDiabetesProperty());
        chkDiabetes.selectedProperty().addListener(diabetesListener);
    }

    private void addListeners() {
        // все подписки уже добавлены в bind()
    }

    private void removeListeners() {
        txtALT.textProperty().removeListener(altListener);
        txtAST.textProperty().removeListener(astListener);
        txtBMI.textProperty().removeListener(bmiListener);
        cmbGender.valueProperty().removeListener(genderListener);
        chkDiabetes.selectedProperty().removeListener(diabetesListener);
    }

    private void updateDouble(java.util.function.DoubleConsumer setter, String newVal) {
        try {
            setter.accept(Double.parseDouble(newVal));
        } catch (Exception ignored) {}
        model.calc();
    }

    @Override
    public void close() {
        removeListeners();
        txtResult.textProperty().unbind();
    }

    @Override
    public double getDefaultWidth() {
        return 720;
    }

    @Override
    public double getDefaultHeight() {
        return 460;
    }
}
