package com.example.demo1.controls.Caprini;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CapriniControl extends StackPane implements CalculatorControl, AutoCloseable {

    private CapriniModel model;

    private Label lblScore;
    private TextField txtAge;
    private ImageView gradientImage;
    private Rectangle marker;

    private static final double SCALE_WIDTH = 400;

    private final ChangeListener<String> ageListener =
            (obs, o, n) -> update(() -> model.setAge(parseInt(n)));

    private final ChangeListener<Object> recalcListener =
            (obs, o, n) -> model.calc();

    private final ChangeListener<Object> scoreListener =
            (obs, o, n) -> updateMarker();

    public CapriniControl(CapriniModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }


    private void initialize() {
        lblScore = new Label("Риск: -");
        lblScore.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");
        txtAge.setMaxWidth(60);

        VBox header = CalculatorHeader.createHeader("Шкала Каприни");

        VBox patientBox = createCategoryBox("Пациент",
                createCheckBox("Варикозные вены", model.varicoseVeins),
                createCheckBox("ИМТ > 25 кг/м2", model.bmiOver25),
                createCheckBox("Сепсис (до 1 мес.)", model.sepsis),
                createCheckBox("Тяжелое заболевание легких (до 1 мес.)", model.lungDisease),
                createCheckBox("Прием ОК/ГЗТ", model.hormones),
                createCheckBox("Беременность/послеродовый период (до 1 мес.)", model.pregnancyPostpartum),
                createCheckBox("Необъяснимые мертворождения/выкидыши", model.priorMiscarriage),
                createCheckBox("Острый инфаркт миокарда", model.acuteMI),
                createCheckBox("Хроническая сердечная недостаточность (до 1 мес.)", model.heartFailure),
                createCheckBox("Постельный режим у нехирургического пациента", model.bedRestNonSurgical),
                createCheckBox("Воспалительные заболевания толстой кишки", model.IBD),
                createCheckBox("ХОБЛ", model.COPD),
                createCheckBox("Отеки нижних конечностей", model.legEdema)
        );

        VBox surgeryBox = createCategoryBox("Хирургия",
                createCheckBox("Малое хирургическое вмешательство", model.minorSurgery),
                createCheckBox("Артроскопическая хирургия", model.arthroscopicSurgery),
                createCheckBox("Злокачественное новообразование", model.malignancy),
                createCheckBox("Лапароскопическое вмешательство (>60 мин.)", model.laparoscopicSurgeryOver60min),
                createCheckBox("Постельный режим >72 ч", model.bedRestOver72h),
                createCheckBox("Иммобилизация конечности (до 1 мес.)", model.limbImmobilization),
                createCheckBox("Катетеризация центральных вен", model.centralLine),
                createCheckBox("Большое хирургическое вмешательство (>45 мин)", model.majorSurgeryOver45min)
        );

        VBox traumaBox = createCategoryBox("Травма / ВТЭО",
                createCheckBox("Личный анамнез ВТЭО", model.priorVTE),
                createCheckBox("Семейный анамнез ВТЭО", model.familyVTE),
                createCheckBox("Полиморфизм Vф Лейден", model.factorVLeiden),
                createCheckBox("Полиморфизм гена IIф 20210A", model.prothrombin20210A),
                createCheckBox("Гипергомоцистеинемия", model.hyperhomocysteinemia),
                createCheckBox("Гепарин-индуцированная тромбоцитопения", model.HIT),
                createCheckBox("Повышенный уровень антител к кардиолипину", model.anticardiolipin),
                createCheckBox("Волчаночный антикоагулянт", model.lupusAnticoagulant),
                createCheckBox("Другие тромбофилии", model.otherThrombophilia),
                createCheckBox("Инсульт (до 1 мес.)", model.recentStroke),
                createCheckBox("Эндопротезирование крупных суставов", model.jointReplacement),
                createCheckBox("Перелом костей", model.fracture),
                createCheckBox("Травма спинного мозга (до 1 мес.)", model.spinalTrauma),
                createCheckBox("Множественная травма (до 1 мес.)", model.multipleTrauma)
        );

        gradientImage = new ImageView(new Image(
                getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm()
        ));
        gradientImage.setFitWidth(SCALE_WIDTH);
        gradientImage.setFitHeight(20);

        marker = new Rectangle(6, 22, Color.BLACK);
        marker.setArcWidth(3);
        marker.setArcHeight(3);

        StackPane gradientPane = new StackPane(gradientImage, marker);
        gradientPane.setAlignment(Pos.CENTER_LEFT);
        gradientPane.setPadding(new Insets(5));

        HBox ticks = new HBox(
                new Label("0–1"), new Label("2"),
                new Label("3–4"), new Label(">4")
        );
        ticks.setSpacing(SCALE_WIDTH / 4.0 - 20);
        ticks.setPadding(new Insets(0, 5, 0, 5));
        ticks.setAlignment(Pos.CENTER_LEFT);

        VBox gradientBox = new VBox(2, gradientPane, ticks);

        VBox left = new VBox(10,
                header,
                txtAge,
                patientBox,
                surgeryBox,
                traumaBox,
                lblScore,
                gradientBox
        );
        left.setPadding(new Insets(5));

        getChildren().add(new HBox(20,
                left,
                CalculatorDescription.createDescription(
                        "Шкала Каприни — оценка риска ВТЭО.\n\n" +
                                "0–1: низкий, 2: умеренный, 3–4: повышенный, >4: высокий."
                )
        ));
    }

    private void bind() {
        model.scoreProperty().addListener(scoreListener);
        model.riskProperty().addListener(scoreListener);
    }

    private void unbind() {
        model.scoreProperty().removeListener(scoreListener);
        model.riskProperty().removeListener(scoreListener);
    }


    private void addListeners() {
        txtAge.textProperty().addListener(ageListener);

        addRecalcTo(
                model.varicoseVeins, model.bmiOver25, model.sepsis, model.lungDisease,
                model.hormones, model.pregnancyPostpartum, model.priorMiscarriage,
                model.acuteMI, model.heartFailure, model.bedRestNonSurgical, model.IBD,
                model.COPD, model.legEdema, model.minorSurgery, model.arthroscopicSurgery,
                model.malignancy, model.laparoscopicSurgeryOver60min, model.bedRestOver72h,
                model.limbImmobilization, model.centralLine, model.majorSurgeryOver45min,
                model.priorVTE, model.familyVTE, model.factorVLeiden, model.prothrombin20210A,
                model.hyperhomocysteinemia, model.HIT, model.anticardiolipin,
                model.lupusAnticoagulant, model.otherThrombophilia, model.recentStroke,
                model.jointReplacement, model.fracture, model.spinalTrauma, model.multipleTrauma
        );
    }

    private void removeListeners() {
        txtAge.textProperty().removeListener(ageListener);

        removeRecalcFrom(
                model.varicoseVeins, model.bmiOver25, model.sepsis, model.lungDisease,
                model.hormones, model.pregnancyPostpartum, model.priorMiscarriage,
                model.acuteMI, model.heartFailure, model.bedRestNonSurgical, model.IBD,
                model.COPD, model.legEdema, model.minorSurgery, model.arthroscopicSurgery,
                model.malignancy, model.laparoscopicSurgeryOver60min, model.bedRestOver72h,
                model.limbImmobilization, model.centralLine, model.majorSurgeryOver45min,
                model.priorVTE, model.familyVTE, model.factorVLeiden, model.prothrombin20210A,
                model.hyperhomocysteinemia, model.HIT, model.anticardiolipin,
                model.lupusAnticoagulant, model.otherThrombophilia, model.recentStroke,
                model.jointReplacement, model.fracture, model.spinalTrauma, model.multipleTrauma
        );
    }

    private void addRecalcTo(BooleanProperty... props) {
        for (BooleanProperty p : props) p.addListener(recalcListener);
    }

    private void removeRecalcFrom(BooleanProperty... props) {
        for (BooleanProperty p : props) p.removeListener(recalcListener);
    }

    private int parseInt(String s) {
        try { return Integer.parseInt(s); }
        catch (Exception ignored) { return 0; }
    }

    private void update(Runnable r) {
        r.run();
        model.calc();
    }

    private void updateMarker() {
        int score = model.getScore();
        double f = (score <= 1) ? 0.0 :
                (score == 2) ? 0.33 :
                        (score <= 4) ? 0.66 : 1.0;

        double x = f * (SCALE_WIDTH - marker.getWidth());

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), marker);
        tt.setToX(x);
        tt.play();

        lblScore.setText(String.format("Риск: %s (баллы: %d)", model.getRisk(), score));
    }

    private CheckBox createCheckBox(String text, BooleanProperty prop) {
        CheckBox chk = new CheckBox(text);
        chk.selectedProperty().bindBidirectional(prop);
        chk.setStyle("-fx-font-size: 11;");
        chk.setMaxWidth(300);
        chk.setWrapText(true);
        return chk;
    }

    private VBox createCategoryBox(String title, CheckBox... boxes) {
        Label lbl = new Label(title);
        lbl.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(5);

        for (int i = 0; i < boxes.length; i++) {
            grid.add(boxes[i], i % 2, i / 2);
        }

        VBox box = new VBox(5, lbl, grid);
        box.setPadding(new Insets(5, 0, 5, 0));
        return box;
    }


    @Override
    public void close() {
        unbind();
        removeListeners();
    }

    @Override
    public double getDefaultWidth() { return 800; }

    @Override
    public double getDefaultHeight() { return 750; }
}
