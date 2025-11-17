package com.example.demo1.controls.CDS;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CDSControl extends StackPane implements AutoCloseable, CalculatorControl {

    private CDSModel model;

    private ComboBox<String> cmbAppearance;
    private ComboBox<String> cmbEyes;
    private ComboBox<String> cmbMucous;
    private ComboBox<String> cmbTears;
    private TextArea txtResult;

    private final ChangeListener<String> appearanceListener = (o, ov, nv) -> { model.appearanceProperty().set(nv); calculate(); };
    private final ChangeListener<String> eyesListener       = (o, ov, nv) -> { model.eyesProperty().set(nv);       calculate(); };
    private final ChangeListener<String> mucousListener     = (o, ov, nv) -> { model.mucousProperty().set(nv);     calculate(); };
    private final ChangeListener<String> tearsListener      = (o, ov, nv) -> { model.tearsProperty().set(nv);      calculate(); };
    private final ChangeListener<String> resultListener     = (o, ov, nv) -> ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 0.5, 4.5);

    public CDSControl(CDSModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        cmbAppearance = createCombo("Внешний вид",
                "Нормальный",
                "Жажда, беспокойство, раздражительность",
                "Вялость, сонливость");

        cmbEyes = createCombo("Глазные яблоки",
                "Тургор нормальный",
                "Слегка запавшие",
                "Запавшие");

        cmbMucous = createCombo("Слизистые оболочки",
                "Влажные",
                "Липкие, суховатые",
                "Сухие");

        cmbTears = createCombo("Слёзы",
                "Слезоотделение в норме",
                "Слезоотделение снижено",
                "Слёзы отсутствуют");

        txtResult = new TextArea();
        txtResult.setEditable(false);

        VBox left = new VBox(10,
                CalculatorHeader.createHeader("Шкала CDS (оценка дегидратации)"),
                cmbAppearance, cmbEyes, cmbMucous, cmbTears, txtResult
        );

        getChildren().add(new HBox(20,
                left,
                CalculatorDescription.createDescription(
                        "Шкала Clinical Dehydration Scale (CDS) используется для оценки степени обезвоживания.\n\n" +
                                "0 — нет дегидратации\n" +
                                "1–4 — лёгкая\n" +
                                "5–8 — средняя/тяжёлая"
                )
        ));
    }

    private ComboBox<String> createCombo(String prompt, String... items) {
        ComboBox<String> cmb = new ComboBox<>();
        cmb.getItems().addAll(items);
        cmb.setPromptText(prompt);
        cmb.setMaxWidth(Double.MAX_VALUE);
        return cmb;
    }

    private void bind() {
        txtResult.textProperty().bindBidirectional(model.resultProperty());
        model.resultProperty().addListener(resultListener);
    }

    private void addListeners() {
        cmbAppearance.valueProperty().addListener(appearanceListener);
        cmbEyes.valueProperty().addListener(eyesListener);
        cmbMucous.valueProperty().addListener(mucousListener);
        cmbTears.valueProperty().addListener(tearsListener);

        model.appearanceProperty().addListener((o, ov, nv) -> cmbAppearance.setValue(nv));
        model.eyesProperty().addListener((o, ov, nv) -> cmbEyes.setValue(nv));
        model.mucousProperty().addListener((o, ov, nv) -> cmbMucous.setValue(nv));
        model.tearsProperty().addListener((o, ov, nv) -> cmbTears.setValue(nv));
    }

    private void removeListeners() {
        cmbAppearance.valueProperty().removeListener(appearanceListener);
        cmbEyes.valueProperty().removeListener(eyesListener);
        cmbMucous.valueProperty().removeListener(mucousListener);
        cmbTears.valueProperty().removeListener(tearsListener);
        model.resultProperty().removeListener(resultListener);
    }

    private void unbind() {
        txtResult.textProperty().unbindBidirectional(model.resultProperty());
    }

    private void calculate() {
        model.calc();
        ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(), 0.5, 4.5);
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
    }

    @Override
    public double getDefaultWidth() {
        return 700;
    }

    @Override
    public double getDefaultHeight() {
        return 430;
    }
}
