package com.example.demo1.controls.APACHEII;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class APACHEIIControl extends StackPane implements AutoCloseable, CalculatorControl {
    private final APACHEIIModel model;
    private TextArea result;
    private static final String[] NAMES={"Возраст, лет",
            "Ректальная температура, °C",
            "Среднее артериальное давление, мм рт. ст.",
            "ЧСС, уд/мин","Частота дыхания, дых/мин",
            "Натрий сыворотки, ммоль/л","Калий сыворотки, ммоль/л",
            "Креатинин сыворотки, мкмоль/л (без ОПН)",
            "Гематокрит, %",
            "Лейкоциты, 10⁹/л",
            "Шкала комы Глазго",
            "Оксигенация",
            "pH артериальной крови",
            "Бикарбонат, ммоль/л"
    };
    private static final String[][] OPTIONS={
            {"0|≤ 44",
            "2|45–54",
            "3|55–64",
            "5|65–74",
            "6|> 74"
    },
            {"4|> 40,9",
                    "3|39–40,9",
                    "1|38,5–38,9",
                    "0|36–38,4",
                    "1|34–35,9",
                    "2|32–33,9",
                    "3|30–31,9",
                    "4|< 30"
            },
            {
                "4|> 159","3|130–159","2|110–129",
                    "0|70–109","2|50–69","4|< 50"
            },
            {"4|> 179",
                    "3|140–179",
                    "2|110–139",
                    "0|70–109",
                    "2|55–69",
                    "3|40–54","4|< 40"
            },
            {"4|> 49",
                    "3|35–49",
                    "1|25–34",
                    "0|12–24",
                    "1|10–11",
                    "2|6–9",
                    "4|< 6"},
            {"4|> 179",
                    "3|160–179",
                    "2|155–159",
                    "1|150–154",
                    "0|130–149",
                    "2|120–129",
                    "3|111–119",
                    "4|< 111"},
            {"4|> 6,9",
                    "3|6–6,9",
                    "1|5,5–5,9",
                    "0|3,5–5,4",
                    "1|3–3,4",
                    "2|2,5–2,9","4|< 2,5"},
            {"8|> 300,56 и ОПН",
                    "6|176,8–300,56 и ОПН",
                    "4|> 300,56 и ХПН",
                    "4|132,6–176,7 и ОПН",
                    "3|176,8–300,56 и ХПН",
                    "2|132,6–176,7 и ХПН",
                    "0|53,04–132,5",
                    "2|< 53,04"},
            {"4|> 59,9",
                    "2|50–59,9",
                    "1|46–49,9",
                    "0|30–45,9",
                    "2|20–29,9",
                    "4|< 20"},
            {"4|> 39,9",
                    "2|20–39,9",
                    "1|15–19,9",
                    "0|3,0–14,9",
                    "2|1,0–2,9",
                    "4|< 1,0"},
            {"0|15",
                    "1|14",
                    "2|13",
                    "3|12",
                    "4|11",
                    "5|10",
                    "6|9",
                    "7|8",
                    "8|7",
                    "9|6",
                    "10|5",
                    "11|4",
                    "12|3"},
            {"4|A-a > 499 / PaO₂ < 55",
                    "3|A-a 350–499 / PaO₂ 55–60",
                    "2|A-a 200–349",
                    "1|PaO₂ 61–70",
                    "0|A-a < 200 / PaO₂ > 70"},
            {"4|> 7,69",
                    "3|7,60–7,69",
                    "1|7,50–7,59",
                    "0|7,33–7,49",
                    "2|7,25–7,32",
                    "3|7,15–7,24",
                    "4|< 7,15"},
            {"4|> 52",
                    "3|41–52",
                    "1|32–40,9",
                    "0|22–31,9",
                    "2|18–21,9",
                    "3|15–17,9",
                    "4|< 15"}
    };
    public APACHEIIControl(APACHEIIModel model){
        this.model=model;
        initialize();
    }
    private void initialize(){
        VBox left=new VBox(10,CalculatorHeader.createHeader("Шкала APACHE II"));
        for(int i=0;i<NAMES.length;i++){
            final int n=i;
            ComboBox<String> c=new ComboBox<>();
            c.getItems().addAll(OPTIONS[i]);
            c.setPromptText(NAMES[i]);
            c.setMaxWidth(Double.MAX_VALUE);
            c.valueProperty().addListener((o,a,b)->{
                model.criterionProperty(n).set(b);
                calculate();
            });
            left.getChildren().add(c);
        }
        ComboBox<String>s=new ComboBox<>();
        s.getItems().addAll("0|Неоперированный без тяжёлой органной недостаточности/иммунодефицита",
                "5|Неоперированный с тяжёлой органной недостаточностью/иммунодефицитом",
                "0|Оперированный экстренно без тяжёлой органной недостаточности/иммунодефицита",
                "5|Оперированный экстренно с тяжёлой органной недостаточностью/иммунодефицитом",
                "0|Оперированный планово без тяжёлой органной недостаточности/иммунодефицита",
                "2|Оперированный планово с тяжёлой органной недостаточностью/иммунодефицитом");
        s.setPromptText("Сопутствующая патология и операция");
        s.setMaxWidth(Double.MAX_VALUE);
        s.valueProperty().addListener((o,a,b)->{
            model.surgeryProperty().set(b);calculate();
        });
        left.getChildren().add(s);
            result=new TextArea();
            result.setEditable(false);
            result.setWrapText(true);
            result.textProperty().bindBidirectional(model.resultProperty());
            left.getChildren().add(result);
            ScrollPane scroll=new ScrollPane(left);
            scroll.setFitToWidth(true);
            getChildren().add(new HBox(20,scroll,CalculatorDescription.createDescription("APACHE II — оценка" +
                    " тяжести состояния по наихудшим значениям первых 24 часов госпитализации.\n\nКреатинин при острой" +
                    " почечной недостаточности: начисляемые баллы следует удвоить. Бикарбонат применяют вместо pH," +
                    " когда невозможно оценить газовый состав крови при нормальной оксигенации.\n\nОриентировочная" +
                    " летальность в результате зависит от суммарного балла и статуса операции.")));
    }
    private void calculate(){
        model.calc();
        ResultStyler.applyStyleForValue(result,model.resultValueProperty().get(),
            10,24);
    }
    @Override public void close(){
        result.textProperty().unbindBidirectional(model.resultProperty());
    }
    @Override public double getDefaultWidth(){
        return 1050;
    }
    @Override public double getDefaultHeight(){
        return 900;
    }
}
