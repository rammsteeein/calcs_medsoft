package com.example.demo1.controls.SHOKS;

import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SHOKSControl extends StackPane {

    private final SHOKSModel model;

    private Label lblSliderValue;
    private ImageView gradientImage;
    private Rectangle marker;
    private HBox ticksBox;

    private final double MAX_SCORE = 12.0; // визуальный максимум (для позиции 0..IV используем деления)

    public SHOKSControl(SHOKSModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        // Слева — набор ComboBox'ов
        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("SHOKS"),
                createLabeledCombo("Одышка (0–2)", 2, model.odyshkaProperty()),
                createLabeledCombo("Изменение веса (0–1)", 1, model.vesProperty()),
                createLabeledCombo("Перебои (0–1)", 1, model.pereboiProperty()),
                createLabeledCombo("Положение (0–3)", 3, model.polozhenieProperty()),
                createLabeledCombo("Шейные вены (0–2)", 2, model.sheinyeVenyProperty()),
                createLabeledCombo("Хрипы (0–3)", 3, model.hripyProperty()),
                createLabeledCombo("Галоп (0–1)", 1, model.galopProperty()),
                createLabeledCombo("Печень (0–2)", 2, model.pechenProperty()),
                createLabeledCombo("Отеки (0–3)", 3, model.otekiProperty()),
                createLabeledCombo("САД (0–2)", 2, model.SADProperty())
        );

        lblSliderValue = new Label("ФК: —");

        // Градиентная полоса
        gradientImage = new ImageView();
        gradientImage.setFitWidth(300);
        gradientImage.setFitHeight(20);
        try {
            gradientImage.setImage(
                    new Image(getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm())
            );
        } catch (Exception e) {
            // если картинка не найдена — игнорируем, полоса останется пустой
        }

        marker = new Rectangle(4, 20);
        marker.setFill(Color.BLACK);

        StackPane gradientPane = new StackPane(gradientImage, marker);

        // Подписи под градиентом
        ticksBox = new HBox();
        ticksBox.setSpacing(0);
        String[] tickLabels = {"0", "I ФК", "II ФК", "III ФК", "IV ФК"};
        for (String label : tickLabels) {
            Label lbl = new Label(label);
            lbl.setPrefWidth(300.0 / (tickLabels.length - 1));
            lbl.setStyle("-fx-text-fill: black; -fx-alignment: center;");
            ticksBox.getChildren().add(lbl);
        }

        leftBox.getChildren().addAll(gradientPane, ticksBox, lblSliderValue);

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Шкала SHOKS используется для оценки функционального класса ХСН.\n\n" +
                                "Баллы по критериям:\n" +
                                "- Одышка: 0–2\n" +
                                "- Изменение веса: 0–1\n" +
                                "- Перебои: 0–1\n" +
                                "- Положение: 0–3\n" +
                                "- Шейные вены: 0–2\n" +
                                "- Хрипы: 0–3\n" +
                                "- Галоп: 0–1\n" +
                                "- Печень: 0–2\n" +
                                "- Отеки: 0–3\n" +
                                "- САД: 0–2\n\n" +
                                "Итоговая сумма (0–12) соответствует I–IV функциональному классу."
                )
        ));
    }

    private void bind() {
        // слушатели на все свойства модели
        ChangeListener<Object> listener = (obs, o, n) -> updateMarker();

        model.odyshkaProperty().addListener(listener);
        model.vesProperty().addListener(listener);
        model.pereboiProperty().addListener(listener);
        model.polozhenieProperty().addListener(listener);
        model.sheinyeVenyProperty().addListener(listener);
        model.hripyProperty().addListener(listener);
        model.galopProperty().addListener(listener);
        model.pechenProperty().addListener(listener);
        model.otekiProperty().addListener(listener);
        model.SADProperty().addListener(listener);
        Platform.runLater(this::updateMarker);
    }

    private void updateMarker() {
        model.calc();
        int score = model.getTotalScore();

        int classIndex;
        String fcText;
        if (score == 0) { classIndex = 0; fcText = "нет признаков СН"; }
        else if (score <= 3) { classIndex = 1; fcText = "I"; }
        else if (score <= 6) { classIndex = 2; fcText = "II"; }
        else if (score <= 9) { classIndex = 3; fcText = "III"; }
        else { classIndex = 4; fcText = "IV"; }
        Label targetLabel = (Label) ticksBox.getChildren().get(classIndex);

        double labelCenterX = targetLabel.getLayoutX() + targetLabel.getWidth() / 2;

        double targetX = labelCenterX - gradientImage.getFitWidth() / 2;

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), marker);
        tt.setToX(targetX);
        tt.play();

        lblSliderValue.setText(String.format("ФК: %s (%d баллов)", fcText, score));
    }

    private HBox createLabeledCombo(String label, int maxValue, javafx.beans.property.IntegerProperty property) {
        Label lbl = new Label(label);
        ComboBox<Integer> combo = new ComboBox<>();
        for (int i = 0; i <= maxValue; i++) combo.getItems().add(i);

        combo.valueProperty().bindBidirectional(property.asObject());
        if (property.get() == 0) combo.setValue(0);

        combo.setPrefWidth(80);
        return new HBox(10, lbl, combo);
    }
}
