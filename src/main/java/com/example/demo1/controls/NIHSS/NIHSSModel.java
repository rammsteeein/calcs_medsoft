package com.example.demo1.controls.NIHSS;

import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Map;

public class NIHSSModel {

    private final Map<String, StringProperty> fields = new HashMap<>();

    private final DoubleProperty resultValue = new SimpleDoubleProperty();
    private final StringProperty result = new SimpleStringProperty();

    public NIHSSModel() {
        create("LOC");
        create("LOC_QUEST");
        create("LOC_COMMAND");
        create("EYE");
        create("VISION");
        create("FACE");
        create("ARM_L");
        create("ARM_R");
        create("LEG_L");
        create("LEG_R");
        create("ATAXIA");
        create("SENS");
        create("SPEECH");
        create("DYSARTHRIA");
        create("NEGLECT");
    }

    private void create(String key) {
        fields.put(key, new SimpleStringProperty());
    }

    public StringProperty get(String key) {
        return fields.get(key);
    }

    public DoubleProperty resultValueProperty() { return resultValue; }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        Map<String, String> data = new HashMap<>();
        for (String key : fields.keySet()) {
            data.put(key, fields.get(key).get());
        }

        NIHSSResult res = NIHSSCalculator.calc(data);
        resultValue.set(res.getValue());
        result.set("Сумма: " + res.getValue() + "\n\n" + res.getText());
    }
}
