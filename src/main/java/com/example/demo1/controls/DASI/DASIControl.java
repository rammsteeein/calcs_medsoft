package com.example.demo1.controls.DASI;

import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DASIControl extends StackPane {

    private final DASIModel model;

    private CheckBox chkSelfCare, chkWalkIndoors, chkWalk1to2Blocks, chkClimbStairsOrHill,
            chkRunShortDistance, chkLightHousework, chkModerateHousework, chkHeavyHousework,
            chkYardWork, chkSexualActivity, chkModerateRecreation, chkStrenuousSports;

    private TextArea txtResult;

    public DASIControl(DASIModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        chkSelfCare = new CheckBox("Забота о себе");
        chkWalkIndoors = new CheckBox("Гуляйте в помещении");
        chkWalk1to2Blocks = new CheckBox("Пройдите 1-2 квартала");
        chkClimbStairsOrHill = new CheckBox("Поднимитесь по лестнице/холму");
        chkRunShortDistance = new CheckBox("Пробежать короткую дистанцию");
        chkLightHousework = new CheckBox("Легкая работа по дому");
        chkModerateHousework = new CheckBox("Умеренная работа по дому");
        chkHeavyHousework = new CheckBox("Тяжелая работа по дому");
        chkYardWork = new CheckBox("Работа во дворе");
        chkSexualActivity = new CheckBox("Вступать в сексуальные отношения");
        chkModerateRecreation = new CheckBox("Умеренные развлечения");
        chkStrenuousSports = new CheckBox("Интенсивные виды спорта");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPromptText("Результат");

        VBox leftBox = new VBox(5,
                CalculatorHeader.createHeader("Индекс DASI"),
                chkSelfCare, chkWalkIndoors, chkWalk1to2Blocks, chkClimbStairsOrHill,
                chkRunShortDistance, chkLightHousework, chkModerateHousework, chkHeavyHousework,
                chkYardWork, chkSexualActivity, chkModerateRecreation, chkStrenuousSports,
                txtResult
        );

        HBox root = new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Индекс DASI позволяет оценить физическую выносливость пациента.\n" +
                                "VO2 max (мл/кг/мин) = 0,43 * DASI + 9,6\n" +
                                "MET = VO2 max / 3,5"
                )
        );

        getChildren().add(root);
    }

    private void bind() {
        chkSelfCare.selectedProperty().bindBidirectional(model.selfCareProperty());
        chkWalkIndoors.selectedProperty().bindBidirectional(model.walkIndoorsProperty());
        chkWalk1to2Blocks.selectedProperty().bindBidirectional(model.walk1to2BlocksProperty());
        chkClimbStairsOrHill.selectedProperty().bindBidirectional(model.climbStairsOrHillProperty());
        chkRunShortDistance.selectedProperty().bindBidirectional(model.runShortDistanceProperty());
        chkLightHousework.selectedProperty().bindBidirectional(model.lightHouseworkProperty());
        chkModerateHousework.selectedProperty().bindBidirectional(model.moderateHouseworkProperty());
        chkHeavyHousework.selectedProperty().bindBidirectional(model.heavyHouseworkProperty());
        chkYardWork.selectedProperty().bindBidirectional(model.yardWorkProperty());
        chkSexualActivity.selectedProperty().bindBidirectional(model.sexualActivityProperty());
        chkModerateRecreation.selectedProperty().bindBidirectional(model.moderateRecreationProperty());
        chkStrenuousSports.selectedProperty().bindBidirectional(model.strenuousSportsProperty());

        model.resultProperty().addListener((obs, oldVal, newVal) -> {
            txtResult.setText(newVal != null ? newVal.toString() : "");
        });

        chkSelfCare.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkWalkIndoors.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkWalk1to2Blocks.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkClimbStairsOrHill.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkRunShortDistance.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkLightHousework.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkModerateHousework.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkHeavyHousework.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkYardWork.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkSexualActivity.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkModerateRecreation.selectedProperty().addListener((obs, o, n) -> model.calc());
        chkStrenuousSports.selectedProperty().addListener((obs, o, n) -> model.calc());
    }
}
