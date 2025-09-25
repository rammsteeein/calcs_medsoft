package com.example.demo1.common.services;

import javafx.scene.control.Label;

public class CalculatorDescription {

    public static Label createDescription(String text) {
        Label lblDescription = new Label();
        lblDescription.setWrapText(true);
        lblDescription.setText(text);
        lblDescription.setMaxWidth(300);
        lblDescription.setStyle("-fx-font-size: 12px; -fx-padding: 10;");
        return lblDescription;
    }
}
