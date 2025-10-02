package com.example.demo1.controls.IMPROVEVTE;

import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class IMPROVETVEControl extends StackPane {

    private final IMPROVETVEModel model;

    private CheckBox chkPriorVTE, chkKnownThrombophilia, chkLowerLimbParalysis;
    private CheckBox chkActiveCancer, chkImmobilization7Days, chkICUstay;
    private TextField txtAge;
    private Label lblSliderValue;

    private ImageView gradientImage;
    private Rectangle marker; // маркер риска
    private HBox ticksBox; // надписи с баллами

    private final double MAX_RISK = 7.2; // максимальный риск по шкале IMPROVE VTE

    public IMPROVETVEControl(IMPROVETVEModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        // Чекбоксы и поле возраста
        chkPriorVTE = new CheckBox("ВТЭО в анамнезе");
        chkKnownThrombophilia = new CheckBox("Известная тромбофилия");
        chkLowerLimbParalysis = new CheckBox("Парез или паралич нижних конечностей");
        chkActiveCancer = new CheckBox("Активный рак");
        chkImmobilization7Days = new CheckBox("Иммобилизация ≥7 дней");
        chkICUstay = new CheckBox("Пребывание в ICU");
        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        lblSliderValue = new Label("Риск: 0%");

        // Gradient PNG
        gradientImage = new ImageView();
        gradientImage.setFitWidth(300);
        gradientImage.setFitHeight(20);
        gradientImage.setImage(
                new Image(getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm())
        );

        // Маркер риска
        marker = new Rectangle(4, 20);
        marker.setFill(Color.BLACK);

        // Объединяем градиент и маркер в StackPane
        StackPane gradientPane = new StackPane();
        gradientPane.getChildren().addAll(gradientImage, marker);

        // Надписи с баллами
        ticksBox = new HBox();
        ticksBox.setSpacing(0);
        String[] tickLabels = {"0", "1", "2", "3", "4", "5–10"};
        for (String label : tickLabels) {
            Label lbl = new Label(label);
            lbl.setPrefWidth(300.0 / (tickLabels.length - 1));
            lbl.setStyle("-fx-text-fill: black; -fx-alignment: center;");
            ticksBox.getChildren().add(lbl);
        }

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("IMPROVE VTE"),
                chkPriorVTE, chkKnownThrombophilia, chkLowerLimbParalysis,
                chkActiveCancer, chkImmobilization7Days, chkICUstay,
                txtAge,
                gradientPane,
                ticksBox,
                lblSliderValue
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "IMPROVE VTE — оценка 3-месячного риска венозной тромбоэмболии у госпитализированных пациентов.\n\n" +
                                "Факторы риска и баллы:\n" +
                                "- ВТЭО в анамнезе: 3\n" +
                                "- Тромбофилия: 2\n" +
                                "- Парез/паралич ног: 2\n" +
                                "- Активный рак: 2\n" +
                                "- Иммобилизация ≥7 дней: 1\n" +
                                "- Пребывание в ICU: 1\n" +
                                "- Возраст >60 лет: 1\n\n" +
                                "Оценка риска:\n" +
                                "0: 0,4%, 1: 0,6%, 2: 1%, 3: 1,7%, 4: 2,9%, ≥5: 7,2%"
                )
        ));
    }

    private void bind() {
        ChangeListener<Object> listener = (obs, oldVal, newVal) -> updateMarker();

        chkPriorVTE.selectedProperty().bindBidirectional(model.priorVTEProperty());
        chkKnownThrombophilia.selectedProperty().bindBidirectional(model.knownThrombophiliaProperty());
        chkLowerLimbParalysis.selectedProperty().bindBidirectional(model.lowerLimbParalysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkImmobilization7Days.selectedProperty().bindBidirectional(model.immobilization7DaysProperty());
        chkICUstay.selectedProperty().bindBidirectional(model.ICUstayProperty());

        chkPriorVTE.selectedProperty().addListener(listener);
        chkKnownThrombophilia.selectedProperty().addListener(listener);
        chkLowerLimbParalysis.selectedProperty().addListener(listener);
        chkActiveCancer.selectedProperty().addListener(listener);
        chkImmobilization7Days.selectedProperty().addListener(listener);
        chkICUstay.selectedProperty().addListener(listener);

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try {
                model.ageProperty().set(Integer.parseInt(newVal));
            } catch (Exception ignored) {}
            updateMarker();
        });

        updateMarker();

    }

    private void updateMarker() {
        IMPROVEResult res = IMPROVETVECalculator.calc(
                model.priorVTEProperty().get(),
                model.knownThrombophiliaProperty().get(),
                model.lowerLimbParalysisProperty().get(),
                model.activeCancerProperty().get(),
                model.immobilization7DaysProperty().get(),
                model.ICUstayProperty().get(),
                model.ageProperty().get()
        );

        double value;
        switch (res.getRisk()) {
            case "0,4%": value = 0.4; break;
            case "0,6%": value = 0.6; break;
            case "1%": value = 1; break;
            case "1,7%": value = 1.7; break;
            case "2,9%": value = 2.9; break;
            default: value = 7.2; break;
        }

        double lineWidth = gradientImage.getFitWidth();
        double targetX = (value / MAX_RISK) * lineWidth - lineWidth / 2;

        // Плавная анимация маркера
        TranslateTransition tt = new TranslateTransition(Duration.millis(300), marker);
        tt.setToX(targetX);
        tt.play();

        lblSliderValue.setText("Риск: " + res.getRisk());
    }
}
