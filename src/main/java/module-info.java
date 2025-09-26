module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.demo1.controls.FLI to javafx.graphics;
    exports com.example.demo1.controls.Cockroft to javafx.graphics;
    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1.controls.CKDEPI;
    opens com.example.demo1.controls.CKDEPI to javafx.fxml;
    exports com.example.demo1.common.enums;
    opens com.example.demo1.common.enums to javafx.fxml;
    opens com.example.demo1.controls to javafx.graphics;
    exports com.example.demo1.controls.POAK_doze to javafx.graphics;
}