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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CalcsApp extends Application {

    private final Map<String, Supplier<javafx.scene.Parent>> calculatorMap = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        calculatorMap.put("Pursuit", () -> new PursuitControl(PursuitModel.builder().build()));
        calculatorMap.put("FLI", () -> new FLIControl(FLIModel.builder().build()));
        calculatorMap.put("Cockroft", () -> new CockroftControl(CockroftModel.builder().build()));
        calculatorMap.put("CKD-EPI", () -> new CKDEPIControl(CKDEPIModel.builder().build()));
        calculatorMap.put("Доза ПОАК", () -> new POAKControl(POAKModel.builder().build()));
        calculatorMap.put("ХС-ЛНП", () -> new LDLControl(LDLModel.builder().build()));
        calculatorMap.put("Макс ЧСС по inbar", () -> new INBARControl(INBARModel.builder().build()));
        calculatorMap.put("FIB-4", () -> new FIB4Control(FIB4Model.builder().build()));
        calculatorMap.put("CDS", () -> new CDSControl(CDSModel.builder().build()));
        calculatorMap.put("Larsen CM, 2017", () -> new LarsenControl(LarsenModel.builder().build()));
        calculatorMap.put("Khorana", () -> new KhoranaControl(KhoranaModel.builder().build()));
        calculatorMap.put("REACH", () -> new REACHControl(REACHModel.builder().build()));
        calculatorMap.put("SAMSCI", () -> new SAMSCIControl(SAMSCIModel.builder().build()));
        calculatorMap.put("NoSAS", () -> new NoSASControl(NoSASModel.builder().build()));
        calculatorMap.put("Wells", () -> new WellsControl(WellsModel.builder().build()));
        calculatorMap.put("rGENEVA", () -> new rGENEVAControl(rGENEVAModel.builder().build()));
        calculatorMap.put("PESI", () -> new PESIControl(PESIModel.builder().build()));
        calculatorMap.put("PERC", () -> new PERCControl(PERCModel.builder().build()));
        calculatorMap.put("IMPROVE VTE", () -> new IMPROVETVEControl(IMPROVETVEModel.builder().build()));
        calculatorMap.put("DLCN", () -> new DLCNControl(DLCNModel.builder().build()));
        calculatorMap.put("Mifflin-St Jeor", () -> new MifflinStJeorControl(MifflinStJeorModel.builder().build()));
        calculatorMap.put("HSI", () -> new HSIControl(HSIModel.builder().build()));
        calculatorMap.put("GRACE", () -> new GRACEControl(GRACEModel.builder().build()));
        calculatorMap.put("Шкала ШОКС", () -> new SHOKSControl(SHOKSModel.builder().build()));
        calculatorMap.put("Алгоритм оценки острого повреждения почек", () -> new AKIControl(AKIModel.builder().build()));
        calculatorMap.put("Mehran-2", () -> new Mehran2Control(Mehran2Model.builder().build()));
        calculatorMap.put("RCRI", () -> new RCRIControl(RCRIModel.builder().build()));
        calculatorMap.put("GuptaMICA", () -> new GuptaMICAControl(GuptaMICAModel.builder().build()));
        calculatorMap.put("DASI", () -> new DASIControl(DASIModel.builder().build()));
        calculatorMap.put("Дифференц. диагностика железодефиц. анемии и анемии хронических заболеваний", () -> new IDAChronicAnemiaControl(IDAChronicAnemiaModel.builder().build()));


        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(calculatorMap.keySet());
        comboBox.setPromptText("Выбрать калькулятор ССЗ");

        Button openButton = new Button("Открыть");
        openButton.setOnAction(e -> {
            String selected = comboBox.getValue();
            if (selected != null && calculatorMap.containsKey(selected)) {
                openCalculator(selected, calculatorMap.get(selected).get());
            }
        });

        VBox root = new VBox(15, comboBox, openButton);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Выбор калькулятора");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openCalculator(String title, javafx.scene.Parent control) {
        Stage stage = new Stage();
        Scene scene = new Scene(control, 400, 300);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}