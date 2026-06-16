package com.example.demo1.controls.WIFI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.ResultStyler;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WIFIControl extends StackPane implements CalculatorControl {

    private WIFIModel model;

    private final ToggleGroup woundGroup = new ToggleGroup();
    private final ToggleGroup ischemiaGroup = new ToggleGroup();
    private final ToggleGroup infectionGroup = new ToggleGroup();

    private TextArea txtResult;

    private final ChangeListener<Object> woundListener = (o, ov, nv) -> updateGrade(woundGroup, model.woundProperty());
    private final ChangeListener<Object> ischemiaListener = (o, ov, nv) -> updateGrade(ischemiaGroup, model.ischemiaProperty());
    private final ChangeListener<Object> infectionListener = (o, ov, nv) -> updateGrade(infectionGroup, model.infectionProperty());

    public WIFIControl(WIFIModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
        TabPane tabs = new TabPane(
                new Tab("W — рана", buildGradeSection(woundGroup, WOUND_LABELS)),
                new Tab("I — ишемия", buildGradeSection(ischemiaGroup, ISCHEMIA_LABELS)),
                new Tab("fI — инфекция", buildGradeSection(infectionGroup, INFECTION_LABELS))
        );
        tabs.getTabs().forEach(t -> t.setClosable(false));
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setWrapText(true);
        txtResult.setPrefRowCount(21);
        txtResult.setStyle("-fx-font-family: 'Consolas', 'Courier New', monospace; -fx-font-size: 12px;");

        VBox left = new VBox(10,
                CalculatorHeader.createHeader("Классификация WIfI (Wound, Ischemia, foot Infection)"),
                tabs,
                txtResult
        );

        ScrollPane inputScroll = new ScrollPane(left);
        inputScroll.setFitToWidth(true);

        ScrollPane descScroll = new ScrollPane(CalculatorDescription.createDescription(buildDescription()));
        descScroll.setFitToWidth(true);
        descScroll.setPrefWidth(400);

        getChildren().add(new HBox(20, inputScroll, descScroll));
    }

    private static final String[][] WOUND_LABELS = {
            {"0", "Нет язвы и гангрены. Ишемические боли в покое, раны нет."},
            {"1", "Маленькая поверхностная язва в дистальном отделе; без вовлечения костей (кроме дистальных фаланг)."},
            {"2", "Глубокая язва с вовлечением костей/суставов/сухожилий; гангрена фаланг."},
            {"3", "Обширная глубокая язва или гангрена переднего/среднего отдела или пятки с костью."}
    };

    private static final String[][] ISCHEMIA_LABELS = {
            {"0", "ЛПИ ≥ 0,8; АД голени > 100; пальцевое ≥ 60; TcPO2 ≥ 60."},
            {"1", "ЛПИ 0,6–0,79; АД голени 70–100; пальцевое 40–59; TcPO2 40–59."},
            {"2", "ЛПИ 0,4–0,59; АД голени 50–70; пальцевое 30–39; TcPO2 30–39."},
            {"3", "ЛПИ < 0,4; АД голени < 50; пальцевое < 30; TcPO2 < 30."}
    };

    private static final String[][] INFECTION_LABELS = {
            {"0", "Инфекции нет."},
            {"1", "Лёгкая — ≥ 2 локальных признаков (отёк, эритема 0,5–2 см, болезненность, гипертермия, гной)."},
            {"2", "Средняя — эритема > 2 см или глубокая инфекция без системных признаков."},
            {"3", "Тяжёлая — с ≥ 2 системными признаками воспаления (лихорадка, тахикардия, тахипноэ, лейкоцитоз)."}
    };

    private VBox buildGradeSection(ToggleGroup group, String[][] labels) {
        VBox box = new VBox(8);
        for (String[] entry : labels) {
            RadioButton rb = new RadioButton(entry[0] + " — " + entry[1]);
            rb.setToggleGroup(group);
            rb.setWrapText(true);
            rb.setMaxWidth(Double.MAX_VALUE);
            rb.setUserData(Integer.parseInt(entry[0]));
            box.getChildren().add(rb);
        }
        ScrollPane scroll = new ScrollPane(box);
        scroll.setFitToWidth(true);
        VBox wrapper = new VBox(scroll);
        wrapper.setPrefHeight(280);
        return wrapper;
    }

    private static String buildDescription() {
        return "Классификация WIfI (Wound, Ischemia, foot Infection) учитывает тяжесть "
                + "морфологического поражения тканей стопы, перфузию нижних конечностей "
                + "и тяжесть инфекционного процесса (SVS).\n\n"
                + "W — глубина поражения (0–3)\n"
                + "I — ишемия по ЛПИ, АД голени, пальцевому давлению или TcPO2 (0–3)\n"
                + "fI — инфекция стопы (0–3)\n\n"
                + "По комбинации W–I–fI определяются клинические стадии 1–4:\n"
                + "1 — очень низкий риск\n"
                + "2 — низкий риск\n"
                + "3 — умеренный риск\n"
                + "4 — высокий риск\n\n"
                + "Отдельно рассчитываются:\n"
                + "• риск ампутации в течение 1 года;\n"
                + "• потребность в реваскуляризации.\n\n"
                + "При неинформативном ЛПИ используйте пальцевое давление или TcPO2.";
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());
    }

    private void addListeners() {
        woundGroup.selectedToggleProperty().addListener(woundListener);
        ischemiaGroup.selectedToggleProperty().addListener(ischemiaListener);
        infectionGroup.selectedToggleProperty().addListener(infectionListener);
    }

    private void updateGrade(ToggleGroup group, javafx.beans.property.IntegerProperty target) {
        Toggle selected = group.getSelectedToggle();
        if (selected != null && selected.getUserData() instanceof Integer) {
            target.set((Integer) selected.getUserData());
            calculate();
        }
    }

    private void calculate() {
        model.calc();
        applyStyle();
    }

    private void applyStyle() {
        if (model.woundProperty().get() < 0
                || model.ischemiaProperty().get() < 0
                || model.infectionProperty().get() < 0) {
            ResultStyler.applyStyle(txtResult, ResultStyler.Zone.GRAY);
            return;
        }
        WIFIResult res = WIFICalculator.calc(
                model.woundProperty().get(),
                model.ischemiaProperty().get(),
                model.infectionProperty().get()
        );
        int maxStage = Math.max(res.getAmputationStage(), res.getRevascularizationStage());
        ResultStyler.applyStyleForValue(txtResult, maxStage, 1.5, 3.5);
    }

    @Override
    public double getDefaultWidth() {
        return 900;
    }

    @Override
    public double getDefaultHeight() {
        return 710;
    }
}
