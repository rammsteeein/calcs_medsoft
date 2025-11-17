package com.example.demo1.controls.DLCN;

import com.example.demo1.common.interfaces.CalculatorControl;
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

import java.io.Closeable;

public class DLCNControl extends StackPane implements CalculatorControl, Closeable {

    private DLCNModel model;

    private CheckBox chkFamilyEarlyASCVDorHighLDL;
    private CheckBox chkFamilyTendonXanthomasOrChildHighLDL;
    private CheckBox chkPersonalEarlyCHD;
    private CheckBox chkPersonalEarlyCerebrovascularDisease;
    private CheckBox chkTendonXanthomas;
    private CheckBox chkCornealArcusUnder45;

    private ImageView gradientImage;
    private Rectangle marker;
    private Label lblSliderValue;

    private final double SCALE_WIDTH = 300;
    private final double MAX_SCORE = 8.0;

    private final ChangeListener<Boolean> chkFamilyEarlyListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkFamilyTendonListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkPersonalCHDListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkPersonalCerebroListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkTendonListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkCornealListener = (obs, o, n) -> model.calc();

    public DLCNControl(DLCNModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
        updateMarker();
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

        Label lbl0 = new Label("0");
        Label lbl3 = new Label("3");
        Label lbl6 = new Label("6");
        Label lbl8 = new Label("8+");
        lbl0.setStyle("-fx-text-fill: black; -fx-font-size: 11;");
        lbl3.setStyle("-fx-text-fill: black; -fx-font-size: 11;");
        lbl6.setStyle("-fx-text-fill: black; -fx-font-size: 11;");
        lbl8.setStyle("-fx-text-fill: black; -fx-font-size: 11;");

        Pane tickPane = new Pane();
        tickPane.setPrefWidth(SCALE_WIDTH);
        double[] tickPositions = {0.01, 3.0 / 8.0, 6.0 / 8.0, 1.0};
        Label[] labels = {lbl0, lbl3, lbl6, lbl8};
        for (int i = 0; i < labels.length; i++) {
            labels[i].setLayoutX(tickPositions[i] * (SCALE_WIDTH - 6) - 4);
            labels[i].setLayoutY(0);
            tickPane.getChildren().add(labels[i]);
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
        chkFamilyEarlyASCVDorHighLDL.selectedProperty().bindBidirectional(model.familyEarlyASCVDorHighLDLProperty());
        chkFamilyTendonXanthomasOrChildHighLDL.selectedProperty().bindBidirectional(model.familyTendonXanthomasOrChildHighLDLProperty());
        chkPersonalEarlyCHD.selectedProperty().bindBidirectional(model.personalEarlyCHDProperty());
        chkPersonalEarlyCerebrovascularDisease.selectedProperty().bindBidirectional(model.personalEarlyCerebrovascularDiseaseProperty());
        chkTendonXanthomas.selectedProperty().bindBidirectional(model.tendonXanthomasProperty());
        chkCornealArcusUnder45.selectedProperty().bindBidirectional(model.cornealArcusUnder45Property());

        model.resultProperty().addListener((obs, o, n) -> updateMarker());
        model.scoreProperty().addListener((obs, o, n) -> updateMarker());
    }

    private void addListeners() {
        chkFamilyEarlyASCVDorHighLDL.selectedProperty().addListener(chkFamilyEarlyListener);
        chkFamilyTendonXanthomasOrChildHighLDL.selectedProperty().addListener(chkFamilyTendonListener);
        chkPersonalEarlyCHD.selectedProperty().addListener(chkPersonalCHDListener);
        chkPersonalEarlyCerebrovascularDisease.selectedProperty().addListener(chkPersonalCerebroListener);
        chkTendonXanthomas.selectedProperty().addListener(chkTendonListener);
        chkCornealArcusUnder45.selectedProperty().addListener(chkCornealListener);
    }

    private void removeListeners() {
        chkFamilyEarlyASCVDorHighLDL.selectedProperty().removeListener(chkFamilyEarlyListener);
        chkFamilyTendonXanthomasOrChildHighLDL.selectedProperty().removeListener(chkFamilyTendonListener);
        chkPersonalEarlyCHD.selectedProperty().removeListener(chkPersonalCHDListener);
        chkPersonalEarlyCerebrovascularDisease.selectedProperty().removeListener(chkPersonalCerebroListener);
        chkTendonXanthomas.selectedProperty().removeListener(chkTendonListener);
        chkCornealArcusUnder45.selectedProperty().removeListener(chkCornealListener);
    }

    private void unbind() {
        chkFamilyEarlyASCVDorHighLDL.selectedProperty().unbindBidirectional(model.familyEarlyASCVDorHighLDLProperty());
        chkFamilyTendonXanthomasOrChildHighLDL.selectedProperty().unbindBidirectional(model.familyTendonXanthomasOrChildHighLDLProperty());
        chkPersonalEarlyCHD.selectedProperty().unbindBidirectional(model.personalEarlyCHDProperty());
        chkPersonalEarlyCerebrovascularDisease.selectedProperty().unbindBidirectional(model.personalEarlyCerebrovascularDiseaseProperty());
        chkTendonXanthomas.selectedProperty().unbindBidirectional(model.tendonXanthomasProperty());
        chkCornealArcusUnder45.selectedProperty().unbindBidirectional(model.cornealArcusUnder45Property());
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

    @Override
    public void close() {
        unbind();
        removeListeners();
    }
}
