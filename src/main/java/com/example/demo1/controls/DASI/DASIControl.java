package com.example.demo1.controls.DASI;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.Closeable;

public class DASIControl extends StackPane implements CalculatorControl, Closeable {

    private DASIModel model;

    private CheckBox chkSelfCare, chkWalkIndoors, chkWalk1to2Blocks, chkClimbStairsOrHill,
            chkRunShortDistance, chkLightHousework, chkModerateHousework, chkHeavyHousework,
            chkYardWork, chkSexualActivity, chkModerateRecreation, chkStrenuousSports;

    private TextArea txtResult;

    private final ChangeListener<Boolean> chkSelfCareListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkWalkIndoorsListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkWalk1to2BlocksListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkClimbStairsOrHillListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkRunShortDistanceListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkLightHouseworkListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkModerateHouseworkListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkHeavyHouseworkListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkYardWorkListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkSexualActivityListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkModerateRecreationListener = (obs, o, n) -> model.calc();
    private final ChangeListener<Boolean> chkStrenuousSportsListener = (obs, o, n) -> model.calc();

    public DASIControl(DASIModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
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
    }

    private void addListeners() {
        chkSelfCare.selectedProperty().addListener(chkSelfCareListener);
        chkWalkIndoors.selectedProperty().addListener(chkWalkIndoorsListener);
        chkWalk1to2Blocks.selectedProperty().addListener(chkWalk1to2BlocksListener);
        chkClimbStairsOrHill.selectedProperty().addListener(chkClimbStairsOrHillListener);
        chkRunShortDistance.selectedProperty().addListener(chkRunShortDistanceListener);
        chkLightHousework.selectedProperty().addListener(chkLightHouseworkListener);
        chkModerateHousework.selectedProperty().addListener(chkModerateHouseworkListener);
        chkHeavyHousework.selectedProperty().addListener(chkHeavyHouseworkListener);
        chkYardWork.selectedProperty().addListener(chkYardWorkListener);
        chkSexualActivity.selectedProperty().addListener(chkSexualActivityListener);
        chkModerateRecreation.selectedProperty().addListener(chkModerateRecreationListener);
        chkStrenuousSports.selectedProperty().addListener(chkStrenuousSportsListener);
    }

    private void removeListeners() {
        chkSelfCare.selectedProperty().removeListener(chkSelfCareListener);
        chkWalkIndoors.selectedProperty().removeListener(chkWalkIndoorsListener);
        chkWalk1to2Blocks.selectedProperty().removeListener(chkWalk1to2BlocksListener);
        chkClimbStairsOrHill.selectedProperty().removeListener(chkClimbStairsOrHillListener);
        chkRunShortDistance.selectedProperty().removeListener(chkRunShortDistanceListener);
        chkLightHousework.selectedProperty().removeListener(chkLightHouseworkListener);
        chkModerateHousework.selectedProperty().removeListener(chkModerateHouseworkListener);
        chkHeavyHousework.selectedProperty().removeListener(chkHeavyHouseworkListener);
        chkYardWork.selectedProperty().removeListener(chkYardWorkListener);
        chkSexualActivity.selectedProperty().removeListener(chkSexualActivityListener);
        chkModerateRecreation.selectedProperty().removeListener(chkModerateRecreationListener);
        chkStrenuousSports.selectedProperty().removeListener(chkStrenuousSportsListener);
    }

    private void unbind() {
        chkSelfCare.selectedProperty().unbindBidirectional(model.selfCareProperty());
        chkWalkIndoors.selectedProperty().unbindBidirectional(model.walkIndoorsProperty());
        chkWalk1to2Blocks.selectedProperty().unbindBidirectional(model.walk1to2BlocksProperty());
        chkClimbStairsOrHill.selectedProperty().unbindBidirectional(model.climbStairsOrHillProperty());
        chkRunShortDistance.selectedProperty().unbindBidirectional(model.runShortDistanceProperty());
        chkLightHousework.selectedProperty().unbindBidirectional(model.lightHouseworkProperty());
        chkModerateHousework.selectedProperty().unbindBidirectional(model.moderateHouseworkProperty());
        chkHeavyHousework.selectedProperty().unbindBidirectional(model.heavyHouseworkProperty());
        chkYardWork.selectedProperty().unbindBidirectional(model.yardWorkProperty());
        chkSexualActivity.selectedProperty().unbindBidirectional(model.sexualActivityProperty());
        chkModerateRecreation.selectedProperty().unbindBidirectional(model.moderateRecreationProperty());
        chkStrenuousSports.selectedProperty().unbindBidirectional(model.strenuousSportsProperty());
    }

    @Override
    public void close() {
        unbind();
        removeListeners();
    }
}
