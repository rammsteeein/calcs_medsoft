package com.example.demo1.controls.Mehran2;

import javafx.beans.property.*;

public class Mehran2Model {

    private final ObjectProperty<String> oksType = new SimpleObjectProperty<>("STEMI");
    private final ObjectProperty<String> diabetesType = new SimpleObjectProperty<>("Инсулинозависимый");
    private final BooleanProperty lvefLow = new SimpleBooleanProperty(false);
    private final BooleanProperty anemia = new SimpleBooleanProperty(false);
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final IntegerProperty contrastVolume = new SimpleIntegerProperty();
    private final BooleanProperty bleeding = new SimpleBooleanProperty();
    private final StringProperty result = new SimpleStringProperty();

    public String getOksType() { return oksType.get(); }
    public void setOksType(String val) { oksType.set(val); }
    public ObjectProperty<String> oksTypeProperty() { return oksType; }

    public String getDiabetesType() { return diabetesType.get(); }
    public void setDiabetesType(String val) { diabetesType.set(val); }
    public ObjectProperty<String> diabetesTypeProperty() { return diabetesType; }

    public boolean isLvefLow() { return lvefLow.get(); }
    public void setLvefLow(boolean val) { lvefLow.set(val); }
    public BooleanProperty lvefLowProperty() { return lvefLow; }

    public boolean isAnemia() { return anemia.get(); }
    public void setAnemia(boolean val) { anemia.set(val); }
    public BooleanProperty anemiaProperty() { return anemia; }

    public int getAge() { return age.get(); }
    public void setAge(int val) { age.set(val); }
    public IntegerProperty ageProperty() { return age; }

    public int getContrastVolume() { return contrastVolume.get(); }
    public void setContrastVolume(int val) { contrastVolume.set(val); }
    public IntegerProperty contrastVolumeProperty() { return contrastVolume; }

    public boolean isBleeding() { return bleeding.get(); }
    public void setBleeding(boolean val) { bleeding.set(val); }
    public BooleanProperty bleedingProperty() { return bleeding; }

    public String getResult() { return result.get(); }
    public void setResult(String val) { result.set(val); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        Mehran2Result res = Mehran2Calculator.calc(
                getOksType(),
                getDiabetesType(),
                isLvefLow(),
                isAnemia(),
                getAge(),
                getContrastVolume(),
                isBleeding()
        );

        setResult(res.toString());

    }
}
