package com.example.demo1.controls.Caprini;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CapriniControl extends StackPane implements CalculatorControl {

    private final CapriniModel model;

    private Label lblScore;
    private TextField txtAge;
    private ImageView gradientImage;
    private Rectangle marker;
    private final double SCALE_WIDTH = 400;

    public CapriniControl(CapriniModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        lblScore = new Label("Риск: -");
        lblScore.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");
        txtAge.setMaxWidth(60);

        VBox header = CalculatorHeader.createHeader("Шкала Каприни");

        // --- Категории ---
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
                createCheckBox("Перелом костей конечности/таза", model.fracture),
                createCheckBox("Травма спинного мозга (до 1 мес.)", model.spinalTrauma),
                createCheckBox("Множественная травма (до 1 мес.)", model.multipleTrauma)
        );

        // --- Градиент и маркер ---
        gradientImage = new ImageView(new Image(
                getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm()
        ));
        gradientImage.setFitWidth(SCALE_WIDTH);
        gradientImage.setFitHeight(20);

        HBox ticks = new HBox();
        ticks.setSpacing(SCALE_WIDTH / 4.0 - 20);
        ticks.setPadding(new Insets(0, 5, 0, 5));
        ticks.getChildren().addAll(
                new Label("0–1"),
                new Label("2"),
                new Label("3–4"),
                new Label(">4")
        );
        ticks.setAlignment(Pos.CENTER_LEFT);

        marker = new Rectangle(6, 22, Color.BLACK);
        marker.setArcWidth(3);
        marker.setArcHeight(3);

        StackPane gradientPane = new StackPane(gradientImage, marker);
        gradientPane.setAlignment(Pos.CENTER_LEFT);
        gradientPane.setPadding(new Insets(5));

        VBox gradientBox = new VBox(2, gradientPane, ticks);

        VBox leftBox = new VBox(10,
                header,
                txtAge,
                patientBox,
                surgeryBox,
                traumaBox,
                lblScore,
                gradientBox
        );
        leftBox.setAlignment(Pos.TOP_LEFT);
        leftBox.setPadding(new Insets(5));

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала Каприни — оценка риска ВТЭО у пациентов.\n" +
                                "Факторы распределены по блокам.\n" +
                                "0–1: низкий риск, 2: умеренный, 3–4: повышенный, >4: высокий риск. \n\n" +
                                "Шкала используется для прогнозирования риска развития венозных тромбоэмболий в " +
                                "послеоперационном периоде у хирургических пациентов.  Результат вычисления позволяет " +
                                "стратифицировать риск ВТЭО и предоставляет рекомендации о том, кого следует выписать с " +
                                "продолжением профилактики.\n" +
                                "\n" +
                                "Оригинальное исследование не включало рекомендации по профилактике ВТЭО, однако шкала " +
                                "валидирована у многих когорт хирургических пациентов. Наиболее широко модель валидирована " +
                                "для оценки риска ВТЭО у хирургических пациентов, включая: общую, пластическую, сосудистую, " +
                                "ОГШ-хирургию, а также хирургических пациентов в ОРИТ."
                )
        ));
    }

    private VBox createCategoryBox(String title, CheckBox... boxes) {
        Label lblTitle = new Label(title);
        lblTitle.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(5);
        int numCols = 2;
        for (int i = 0; i < boxes.length; i++) {
            int col = i % numCols;
            int row = i / numCols;
            boxes[i].setMaxWidth(300);
            boxes[i].setWrapText(true);
            grid.add(boxes[i], col, row);
        }

        VBox container = new VBox(5, lblTitle, grid);
        container.setPadding(new Insets(5, 0, 5, 0));
        return container;
    }

    private VBox createStructuredBox(CheckBox... boxes) {
        VBox container = new VBox(3);
        int numCols = 2;
        for (int i = 0; i < boxes.length; i += numCols) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            for (int j = 0; j < numCols && (i + j) < boxes.length; j++) {
                boxes[i + j].setMinWidth(180);
                boxes[i + j].setStyle("-fx-font-size: 11;");
                row.getChildren().add(boxes[i + j]);
            }
            container.getChildren().add(row);
        }
        return container;
    }

    private CheckBox createCheckBox(String text, BooleanProperty prop) {
        CheckBox chk = new CheckBox(text);
        chk.selectedProperty().bindBidirectional(prop);
        chk.setStyle("-fx-font-size: 11;");
        return chk;
    }

    private void bind() {
        ChangeListener<Object> recalc = (obs, oldV, newV) -> model.calc();

        txtAge.textProperty().addListener((obs, oldV, newV) -> {
            try {
                int age = Integer.parseInt(newV);
                model.setAge(age);
            } catch (NumberFormatException e) {
                model.setAge(0);
            }
            model.calc();
        });

        // Чекбоксы
        model.varicoseVeins.addListener(recalc);
        model.bmiOver25.addListener(recalc);
        model.minorSurgery.addListener(recalc);
        model.sepsis.addListener(recalc);
        model.lungDisease.addListener(recalc);
        model.hormones.addListener(recalc);
        model.pregnancyPostpartum.addListener(recalc);
        model.priorMiscarriage.addListener(recalc);
        model.acuteMI.addListener(recalc);
        model.heartFailure.addListener(recalc);
        model.bedRestNonSurgical.addListener(recalc);
        model.IBD.addListener(recalc);
        model.COPD.addListener(recalc);
        model.legEdema.addListener(recalc);
        model.arthroscopicSurgery.addListener(recalc);
        model.malignancy.addListener(recalc);
        model.laparoscopicSurgeryOver60min.addListener(recalc);
        model.bedRestOver72h.addListener(recalc);
        model.limbImmobilization.addListener(recalc);
        model.centralLine.addListener(recalc);
        model.majorSurgeryOver45min.addListener(recalc);
        model.priorVTE.addListener(recalc);
        model.familyVTE.addListener(recalc);
        model.factorVLeiden.addListener(recalc);
        model.prothrombin20210A.addListener(recalc);
        model.hyperhomocysteinemia.addListener(recalc);
        model.HIT.addListener(recalc);
        model.anticardiolipin.addListener(recalc);
        model.lupusAnticoagulant.addListener(recalc);
        model.otherThrombophilia.addListener(recalc);
        model.recentStroke.addListener(recalc);
        model.jointReplacement.addListener(recalc);
        model.fracture.addListener(recalc);
        model.spinalTrauma.addListener(recalc);
        model.multipleTrauma.addListener(recalc);

        model.scoreProperty().addListener((obs, oldV, newV) -> updateMarker());
        model.riskProperty().addListener((obs, oldV, newV) -> updateMarker());
    }

    private void updateMarker() {
        int score = model.getScore();

        double fraction;
        if (score <= 1) fraction = 0.0;
        else if (score == 2) fraction = 0.33;
        else if (score <= 4) fraction = 0.66;
        else fraction = 1.0;

        double targetX = fraction * (SCALE_WIDTH - marker.getWidth());

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), marker);
        tt.setToX(targetX);
        tt.play();

        lblScore.setText(String.format("Риск: %s (баллы: %d)", model.getRisk(), score));
    }

    @Override
    public double getDefaultWidth() {
        return 800;
    }

    @Override
    public double getDefaultHeight() {
        return 750;
    }
}
