
package com.example.demo1.controls.Morisky;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MoriskyControl extends StackPane implements AutoCloseable, CalculatorControl {

    private final MoriskyModel model;

    private ToggleGroup tg1, tg2, tg3, tg4;
    private TextArea txtResult;

    public MoriskyControl(MoriskyModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        tg1 = new ToggleGroup();
        tg2 = new ToggleGroup();
        tg3 = new ToggleGroup();
        tg4 = new ToggleGroup();

        VBox left = new VBox(12,
                CalculatorHeader.createHeader("Шкала Мориски–Грин"),
                createQuestion("1. Вы когда-нибудь забывали принимать препараты?", tg1),
                createQuestion("2. Не относитесь ли вы невнимательно к часам приема препаратов?", tg2),
                createQuestion("3. Если вы чувствуете себя лучше, вы иногда прекращаете прием препаратов?", tg3),
                createQuestion("4. Иногда, если вы чувствуете себя плохо после приема препаратов, вы пропускаете следующий прием?", tg4)
        );

        txtResult = new TextArea();
        txtResult.setEditable(false);
        left.getChildren().add(txtResult);

        getChildren().add(new HBox(20, left,
                CalculatorDescription.createDescription(
                        "Шкала Мориски-Грина (4-item Morisky Medication Adherence Scale, MMAS-4) —" +
                                " клинико-психологическая тестовая методика для оценки приверженности к лечению." +
                                " Разработана в 1986 году. \n\n" +
                                "Назначение: клинико-психологическая тестовая методика, предназначенная для" +
                                " предварительной оценки комплаентности и скринингового выявления недостаточно" +
                                " комплаентных больных в рутинной врачебной практике. Используется для включения" +
                                " в программу стандартного медицинского обследования людей с хроническими заболеваниями." +
                                " Может быть применена для выделения контингента, нуждающегося в дополнительном внимании" +
                                " как недостаточно приверженного лечению. Используется в научных исследованиях как" +
                                " основной инструмент и как эталон сравнения при разработке новых, более подробных" +
                                " и специализированных шкал.")));
    }

    private VBox createQuestion(String text, ToggleGroup group) {
        Label label = new Label(text);
        RadioButton yes = new RadioButton("Да");
        RadioButton no = new RadioButton("Нет");
        yes.setToggleGroup(group);
        no.setToggleGroup(group);
        return new VBox(4, label, yes, no);
    }

    private void bind() {
        txtResult.textProperty().bindBidirectional(model.resultProperty());
    }

    private void addListeners() {
        tg1.selectedToggleProperty().addListener((o,a,b)->{model.question1Property().set(getValue(b));calculate();});
        tg2.selectedToggleProperty().addListener((o,a,b)->{model.question2Property().set(getValue(b));calculate();});
        tg3.selectedToggleProperty().addListener((o,a,b)->{model.question3Property().set(getValue(b));calculate();});
        tg4.selectedToggleProperty().addListener((o,a,b)->{model.question4Property().set(getValue(b));calculate();});
    }

    private String getValue(Toggle t){
        return t==null?null:((RadioButton)t).getText();
    }

    private void calculate(){
        model.calc();
        ResultStyler.applyStyleForValue(txtResult, model.resultValueProperty().get(),1,2);
    }

    @Override public void close(){ txtResult.textProperty().unbindBidirectional(model.resultProperty()); }

    @Override public double getDefaultWidth(){ return 800; }

    @Override public double getDefaultHeight(){ return 520; }
}
