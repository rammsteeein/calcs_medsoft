package com.example.demo1.controls.DLCN;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class DLCNControl extends StackPane {

    private final DLCNModel model;

    private CheckBox chkFamilyEarlyASCVDorHighLDL;
    private CheckBox chkFamilyTendonXanthomasOrChildHighLDL;
    private CheckBox chkPersonalEarlyCHD;
    private CheckBox chkPersonalEarlyCerebrovascularDisease;
    private CheckBox chkTendonXanthomas;
    private CheckBox chkCornealArcusUnder45;

    private ImageView gradientImage;
    private Rectangle marker;
    private HBox ticksBox;
    private Label lblSliderValue;

    private final double SCALE_WIDTH = 300;
    private final double MAX_SCORE = 8.0;

    public DLCNControl(DLCNModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkFamilyEarlyASCVDorHighLDL = new CheckBox("Раннее ССЗ у родственников / высокий LDL");
        chkFamilyTendonXanthomasOrChildHighLDL = new CheckBox("Ксантомы у родственников или LDL у детей >95%");
        chkPersonalEarlyCHD = new CheckBox("Ранняя ИБС");
        chkPersonalEarlyCerebrovascularDisease = new CheckBox("Раннее поражение мозговых/периферических артерий");
        chkTendonXanthomas = new CheckBox("Ксантомы сухожилия");
        chkCornealArcusUnder45 = new CheckBox("Липидная дуга роговицы <45 лет");

        gradientImage = new ImageView(new Image(
                getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm()
        ));
        gradientImage.setFitWidth(SCALE_WIDTH);
        gradientImage.setFitHeight(20);

        marker = new Rectangle(6, 22, Color.BLACK);
        marker.setArcWidth(3);
        marker.setArcHeight(3);

        StackPane gradientPane = new StackPane();
        gradientPane.setAlignment(Pos.CENTER_LEFT);
        gradientPane.getChildren().addAll(gradientImage, marker);
        gradientPane.setPrefWidth(SCALE_WIDTH);

        ticksBox = new HBox();
        ticksBox.setPrefWidth(SCALE_WIDTH);
        ticksBox.setAlignment(Pos.CENTER_LEFT);
        ticksBox.setSpacing(0);

        double[] tickPositions = {0.01, 3.0 / 8.0, 6.0 / 8.0, 1.0};
        String[] tickLabels = {"0", "3", "6", "8+"};

        Pane tickPane = new Pane();
        tickPane.setPrefWidth(SCALE_WIDTH);
        for (int i = 0; i < tickLabels.length; i++) {
            Label lbl = new Label(tickLabels[i]);
            lbl.setStyle("-fx-text-fill: black; -fx-font-size: 11;");
            lbl.setLayoutX(tickPositions[i] * (SCALE_WIDTH - 6) - 4); // центрируем по тексту
            lbl.setLayoutY(0);
            tickPane.getChildren().add(lbl);
        }

        lblSliderValue = new Label("Результат: FH маловероятна (0)");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Критерии DLCN для диагностики СГХС"),
                chkFamilyEarlyASCVDorHighLDL,
                chkFamilyTendonXanthomasOrChildHighLDL,
                chkPersonalEarlyCHD,
                chkPersonalEarlyCerebrovascularDisease,
                chkTendonXanthomas,
                chkCornealArcusUnder45,
                gradientPane,
                tickPane,
                lblSliderValue
        );
        leftBox.setAlignment(Pos.TOP_LEFT);

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала DLCN используется для диагностики гетерозиготной семейной гиперхолестеринемии (СГХС).\n\n" +
                                "Интерпретация:\n" +
                                "0–2: маловероятно\n" +
                                "3–5: возможная СГХС\n" +
                                "6–8: вероятная СГХС\n" +
                                "≥8: достоверная СГХС"
                )
        ));
    }

    private void bind() {
        ChangeListener<Object> recalcListener = (obs, oldVal, newVal) -> model.calc();

        chkFamilyEarlyASCVDorHighLDL.selectedProperty().bindBidirectional(model.familyEarlyASCVDorHighLDLProperty());
        chkFamilyTendonXanthomasOrChildHighLDL.selectedProperty().bindBidirectional(model.familyTendonXanthomasOrChildHighLDLProperty());
        chkPersonalEarlyCHD.selectedProperty().bindBidirectional(model.personalEarlyCHDProperty());
        chkPersonalEarlyCerebrovascularDisease.selectedProperty().bindBidirectional(model.personalEarlyCerebrovascularDiseaseProperty());
        chkTendonXanthomas.selectedProperty().bindBidirectional(model.tendonXanthomasProperty());
        chkCornealArcusUnder45.selectedProperty().bindBidirectional(model.cornealArcusUnder45Property());

        chkFamilyEarlyASCVDorHighLDL.selectedProperty().addListener(recalcListener);
        chkFamilyTendonXanthomasOrChildHighLDL.selectedProperty().addListener(recalcListener);
        chkPersonalEarlyCHD.selectedProperty().addListener(recalcListener);
        chkPersonalEarlyCerebrovascularDisease.selectedProperty().addListener(recalcListener);
        chkTendonXanthomas.selectedProperty().addListener(recalcListener);
        chkCornealArcusUnder45.selectedProperty().addListener(recalcListener);

        model.resultProperty().addListener((obs, oldVal, newVal) -> updateMarker());
        model.scoreProperty().addListener((obs, oldVal, newVal) -> updateMarker());

        updateMarker();
    }

    private void updateMarker() {
        int score = model.getScore();
        double lineWidth = gradientImage.getFitWidth();
        double maxTravel = lineWidth - marker.getWidth();

        double fraction = Math.min(score / MAX_SCORE, 1.0);
        double targetX = fraction * maxTravel;

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), marker);
        tt.setToX(targetX);
        tt.play();

        lblSliderValue.setText(String.format("Результат: %s (баллы: %d)", model.getResult(), score));
    }
}
