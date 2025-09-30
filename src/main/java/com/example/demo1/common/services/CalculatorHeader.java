package com.example.demo1.common.services;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CalculatorHeader {

    public static VBox createHeader(String title) {
        Label lblTitle = new Label(title);
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        lblTitle.setAlignment(Pos.CENTER);
        lblTitle.setStyle("-fx-text-fill: #e75133;" +
                " -fx-border-color: #e75133;" +
                " -fx-border-radius: 5px;" +
                " -fx-padding: 8 16 8 16;");

        VBox headerBox = new VBox(lblTitle);
        headerBox.setAlignment(Pos.CENTER);

        return headerBox;
    }
}