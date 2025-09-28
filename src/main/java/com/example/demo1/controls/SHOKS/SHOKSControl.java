package com.example.demo1.controls.SHOKS;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SHOKSControl extends BorderPane {

    private final SHOKSModel model;

    private TextArea txtResult;
    private Button btnCalc;

    public SHOKSControl(SHOKSModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        VBox fields = new VBox(10,
                CalculatorHeader.createHeader("Шкала SHOKS"),
                createLabeledCombo("Одышка (0–2)", 2, model::setOdyshka),
                createLabeledCombo("Изменение веса (0–1)", 1, model::setVes),
                createLabeledCombo("Перебои (0–1)", 1, model::setPereboi),
                createLabeledCombo("Положение (0–3)", 3, model::setPolozhenie),
                createLabeledCombo("Шейные вены (0–2)", 2, model::setSheinyeVeny),
                createLabeledCombo("Хрипы (0–3)", 3, model::setHripy),
                createLabeledCombo("Галоп (0–1)", 1, model::setGalop),
                createLabeledCombo("Печень (0–2)", 2, model::setPechen),
                createLabeledCombo("Отеки (0–3)", 3, model::setOteki),
                createLabeledCombo("САД (0–2)", 2, model::setSAD)
        );

        btnCalc = new Button("Рассчитать");
        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefRowCount(3);

        fields.getChildren().addAll(btnCalc, txtResult);

        ScrollPane scrollPane = new ScrollPane(fields);
        scrollPane.setFitToWidth(true);

        setCenter(scrollPane);
        setRight(CalculatorDescription.createDescription(
                "Шкала оценки клинического состояния предназначена для оценки тяжести клинических проявлений ХСН." +
                        " В шкалу включены наиболее распространенные симптомы и признаки СН," +
                        " выявляемые при расспросе и физикальном обследовании без применения инструментальных методов." +
                        " Каждый из этих признаков имеет балльную оценку. Сумма баллов соответствует функциональному классу СН." +
                        " Использование данной шкалы в динамике позволяет оценивать эффективность проводимого лечения."
        ));
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());
        btnCalc.setOnAction(e -> model.calc());
    }


    private HBox createLabeledCombo(String label, int maxValue, java.util.function.IntConsumer setter) {
        Label lbl = new Label(label);
        ComboBox<Integer> combo = new ComboBox<>();
        for (int i = 0; i <= maxValue; i++) {
            combo.getItems().add(i);
        }
        combo.valueProperty().addListener((obs, old, val) -> {
            if (val != null) setter.accept(val);
        });
        combo.setPrefWidth(80);

        HBox box = new HBox(10, lbl, combo);
        box.setFillHeight(true);
        return box;
    }
}
