package com.example.demo1.common.services;

import javafx.scene.control.Label;

public class CalculatorDescription {

    public static Label createDescription(String text) {
        Label lblDescription = new Label();
        lblDescription.setWrapText(true);
        lblDescription.setText(text);
        lblDescription.setMaxWidth(300);
        lblDescription.setStyle("-fx-font-size: 12px;  -fx-border-color: #e75133;" +
                " -fx-border-radius: 5px; -fx-padding: 8 16 8 16;");
        return lblDescription;
    }
}
