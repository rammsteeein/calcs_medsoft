package com.example.demo1.controls.Larsen;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class LarsenControl extends StackPane implements AutoCloseable, CalculatorControl {
    private final LarsenModel model;

    private ComboBox<String> cmbDrug;
    private CheckBox cbCardiomegalia, cbIBS, cbAG, cbSD, cbAnthraHistory, cbRadiation, cbAge, cbFemale;

    private Label lblResult;

    private ImageView gradientImage;
    private Rectangle marker;
    private HBox ticksBox;

    private final double MAX_SCORE = 10.0;

    public LarsenControl(LarsenModel model) {
        this.model = model;
        initialize();
        bind();
    }

    private void initialize() {
        cmbDrug = new ComboBox<>();
        cmbDrug.getItems().addAll(
                "Антрациклины", "Циклофосфан", "Ифосфамид", "Клофарабин", "Герцептин",
                "Доцетаксел", "Пертузумаб", "Сунитиниб", "Сорафениб",
                "Бевацизумаб", "Дазатиниб", "Иматиниб", "Лапатиниб",
                "Этопозид", "Ритуксимаб", "Талидомид"
        );
        cmbDrug.setPromptText("Выберите препарат");

        cbCardiomegalia = new CheckBox("Кардиомегалия или ХСН");
        cbIBS = new CheckBox("ИБС/эквивалент (ЗПА)");
        cbAG = new CheckBox("АГ");
        cbSD = new CheckBox("СД");
        cbAnthraHistory = new CheckBox("Лечение антрациклинами в анамнезе");
        cbRadiation = new CheckBox("Лучевая терапия грудной клетки");
        cbAge = new CheckBox("Возраст <15 или >65 лет");
        cbFemale = new CheckBox("Женский пол");

        lblResult = new Label("Риск: 0 (Отсутствие риска)");

        gradientImage = new ImageView();
        gradientImage.setFitWidth(300);
        gradientImage.setFitHeight(20);
        gradientImage.setImage(
                new Image(getClass().getResource("/com/example/demo1/img/smooth-rgb-gradients.png").toExternalForm())
        );

        marker = new Rectangle(4, 20);
        marker.setFill(Color.BLACK);

        StackPane gradientPane = new StackPane();
        gradientPane.getChildren().addAll(gradientImage, marker);

        ticksBox = new HBox();
        String[] tickLabels = {"0", "2", "4", "6", "8", "10"};
        for (String t : tickLabels) {
            Label lbl = new Label(t);
            lbl.setPrefWidth(300.0 / (tickLabels.length - 1));
            lbl.setStyle("-fx-text-fill: black; -fx-alignment: center;");
            ticksBox.getChildren().add(lbl);
        }

        VBox leftBox = new VBox(10,
                CalculatorHeader.createHeader("Оценка кардиотоксичности (Larsen CM, 2017)"),
                cmbDrug, cbCardiomegalia, cbIBS, cbAG, cbSD,
                cbAnthraHistory, cbRadiation, cbAge, cbFemale,
                gradientPane, ticksBox, lblResult
        );

        getChildren().add(new HBox(20,
                leftBox,
                CalculatorDescription.createDescription(
                        "Методика Larsen CM (2017) применяется для оценки риска кардиотоксичности " +
                                "перед началом противоопухолевой терапии.\n\n" +
                                "Сумма баллов = препарат + факторы пациента.\n" +
                                "Интерпретация:\n" +
                                "0: Отсутствие риска\n" +
                                "1–2: Низкий риск\n" +
                                "3–5: Промежуточный риск\n" +
                                "≥6: Высокий риск"
                )
        ));
    }

    private void bind() {
        ChangeListener<Object> listener = (obs, oldVal, newVal) -> calculateAndUpdate();

        cmbDrug.valueProperty().bindBidirectional(model.drugProperty());
        cmbDrug.valueProperty().addListener(listener);

        cbCardiomegalia.selectedProperty().addListener(listener);
        cbIBS.selectedProperty().addListener(listener);
        cbAG.selectedProperty().addListener(listener);
        cbSD.selectedProperty().addListener(listener);
        cbAnthraHistory.selectedProperty().addListener(listener);
        cbRadiation.selectedProperty().addListener(listener);
        cbAge.selectedProperty().addListener(listener);
        cbFemale.selectedProperty().addListener(listener);

        calculateAndUpdate();
    }

    private void calculateAndUpdate() {
        model.getPatientFactors().clear();
        if (cbCardiomegalia.isSelected()) model.getPatientFactors().add(cbCardiomegalia.getText());
        if (cbIBS.isSelected()) model.getPatientFactors().add(cbIBS.getText());
        if (cbAG.isSelected()) model.getPatientFactors().add(cbAG.getText());
        if (cbSD.isSelected()) model.getPatientFactors().add(cbSD.getText());
        if (cbAnthraHistory.isSelected()) model.getPatientFactors().add(cbAnthraHistory.getText());
        if (cbRadiation.isSelected()) model.getPatientFactors().add(cbRadiation.getText());
        if (cbAge.isSelected()) model.getPatientFactors().add(cbAge.getText());
        if (cbFemale.isSelected()) model.getPatientFactors().add(cbFemale.getText());

        model.calc();
        updateMarker(model.totalScoreProperty().get(), model.interpretationProperty().get());
    }

    private void updateMarker(int score, String interpretation) {
        double lineWidth = gradientImage.getFitWidth();
        double targetX = (score / MAX_SCORE) * lineWidth - lineWidth / 2;

        TranslateTransition tt = new TranslateTransition(Duration.millis(300), marker);
        tt.setToX(targetX);
        tt.play();

        lblResult.setText("Риск: " + score + " (" + interpretation + ")");
    }

    @Override
    public void close() {
        cmbDrug.valueProperty().unbindBidirectional(model.drugProperty());
    }

    @Override
    public double getDefaultWidth() {
        return 800;
    }

    @Override
    public double getDefaultHeight() {
        return 500;
    }
}
