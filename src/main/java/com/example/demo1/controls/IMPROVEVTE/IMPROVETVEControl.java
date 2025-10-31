package com.example.demo1.controls.IMPROVEVTE;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
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

public class IMPROVETVEControl extends StackPane implements CalculatorControl {

    private final IMPROVETVEModel model;

    private CheckBox chkPriorVTE, chkKnownThrombophilia, chkLowerLimbParalysis;
    private CheckBox chkActiveCancer, chkImmobilization7Days, chkICUstay;
    private TextField txtAge;
    private Label lblSliderValue;

    private ImageView gradientImage;
    private Rectangle marker;

    private final double SCALE_WIDTH = 300;
    private final double MAX_SCORE = 10.0; // 5–10 баллов

    public IMPROVETVEControl(IMPROVETVEModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkPriorVTE = new CheckBox("ВТЭО в анамнезе");
        chkKnownThrombophilia = new CheckBox("Тромбофилия");
        chkLowerLimbParalysis = new CheckBox("Парез/паралич ног");
        chkActiveCancer = new CheckBox("Активный рак");
        chkImmobilization7Days = new CheckBox("Иммобилизация ≥7 дней");
        chkICUstay = new CheckBox("Пребывание в ICU");
        txtAge = new TextField();
        txtAge.setPromptText("Возраст (лет)");

        lblSliderValue = new Label("Риск: 0%");

        // Градиент
        gradientImage = new ImageView(new Image(
                getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm()
        ));
        gradientImage.setFitWidth(SCALE_WIDTH);
        gradientImage.setFitHeight(20);

        // Маркер
        marker = new Rectangle(6, 22, Color.BLACK);
        marker.setArcWidth(3);
        marker.setArcHeight(3);

        StackPane gradientPane = new StackPane();
        gradientPane.setAlignment(Pos.CENTER_LEFT);
        gradientPane.getChildren().addAll(gradientImage, marker);
        gradientPane.setPrefWidth(SCALE_WIDTH);

        double[] tickValues = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] tickLabels = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        Pane tickPane = new Pane();
        tickPane.setPrefWidth(SCALE_WIDTH);

        for (int i = 0; i < tickValues.length; i++) {
            double normalized = tickValues[i] / MAX_SCORE;
            double x = normalized * (SCALE_WIDTH - marker.getWidth());
            Label lbl = new Label(tickLabels[i]);
            lbl.setStyle("-fx-text-fill: black; -fx-font-size: 11;");
            lbl.setLayoutX(x);
            lbl.setLayoutY(0);
            tickPane.getChildren().add(lbl);
        }

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("IMPROVE VTE"),
                chkPriorVTE, chkKnownThrombophilia, chkLowerLimbParalysis,
                chkActiveCancer, chkImmobilization7Days, chkICUstay,
                txtAge,
                gradientPane,
                tickPane,
                lblSliderValue
        );
        leftBox.setAlignment(Pos.TOP_LEFT);

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "IMPROVE VTE — оценка 3-месячного риска венозной тромбоэмболии.\n\n" +
                                "Баллы: ВТЭО:3, Тромбофилия:2, Парез/паралич:2, Рак:2, Иммобилизация:1, ICU:1, Возраст>60:1\n" +
                                "Риск: 0:0,4%, 1:0,6%, 2:1%, 3:1,7%, 4:2,9%, ≥5:7,2%"
                )
        ));
    }

    private void bind() {
        ChangeListener<Object> recalcListener = (obs, oldVal, newVal) -> model.calc();

        chkPriorVTE.selectedProperty().bindBidirectional(model.priorVTEProperty());
        chkKnownThrombophilia.selectedProperty().bindBidirectional(model.knownThrombophiliaProperty());
        chkLowerLimbParalysis.selectedProperty().bindBidirectional(model.lowerLimbParalysisProperty());
        chkActiveCancer.selectedProperty().bindBidirectional(model.activeCancerProperty());
        chkImmobilization7Days.selectedProperty().bindBidirectional(model.immobilization7DaysProperty());
        chkICUstay.selectedProperty().bindBidirectional(model.ICUstayProperty());

        chkPriorVTE.selectedProperty().addListener(recalcListener);
        chkKnownThrombophilia.selectedProperty().addListener(recalcListener);
        chkLowerLimbParalysis.selectedProperty().addListener(recalcListener);
        chkActiveCancer.selectedProperty().addListener(recalcListener);
        chkImmobilization7Days.selectedProperty().addListener(recalcListener);
        chkICUstay.selectedProperty().addListener(recalcListener);

        model.resultProperty().addListener((obs, oldVal, newVal) -> updateMarker());
        model.scoreProperty().addListener((obs, oldVal, newVal) -> updateMarker());

        txtAge.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.ageProperty().set(Integer.parseInt(newVal)); } catch (Exception ignored) {}
            updateMarker();
        });

        updateMarker();
    }

    private void updateMarker() {
        int score = model.getScore();
        double lineWidth = SCALE_WIDTH - marker.getWidth();
        double fraction = Math.min(score / MAX_SCORE, 1.0);
        double targetX = fraction * lineWidth;

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), marker);
        tt.setToX(targetX);
        tt.play();

        lblSliderValue.setText(String.format("Риск: %s (баллы: %d)", model.getResult(), score));
    }
}
