package com.example.demo1.controls.DLCN;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DLCNControl extends StackPane {

    private final DLCNModel model;

    private CheckBox chkFamilyEarlyASCVDorHighLDL;
    private CheckBox chkFamilyTendonXanthomasOrChildHighLDL;
    private CheckBox chkPersonalEarlyCHD;
    private CheckBox chkPersonalEarlyCerebrovascularDisease;
    private CheckBox chkTendonXanthomas;
    private CheckBox chkCornealArcusUnder45;
    private TextArea txtResult;

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
        txtResult = new TextArea(); txtResult.setEditable(false); txtResult.setPromptText("Результат");

        this.getChildren().add(new VBox(10,
                chkFamilyEarlyASCVDorHighLDL,
                chkFamilyTendonXanthomasOrChildHighLDL,
                chkPersonalEarlyCHD,
                chkPersonalEarlyCerebrovascularDisease,
                chkTendonXanthomas,
                chkCornealArcusUnder45,
                txtResult));
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

        txtResult.textProperty().bind(model.resultProperty());
    }
}
