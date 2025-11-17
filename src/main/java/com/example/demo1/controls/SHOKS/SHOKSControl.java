package com.example.demo1.controls.SHOKS;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorHeader;
import com.example.demo1.common.services.CalculatorDescription;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SHOKSControl extends StackPane implements CalculatorControl, AutoCloseable {

    private final SHOKSModel model;

    private Label lblSliderValue;
    private ImageView gradientImage;
    private Rectangle marker;
    private HBox ticksBox;

    private ChangeListener<Object> propertyListener;

    public SHOKSControl(SHOKSModel model) {
        this.model = model;
        initialize();
        bind();
        addListeners();
    }

    private void initialize() {
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

        gradientImage = new ImageView();
        gradientImage.setFitWidth(300);
        gradientImage.setFitHeight(20);
        try {
            gradientImage.setImage(
                    new Image(getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm())
            );
        } catch (Exception ignored) {}

        marker = new Rectangle(4, 20);
        marker.setFill(Color.BLACK);

        StackPane gradientPane = new StackPane(gradientImage, marker);

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
                                "Итоговая сумма (0–12) соответствует I–IV функциональному классу.\n\n" +
                                "*Интерпритацию баллов можно посмотреть, наведя курсор мыши на критерий"
                )
        ));
    }

    private void bind() {
        propertyListener = (obs, o, n) -> updateMarker();
    }

    private void addListeners() {
        model.odyshkaProperty().addListener(propertyListener);
        model.vesProperty().addListener(propertyListener);
        model.pereboiProperty().addListener(propertyListener);
        model.polozhenieProperty().addListener(propertyListener);
        model.sheinyeVenyProperty().addListener(propertyListener);
        model.hripyProperty().addListener(propertyListener);
        model.galopProperty().addListener(propertyListener);
        model.pechenProperty().addListener(propertyListener);
        model.otekiProperty().addListener(propertyListener);
        model.SADProperty().addListener(propertyListener);
        Platform.runLater(this::updateMarker);
    }

    private void removeListeners() {
        model.odyshkaProperty().removeListener(propertyListener);
        model.vesProperty().removeListener(propertyListener);
        model.pereboiProperty().removeListener(propertyListener);
        model.polozhenieProperty().removeListener(propertyListener);
        model.sheinyeVenyProperty().removeListener(propertyListener);
        model.hripyProperty().removeListener(propertyListener);
        model.galopProperty().removeListener(propertyListener);
        model.pechenProperty().removeListener(propertyListener);
        model.otekiProperty().removeListener(propertyListener);
        model.SADProperty().removeListener(propertyListener);
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

        Tooltip tooltip = new Tooltip(createTooltipText(label));
        tooltip.setShowDelay(Duration.millis(200));
        combo.setTooltip(tooltip);

        return new HBox(10, lbl, combo);
    }

    private String createTooltipText(String label) {
        switch (label.split(" ")[0]) {
            case "Одышка": return "0: нет\n1: при нагрузке\n2: в покое";
            case "Изменение": return "0: нет\n1: увеличился за последнюю неделю";
            case "Перебои": return "0: нет\n1: есть жалобы на перебои в работе сердца";
            case "Положение": return "0: горизонтально\n1: с приподнятым изголовьем\n2: просыпается от удушья\n3: вынужден сидеть";
            case "Шейные": return "0: нет\n1: набухание лежа\n2: набухание стоя";
            case "Хрипы": return "0: нет\n1: нижние отделы\n2: до лопаток\n3: над всей поверхностью легких";
            case "Галоп": return "0: нет\n1: есть (ритм галопа)";
            case "Печень": return "0: не увеличена\n1: увеличена до 5 см\n2: более 5 см";
            case "Отеки": return "0: нет\n1: пастозность\n2: отеки\n3: анасарка";
            case "САД": return "0: >120 мм рт. ст.\n1: 100–120 мм рт. ст.\n2: <100 мм рт. ст.";
            default: return "";
        }
    }

    @Override
    public void close() {
        removeListeners();
    }

    @Override
    public double getDefaultHeight() {
        return 480;
    }
}
