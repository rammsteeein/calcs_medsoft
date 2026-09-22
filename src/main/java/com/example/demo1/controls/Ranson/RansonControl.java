package com.example.demo1.controls.Ranson;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class RansonControl extends StackPane implements AutoCloseable, CalculatorControl {
    private final RansonModel model; private TextArea result;
    private static final String[] CRITERIA = {
            "При поступлении: возраст более 55 лет",
            "При поступлении: лейкоцитоз более 16 000/мкл",
            "При поступлении: глюкоза более 11,1 ммоль/л",
            "При поступлении: ЛДГ более 350 Ед/л",
            "При поступлении: АСТ более 250 Ед/л",
            "Через 48 часов: снижение гематокрита более 10%",
            "Через 48 часов: повышение мочевины более 1,8 ммоль/л от исходной",
            "Через 48 часов: кальций плазмы менее 2 ммоль/л",
            "Через 48 часов: PaO2 менее 60 мм рт. ст.", "Через 48 часов: дефицит оснований более 4 ммоль/л",
            "Через 48 часов: секвестрация жидкости более 6 л"
    };

    public RansonControl(RansonModel model) {
        this.model=model; initialize();
    }

    private void initialize() {
        VBox left = new VBox(10, CalculatorHeader.createHeader("Шкала Ranson"));
        for (int i=0;i<CRITERIA.length;i++) {
            final int n=i;
            ComboBox<String> c=new ComboBox<>();
            c.getItems().addAll("Нет", "Да");
            c.setPromptText(CRITERIA[i]);
            c.setMaxWidth(Double.MAX_VALUE);
            c.valueProperty().addListener((o,a,b)->{
                model.criterionProperty(n).set(b);
                calculate();}); left.getChildren().add(c);
        }
        result=new TextArea(); result.setEditable(false);
        result.setWrapText(true);
        result.textProperty().bindBidirectional(model.resultProperty());
        left.getChildren().add(result);
        ScrollPane scroll=new ScrollPane(left); scroll.setFitToWidth(true);
        getChildren().add(new HBox(20, scroll, CalculatorDescription.createDescription("Шкала Ranson" +
                " применяется для оценки тяжести острого панкреатита. Первые пять критериев оцениваются при" +
                " поступлении, остальные — через 48 часов.\n\nМенее 3 баллов — ориентировочная летальность" +
                " 1%.\n3–4 балла — 16%.\n5–6 баллов — 40%.\nБолее 6 баллов — до 100%.")));
    }
    private void calculate() {
        model.calc();
        ResultStyler.applyStyleForValue(result, model.resultValueProperty().get(), 3, 6);
    }

    @Override public void close() {
        result.textProperty().unbindBidirectional(model.resultProperty());
    }
    @Override public double getDefaultWidth(){
        return 1000;
    }
    @Override public double getDefaultHeight(){
        return 900;
    }
}
