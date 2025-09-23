package com.example.demo1.controls.FIB4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FIB4Model {
    private final StringProperty age = new SimpleStringProperty();
    private final StringProperty ast = new SimpleStringProperty();
    private final StringProperty alt = new SimpleStringProperty();
    private final StringProperty platelets = new SimpleStringProperty();
    private final StringProperty result = new SimpleStringProperty();

    public String getAge() { return age.get(); }
    public void setAge(String value) { age.set(value); }
    public StringProperty ageProperty() { return age; }

    public String getAst() { return ast.get(); }
    public void setAst(String value) { ast.set(value); }
    public StringProperty astProperty() { return ast; }

    public String getAlt() { return alt.get(); }
    public void setAlt(String value) { alt.set(value); }
    public StringProperty altProperty() { return alt; }

    public String getPlatelets() { return platelets.get(); }
    public void setPlatelets(String value) { platelets.set(value); }
    public StringProperty plateletsProperty() { return platelets; }

    public String getResult() { return result.get(); }
    public void setResult(String value) { result.set(value); }
    public StringProperty resultProperty() { return result; }

    public void calc() {
        try {
            int ageValue = Integer.parseInt(getAge());
            double astValue = Double.parseDouble(getAst());
            double altValue = Double.parseDouble(getAlt());
            double plateletsValue = Double.parseDouble(getPlatelets());

            FIB4Result res = FIB4Calculator.calc(ageValue, astValue, altValue, plateletsValue);
            setResult(res.toString());
        } catch (Exception e) {
            setResult("Ошибка: " + e.getMessage());
        }
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String age = "";
        private String ast = "";
        private String alt = "";
        private String platelets = "";
        private String result = "";

        public Builder withAge(String age) { this.age = age; return this; }
        public Builder withAst(String ast) { this.ast = ast; return this; }
        public Builder withAlt(String alt) { this.alt = alt; return this; }
        public Builder withPlatelets(String platelets) { this.platelets = platelets; return this; }
        public Builder withResult(String result) { this.result = result; return this; }

        public FIB4Model build() {
            FIB4Model m = new FIB4Model();
            m.setAge(age);
            m.setAst(ast);
            m.setAlt(alt);
            m.setPlatelets(platelets);
            m.setResult(result);
            return m;
        }
    }
}