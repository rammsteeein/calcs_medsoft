package com.example.demo1.controls;

import com.example.demo1.controls.AKI.AKIControl;
import com.example.demo1.controls.AKI.AKIModel;
import com.example.demo1.controls.CDS.CDSControl;
import com.example.demo1.controls.CDS.CDSModel;
import com.example.demo1.controls.Cockroft.*;
import com.example.demo1.controls.DASI.DASIControl;
import com.example.demo1.controls.DASI.DASIModel;
import com.example.demo1.controls.DLCN.DLCNControl;
import com.example.demo1.controls.DLCN.DLCNModel;
import com.example.demo1.controls.FIB4.FIB4Control;
import com.example.demo1.controls.FIB4.FIB4Model;
import com.example.demo1.controls.GRACE.GRACEControl;
import com.example.demo1.controls.GRACE.GRACEModel;
import com.example.demo1.controls.GuptaMICA.GuptaMICAControl;
import com.example.demo1.controls.GuptaMICA.GuptaMICAModel;
import com.example.demo1.controls.HSI.HSIControl;
import com.example.demo1.controls.HSI.HSIModel;
import com.example.demo1.controls.IDAChronicAnemia.IDAChronicAnemiaControl;
import com.example.demo1.controls.IDAChronicAnemia.IDAChronicAnemiaModel;
import com.example.demo1.controls.IMPROVEVTE.IMPROVETVEControl;
import com.example.demo1.controls.IMPROVEVTE.IMPROVETVEModel;
import com.example.demo1.controls.Khorana.KhoranaControl;
import com.example.demo1.controls.Khorana.KhoranaModel;
import com.example.demo1.controls.Larsen.LarsenControl;
import com.example.demo1.controls.Larsen.LarsenModel;
import com.example.demo1.controls.Mehran2.Mehran2Control;
import com.example.demo1.controls.Mehran2.Mehran2Model;
import com.example.demo1.controls.MifflinStJeor.MifflinStJeorControl;
import com.example.demo1.controls.MifflinStJeor.MifflinStJeorModel;
import com.example.demo1.controls.NoSAS.NoSASControl;
import com.example.demo1.controls.NoSAS.NoSASModel;
import com.example.demo1.controls.PERC.PERCControl;
import com.example.demo1.controls.PERC.PERCModel;
import com.example.demo1.controls.PESI.PESIControl;
import com.example.demo1.controls.PESI.PESIModel;
import com.example.demo1.controls.Pursuit.*;
import com.example.demo1.controls.FLI.*;
import com.example.demo1.controls.POAK_doze.*;
import com.example.demo1.controls.CKDEPI.*;
import com.example.demo1.controls.LDL.*;
import com.example.demo1.controls.RCRI.RCRIControl;
import com.example.demo1.controls.RCRI.RCRIModel;
import com.example.demo1.controls.REACH.REACHControl;
import com.example.demo1.controls.REACH.REACHModel;
import com.example.demo1.controls.SAMSCI.SAMSCIControl;
import com.example.demo1.controls.SAMSCI.SAMSCIModel;
import com.example.demo1.controls.SHOKS.*;
import com.example.demo1.controls.Wells.WellsControl;
import com.example.demo1.controls.Wells.WellsModel;
import com.example.demo1.controls.inbar.*;
import com.example.demo1.controls.rGENEVA.rGENEVAControl;
import com.example.demo1.controls.rGENEVA.rGENEVAModel;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;
import java.util.function.Supplier;

public class CalcsApp extends Application {

