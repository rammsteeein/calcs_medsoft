package com.example.demo1.controls.DLCN;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

    // максимум баллов по DLCN шкале
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

        // Градиент
        gradientImage = new ImageView();
        gradientImage.setFitWidth(300);
        gradientImage.setFitHeight(20);
        gradientImage.setImage(
                new Image(getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm())
        );

        // Маркер
        marker = new Rectangle(4, 20);
        marker.setFill(Color.BLACK);

        StackPane gradientPane = new StackPane();
        gradientPane.getChildren().addAll(gradientImage, marker);

        // Подписи для интерпретации баллов
        ticksBox = new HBox();
        ticksBox.setSpacing(0);
        String[] tickLabels = {"0", "3", "6", "8+"};
        for (String label : tickLabels) {
            Label lbl = new Label(label);
            lbl.setPrefWidth(300.0 / (tickLabels.length - 1));
            lbl.setStyle("-fx-text-fill: black; -fx-alignment: center;");
            ticksBox.getChildren().add(lbl);
        }

        lblSliderValue = new Label("Результат: 0");

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Критерии DLCN для диагностики СГХС"),
                chkFamilyEarlyASCVDorHighLDL,
                chkFamilyTendonXanthomasOrChildHighLDL,
                chkPersonalEarlyCHD,
                chkPersonalEarlyCerebrovascularDisease,
                chkTendonXanthomas,
                chkCornealArcusUnder45,
                gradientPane,
                ticksBox,
                lblSliderValue
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала DLCN (Dutch Lipid Clinic Network) используется для диагностики " +
                                "гетерозиготной семейной гиперхолестеринемии (СГХС).\n\n" +
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

        // Обновление слайдера при изменении результата
        model.resultProperty().addListener((obs, oldVal, newVal) -> updateMarker(newVal));

        updateMarker("0");
    }

    private void updateMarker(String result) {
        int score = model.getScore();

        double lineWidth = gradientImage.getFitWidth();
        double targetX = (score / MAX_SCORE) * lineWidth - lineWidth / 2;

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), marker);
        tt.setToX(targetX);
        tt.play();

        lblSliderValue.setText("Результат: " + result + " (баллы: " + score + ")");
    }
}