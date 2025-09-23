package com.example.demo1.controls.SHOKS;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Pair;

public class SHOKSControl extends StackPane {

    private SHOKSModel model = new SHOKSModel();

    private ComboBox<Pair<String, Integer>> cbOdyshka;
    private ComboBox<Pair<String, Integer>> cbVes;
    private ComboBox<Pair<String, Integer>> cbPereboi;
    private ComboBox<Pair<String, Integer>> cbPolozhenie;
    private ComboBox<Pair<String, Integer>> cbSheinyeVeny;
    private ComboBox<Pair<String, Integer>> cbHripy;
    private ComboBox<Pair<String, Integer>> cbGalop;
    private ComboBox<Pair<String, Integer>> cbPechen;
    private ComboBox<Pair<String, Integer>> cbOteki;
    private ComboBox<Pair<String, Integer>> cbSAD;

    private Button btnCalc;
    private TextArea txtResult;

    public SHOKSControl() {
        initialize();
    }

    private void initialize() {
        VBox vbox = new VBox(5); // маленькие отступы
        vbox.setStyle("-fx-padding: 10;");

        cbOdyshka = createComboBox(new Pair<>("0 – нет", 0), new Pair<>("1 – при нагрузке", 1), new Pair<>("2 – в покое", 2));
        cbVes = createComboBox(new Pair<>("0 – нет", 0), new Pair<>("1 – увеличился", 1));
        cbPereboi = createComboBox(new Pair<>("0 – нет", 0), new Pair<>("1 – есть", 1));
        cbPolozhenie = createComboBox(
                new Pair<>("0 – горизонтально", 0), new Pair<>("1 – с приподнятым головным концом", 1),
                new Pair<>("2 – плюс просыпается от удушья", 2), new Pair<>("3 – сидя", 3)
        );
        cbSheinyeVeny = createComboBox(new Pair<>("0 – нет", 0), new Pair<>("1 – лежа", 1), new Pair<>("2 – стоя", 2));
        cbHripy = createComboBox(
                new Pair<>("0 – нет", 0), new Pair<>("1 – нижние отделы", 1),
                new Pair<>("2 – до лопаток", 2), new Pair<>("3 – над всей поверхностью легких", 3)
        );
        cbGalop = createComboBox(new Pair<>("0 – нет", 0), new Pair<>("1 – есть", 1));
        cbPechen = createComboBox(new Pair<>("0 – не увеличена", 0), new Pair<>("1 – до 5 см", 1), new Pair<>("2 – более 5 см", 2));
        cbOteki = createComboBox(new Pair<>("0 – нет", 0), new Pair<>("1 – пастозность", 1), new Pair<>("2 – отеки", 2), new Pair<>("3 – анасарка", 3));
        cbSAD = createComboBox(new Pair<>("0 – более 120 мм рт. ст.", 0), new Pair<>("1 – 100-120 мм рт. ст.", 1), new Pair<>("2 – менее 100 мм рт. ст.", 2));

        // Добавляем все признаки с Label + ComboBox в одну строку (HBox)
        vbox.getChildren().addAll(
                createHBox("Одышка", cbOdyshka),
                createHBox("Изменился ли за последнюю неделю вес", cbVes),
                createHBox("Жалобы на перебои в работе сердца", cbPereboi),
                createHBox("В каком положении находится в постели", cbPolozhenie),
                createHBox("Набухшие шейные вены", cbSheinyeVeny),
                createHBox("Хрипы в легких", cbHripy),
                createHBox("Наличие ритма галопа", cbGalop),
                createHBox("Печень", cbPechen),
                createHBox("Отеки", cbOteki),
                createHBox("Уровень САД", cbSAD)
        );

        btnCalc = new Button("Рассчитать");
        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setPrefRowCount(3);

        vbox.getChildren().addAll(btnCalc, txtResult);

        btnCalc.setOnAction(e -> {
            try {
                model.setOdyshka(String.valueOf(cbOdyshka.getValue().getValue()));
                model.setVes(String.valueOf(cbVes.getValue().getValue()));
                model.setPereboi(String.valueOf(cbPereboi.getValue().getValue()));
                model.setPolozhenie(String.valueOf(cbPolozhenie.getValue().getValue()));
                model.setSheinyeVeny(String.valueOf(cbSheinyeVeny.getValue().getValue()));
                model.setHripy(String.valueOf(cbHripy.getValue().getValue()));
                model.setGalop(String.valueOf(cbGalop.getValue().getValue()));
                model.setPechen(String.valueOf(cbPechen.getValue().getValue()));
                model.setOteki(String.valueOf(cbOteki.getValue().getValue()));
                model.setSAD(String.valueOf(cbSAD.getValue().getValue()));

                model.calc();
                txtResult.setText(model.getResult());
            } catch (Exception ex) {
                txtResult.setText("Ошибка: " + ex.getMessage());
            }
        });

        // Добавляем VBox в ScrollPane для прокрутки
        ScrollPane scrollPane = new ScrollPane(vbox);
        scrollPane.setFitToWidth(true);

        getChildren().add(scrollPane);
    }

    private HBox createHBox(String labelText, ComboBox<Pair<String, Integer>> comboBox) {
        Label label = new Label(labelText + ":");
        label.setPrefWidth(220); // ширина для выравнивания
        HBox hbox = new HBox(10, label, comboBox);
        return hbox;
    }

    private ComboBox<Pair<String, Integer>> createComboBox(Pair<String, Integer>... items) {
        ComboBox<Pair<String, Integer>> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Pair<String, Integer> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getKey());
            }
        });
        comboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Pair<String, Integer> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : item.getKey());
            }
        });
        return comboBox;
    }
}