package com.example.demo1.controls.SHOKS;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SHOKSControl extends StackPane {

    private final SHOKSModel model;

    private TextField txtOdyshka;
    private TextField txtVes;
    private TextField txtPereboi;
    private TextField txtPolozhenie;
    private TextField txtSheinyeVeny;
    private TextField txtHripy;
    private TextField txtGalop;
    private TextField txtPechen;
    private TextField txtOteki;
    private TextField txtSAD;
    private TextArea txtResult;
    private Button btnCalc;

    public SHOKSControl(SHOKSModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        VBox vbox = new VBox(5);
        vbox.setStyle("-fx-padding: 10;");

        txtOdyshka = new TextField(); txtOdyshka.setPromptText("Одышка (0-2)");
        txtVes = new TextField(); txtVes.setPromptText("Изменение веса (0-1)");
        txtPereboi = new TextField(); txtPereboi.setPromptText("Перебои (0-1)");
        txtPolozhenie = new TextField(); txtPolozhenie.setPromptText("Положение (0-3)");
        txtSheinyeVeny = new TextField(); txtSheinyeVeny.setPromptText("Шейные вены (0-2)");
        txtHripy = new TextField(); txtHripy.setPromptText("Хрипы (0-3)");
        txtGalop = new TextField(); txtGalop.setPromptText("Галоп (0-1)");
        txtPechen = new TextField(); txtPechen.setPromptText("Печень (0-2)");
        txtOteki = new TextField(); txtOteki.setPromptText("Отеки (0-3)");
        txtSAD = new TextField(); txtSAD.setPromptText("САД (0-2)");

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefRowCount(3);

        btnCalc = new Button("Рассчитать");

        vbox.getChildren().addAll(
                txtOdyshka, txtVes, txtPereboi, txtPolozhenie, txtSheinyeVeny,
                txtHripy, txtGalop, txtPechen, txtOteki, txtSAD,
                btnCalc, txtResult
        );

        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);
        getChildren().add(scrollPane);
    }

    private void bind() {
        txtOdyshka.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setOdyshka(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtVes.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setVes(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtPereboi.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setPereboi(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtPolozhenie.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setPolozhenie(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtSheinyeVeny.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setSheinyeVeny(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtHripy.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setHripy(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtGalop.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setGalop(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtPechen.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setPechen(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtOteki.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setOteki(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });
        txtSAD.textProperty().addListener((obs, oldVal, newVal) -> {
            try { model.setSAD(Integer.parseInt(newVal)); } catch (Exception ignored) {}
        });

        txtResult.textProperty().bind(model.resultProperty());

        btnCalc.setOnAction(e -> model.calc());
    }
}
