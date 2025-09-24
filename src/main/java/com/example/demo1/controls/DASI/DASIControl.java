package com.example.demo1.controls.DASI;

import com.example.demo1.common.services.CalculatorHeader;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DASIControl extends StackPane {

    private final DASIModel model;

    private CheckBox chkSelfCare;
    private CheckBox chkWalkIndoors;
    private CheckBox chkWalk1to2Blocks;
    private CheckBox chkClimbStairsOrHill;
    private CheckBox chkRunShortDistance;
    private CheckBox chkLightHousework;
    private CheckBox chkModerateHousework;
    private CheckBox chkHeavyHousework;
    private CheckBox chkYardWork;
    private CheckBox chkSexualActivity;
    private CheckBox chkModerateRecreation;
    private CheckBox chkStrenuousSports;

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

        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(5,
                CalculatorHeader.createHeader("Индекс DASI"),
                chkSelfCare, chkWalkIndoors, chkWalk1to2Blocks, chkClimbStairsOrHill,
                chkRunShortDistance, chkLightHousework, chkModerateHousework, chkHeavyHousework,
                chkYardWork, chkSexualActivity, chkModerateRecreation, chkStrenuousSports,
                txtResult
        ));
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

        txtResult.textProperty().bind(model.resultProperty());

        // Пересчет при изменении галочек
        chkSelfCare.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkWalkIndoors.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkWalk1to2Blocks.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkClimbStairsOrHill.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkRunShortDistance.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkLightHousework.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkModerateHousework.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkHeavyHousework.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkYardWork.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkSexualActivity.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkModerateRecreation.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
        chkStrenuousSports.selectedProperty().addListener((obs, oldVal, newVal) -> model.calc());
    }
}