    private final Map<String, Supplier<javafx.scene.Parent>> calculatorMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        calculatorMap.put("Pursuit", () -> new PursuitControl(new PursuitModel()));
        calculatorMap.put("FLI", () -> new FLIControl(new FLIModel()));
        calculatorMap.put("Cockroft", () -> new CockroftControl(new CockroftModel()));
        calculatorMap.put("CKD-EPI", () -> new CKDEPIControl(new CKDEPIModel()));
        calculatorMap.put("Доза ПОАК", () -> new POAKControl(new POAKModel()));
        calculatorMap.put("ХС-ЛНП", () -> new LDLControl(new LDLModel()));
        calculatorMap.put("Макс ЧСС по inbar", () -> new INBARControl(new INBARModel()));
        calculatorMap.put("FIB-4", () -> new FIB4Control(new FIB4Model()));
        calculatorMap.put("CDS", () -> new CDSControl(new CDSModel())); //
        calculatorMap.put("Larsen CM, 2017", () -> new LarsenControl(new LarsenModel()));
        calculatorMap.put("Шкала Хорана", () -> new KhoranaControl(new KhoranaModel()));
        calculatorMap.put("REACH", () -> new REACHControl(new REACHModel()));
        calculatorMap.put("SAMSCI", () -> new SAMSCIControl(new SAMSCIModel()));
        calculatorMap.put("NoSAS", () -> new NoSASControl(new NoSASModel()));
        calculatorMap.put("Wells", () -> new WellsControl(new WellsModel()));
        calculatorMap.put("rGENEVA", () -> new rGENEVAControl(new rGENEVAModel()));
        calculatorMap.put("PESI", () -> new PESIControl(new PESIModel()));
        calculatorMap.put("PERC", () -> new PERCControl(new PERCModel()));
        calculatorMap.put("IMPROVE VTE", () -> new IMPROVETVEControl(new IMPROVETVEModel()));
        calculatorMap.put("DLCN", () -> new DLCNControl(new DLCNModel()));
        calculatorMap.put("Mifflin-St Jeor", () -> new MifflinStJeorControl(new MifflinStJeorModel()));
        calculatorMap.put("HSI", () -> new HSIControl(new HSIModel()));
        calculatorMap.put("GRACE", () -> new GRACEControl(new GRACEModel()));
        calculatorMap.put("Шкала ШОКС", () -> new SHOKSControl(new SHOKSModel()));
        calculatorMap.put("Алгоритм оценки острого повреждения почек", () -> new AKIControl(new AKIModel()));
        calculatorMap.put("Mehran-2", () -> new Mehran2Control(new Mehran2Model()));
        calculatorMap.put("RCRI", () -> new RCRIControl(new RCRIModel()));
        calculatorMap.put("GuptaMICA", () -> new GuptaMICAControl(new GuptaMICAModel()));
        calculatorMap.put("DASI", () -> new DASIControl(new DASIModel()));
        calculatorMap.put("Дифференц. диагностика железодефиц. анемии и анемии хронических заболеваний",
                () -> new IDAChronicAnemiaControl(new IDAChronicAnemiaModel()));

        List<String> keys = new ArrayList<>(calculatorMap.keySet());
        Collections.sort(keys);

        ObservableList<String> items = FXCollections.observableArrayList(keys);

        ComboBox<String> comboBox = new ComboBox<>(items);
        comboBox.setEditable(true);
        comboBox.setPromptText("Выбрать калькулятор ССЗ");

        comboBox.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null || newVal.isBlank()) {
                comboBox.setItems(items);
                comboBox.hide();
            } else {
                String filter = newVal.toLowerCase();
                List<String> filtered = new ArrayList<>();
                for (String key : keys) {
                    if (key.toLowerCase().contains(filter)) {
                        filtered.add(key);
                    }
                }
                comboBox.setItems(FXCollections.observableArrayList(filtered));
                comboBox.show();
            }
        });

        comboBox.setOnAction(e -> {
            String selected = comboBox.getSelectionModel().getSelectedItem();
            if (selected != null) {
                comboBox.getEditor().setText(selected);
                comboBox.setItems(items);
                comboBox.hide();
            }
        });

        Button openButton = new Button("Открыть");
        openButton.setOnAction(e -> {
            String selected = comboBox.getValue();
            if (selected != null && calculatorMap.containsKey(selected)) {
                openCalculator(selected, calculatorMap.get(selected).get(), primaryStage);
            }
        });

        VBox root = new VBox(15, comboBox, openButton);
        root.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: white;");

        Scene scene = new Scene(root, 350, 300);
        primaryStage.setTitle("Выбор калькулятора");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openCalculator(String title, javafx.scene.Parent control, Stage owner) {
        Stage stage = new Stage();

        if (control instanceof javafx.scene.layout.Pane) {
            ((javafx.scene.layout.Pane) control).setStyle("-fx-background-color: white;");
        }

        double width, height;
        switch (title) {
            case "GRACE":
            case "REACH":
            case "Larsen CM, 2017":
            case "GuptaMICA":
            case "PESI":
                width = 800; height = 500; break;
            case "FLI":
            case "Mehran-2":
                width = 550; height = 500; break;
            case "Wells":
            case "CDS":
            case "FIB-4":
            case "rGENEVA":
                width = 700; height = 430; break;
            case "Дифференц. диагностика железодефиц. анемии и анемии хронических заболеваний":
                width = 700; height = 400; break;
            default:
                width = 550; height = 370; break;
        }

        Scene scene = new Scene(control, width, height, javafx.scene.paint.Color.WHITE);
        try {
            scene.getStylesheets().add("com/example/demo1/styles/style.css");
        } catch (Exception ignored) {}

        stage.setScene(scene);
        stage.setTitle(title);

        stage.initOwner(owner);
        stage.initModality(Modality.NONE);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
