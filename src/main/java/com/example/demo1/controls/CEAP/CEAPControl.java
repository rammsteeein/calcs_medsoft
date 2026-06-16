package com.example.demo1.controls.CEAP;

import com.example.demo1.common.interfaces.CalculatorControl;
import com.example.demo1.common.services.CalculatorDescription;
import com.example.demo1.common.services.CalculatorHeader;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.*;

public class CEAPControl extends StackPane implements AutoCloseable, CalculatorControl {

    private final CEAPModel model;
    private final List<ChangeListener<?>> listeners = new ArrayList<>();

    private TextArea txtResult;

    private LegPanel rightPanel;
    private LegPanel leftPanel;

    public CEAPControl(CEAPModel model) {
        this.model = model;
        initialize();
        bind();
        model.calc();
    }

    private void initialize() {
        Runnable onChange = () -> model.calc();

        rightPanel = new LegPanel(model.getRightLeg(), onChange);
        leftPanel = new LegPanel(model.getLeftLeg(), onChange);

        Tab tabRight = new Tab("Правая нога", rightPanel.getContent());
        tabRight.setClosable(false);
        Tab tabLeft = new Tab("Левая нога", leftPanel.getContent());
        tabLeft.setClosable(false);

        TabPane legTabs = new TabPane(tabRight, tabLeft);
        legTabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        txtResult = new TextArea();
        txtResult.setEditable(false);
        txtResult.setWrapText(true);
        txtResult.setPrefRowCount(14);
        txtResult.setStyle("-fx-font-family: 'Consolas', 'Courier New', monospace; -fx-font-size: 12px;");

        Label lblResult = new Label("Результат");
        lblResult.setStyle("-fx-font-weight: bold;");

        BorderPane left = new BorderPane();

        left.setTop(
                new VBox(
                        10,
                        CalculatorHeader.createHeader("CEAP"),
                        legTabs
                )
        );

        left.setBottom(
                new VBox(
                        10,
                        lblResult,
                        txtResult
                )
        );

        ScrollPane inputScroll = new ScrollPane(left);
        inputScroll.setFitToWidth(true);

        Label description = CalculatorDescription.createDescription(buildDescription());
        description.setWrapText(true);
        description.setPrefWidth(380);

        getChildren().add(new HBox(
                20,
                inputScroll,
                description
        ));
    }

    private static String buildDescription() {
        return "Классификация CEAP — это международный стандарт для описания хронических заболеваний вен (ХЗВ)." +
                " Аббревиатура расшифровывается как Clinical (клинические проявления), Etiological (этиология)," +
                " Anatomical (анатомическая локализация) и Pathophysiological (патофизиология). Она позволяет комплексно" +
                " охарактеризовать состояние пациента, что важно как для повседневной практики," +
                " так и для научных исследований. ";
    }

    private void bind() {
        txtResult.textProperty().bind(model.resultProperty());
    }

    @Override
    public void close() {
        txtResult.textProperty().unbind();
        rightPanel.removeListeners();
        leftPanel.removeListeners();
        listeners.clear();
    }

    @Override
    public double getDefaultWidth() {
        return 700;
    }

    @Override
    public double getDefaultHeight() {
        return 700;
    }

    private static class LegPanel {

        private final CEAPModel.LegData data;
        private final Runnable onChange;
        private final List<ChangeListener<?>> listeners = new ArrayList<>();

        private final Map<String, CheckBox> clinicalBoxes = new LinkedHashMap<>();
        private final CheckBox cbC4a;
        private final CheckBox cbC4b;
        private final CheckBox cbC4c;

        private final ToggleGroup symptomsGroup = new ToggleGroup();
        private final ToggleGroup etiologyGroup = new ToggleGroup();
        private final ToggleGroup pathophysiologyGroup = new ToggleGroup();
        private final ToggleGroup levelGroup = new ToggleGroup();

        private final Map<String, CheckBox> superficialBoxes = new LinkedHashMap<>();
        private final Map<String, CheckBox> perforatingBoxes = new LinkedHashMap<>();
        private final Map<String, CheckBox> deepBoxes = new LinkedHashMap<>();

        private final VBox veinsBox = new VBox(8);

        LegPanel(CEAPModel.LegData data, Runnable onChange) {
            this.data = data;
            this.onChange = onChange;

            cbC4a = createClinicalSubBox("4a", "C4a: Пигментации или экзема");
            cbC4b = createClinicalSubBox("4b", "C4b: Липодерматосклероз или белая атрофия");
            cbC4c = createClinicalSubBox("4c", "C4c: Флебэктатическая корона");
        }

        VBox getContent() {
            VBox clinicalBox = buildClinicalSection();
            VBox etiologyBox = buildEtiologySection();
            VBox pathophysiologyBox = buildPathophysiologySection();
            VBox levelBox = buildLevelSection();

            Tab tabC = new Tab("C", wrapScroll(clinicalBox));
            Tab tabE = new Tab("E", wrapScroll(etiologyBox));
            Tab tabP = new Tab("P", wrapScroll(pathophysiologyBox));
            Tab tabL = new Tab("L", wrapScroll(levelBox));
            tabC.setClosable(false);
            tabE.setClosable(false);
            tabP.setClosable(false);
            tabL.setClosable(false);

            TabPane sectionTabs = new TabPane(tabC, tabE, tabP, tabL);
            sectionTabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

            VBox content = new VBox(8, sectionTabs);
            content.setPadding(new Insets(4));
            return content;
        }

        void removeListeners() {
            for (ChangeListener<?> listener : listeners) {
            }
            listeners.clear();
        }

        private ScrollPane wrapScroll(VBox box) {
            ScrollPane scroll = new ScrollPane(box);
            scroll.setFitToWidth(true);
            return scroll;
        }

        private VBox buildClinicalSection() {
            VBox box = new VBox(6);

            box.getChildren().add(sectionTitle("C — Клинические проявления"));

            addClinical("0", "C0: Нет видимых и пальпируемых признаков патологии вен", box);
            addClinical("1", "C1: Телеангиоэктазии и/или ретикулярные вены", box);
            addClinical("2", "C2: Варикозные вены", box);
            addClinical("2r", "C2r: Рецидив варикозных вен", box);
            addClinical("3", "C3: Отек", box);
            addClinical("4", "C4: Изменения кожи и подкожных тканей в следствие ХЗВ", box);

            VBox c4sub = new VBox(4, cbC4a, cbC4b, cbC4c);
            c4sub.setPadding(new Insets(0, 0, 0, 20));
            box.getChildren().add(c4sub);

            addClinical("5", "C5: Зажившая трофическая язва", box);
            addClinical("6", "C6: Открытая трофическая язва", box);
            addClinical("6r", "C6r: Рецидив открытой трофической язвы", box);

            box.getChildren().add(sectionTitle("Субъективные симптомы"));

            RadioButton rbA = createRadio(symptomsGroup, "A", "A: Нет субъективных симптомов");
            RadioButton rbS = createRadio(symptomsGroup, "S", "S: Есть субъективные симптомы");
            box.getChildren().addAll(rbA, rbS);

            addListener(symptomsGroup.selectedToggleProperty(), (o, ov, nv) -> {
                if (nv == null) {
                    data.setSymptoms(null);
                } else {
                    data.setSymptoms((String) symptomsGroup.getSelectedToggle().getUserData());
                }
                onChange.run();
            });

            return box;
        }

        private VBox buildEtiologySection() {
            VBox box = new VBox(6, sectionTitle("E — Этиология"));

            addEtiology(box, "En", "En: Не индетифицировано венозных причин");
            addEtiology(box, "Ep", "Ep: Первичное заболевание");
            addEtiology(box, "Ec", "Ec: Врожденное");
            addEtiology(box, "Es", "Es: Вторичное заболевание");
            addEtiology(box, "Esi", "Esi: Вторичное интравенозное");
            addEtiology(box, "Ese", "Ese: Вторичное экстравенозное");

            addListener(etiologyGroup.selectedToggleProperty(), (o, ov, nv) -> {
                if (nv == null) {
                    data.setEtiology(null);
                } else {
                    data.setEtiology((String) etiologyGroup.getSelectedToggle().getUserData());
                }
                onChange.run();
            });

            return box;
        }

        private VBox buildPathophysiologySection() {
            VBox box = new VBox(8, sectionTitle("P — Патофизиология"));

            RadioButton rbPn = createRadio(pathophysiologyGroup, "Pn", "Pn: Не обнаружено венозной патологии");
            RadioButton rbUnknown = createRadio(pathophysiologyGroup, "(?)", "(?): Требуется дообследование");
            RadioButton rbPr = createRadio(pathophysiologyGroup, "Pr", "Pr — рефлюкс");
            RadioButton rbPo = createRadio(pathophysiologyGroup, "Po", "Po — обструкция");
            RadioButton rbPro = createRadio(pathophysiologyGroup, "Pr,o", "Pr,o — рефлюкс и обструкция");

            box.getChildren().addAll(rbPn, rbUnknown, rbPr, rbPo, rbPro);

            addListener(pathophysiologyGroup.selectedToggleProperty(), (o, ov, nv) -> {
                if (nv == null) {
                    data.setPathophysiology(null);
                } else {
                    data.setPathophysiology((String) pathophysiologyGroup.getSelectedToggle().getUserData());
                }
                updateVeinsEnabled();
                onChange.run();
            });

            veinsBox.getChildren().add(sectionTitle("Поверхностные вены — s"));
            addVein(superficialBoxes, "Tel", "Tel: Телеангиоэктазии", veinsBox, data.getSuperficialVeins());
            addVein(superficialBoxes, "Ret", "Ret: Ретикулярные вены", veinsBox, data.getSuperficialVeins());
            addVein(superficialBoxes, "GSVa", "GSVa: БПВ выше колена", veinsBox, data.getSuperficialVeins());
            addVein(superficialBoxes, "GSVb", "GSVb: БПВ ниже колена", veinsBox, data.getSuperficialVeins());
            addVein(superficialBoxes, "SSV", "SSV: МПВ", veinsBox, data.getSuperficialVeins());
            addVein(superficialBoxes, "AASV", "AASV: ПДПВ", veinsBox, data.getSuperficialVeins());
            addVein(superficialBoxes, "NSV", "NSV: Несафенные вены", veinsBox, data.getSuperficialVeins());

            veinsBox.getChildren().add(sectionTitle("Перфоратные вены — p"));
            addVein(perforatingBoxes, "TPV", "TPV: Перфоранты бедра", veinsBox, data.getPerforatingVeins());
            addVein(perforatingBoxes, "CPV", "CPV: Перфоранты голени", veinsBox, data.getPerforatingVeins());

            veinsBox.getChildren().add(sectionTitle("Глубокие вены — d"));
            addVein(deepBoxes, "IVC", "IVC: Нижняя полая вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "CIV", "CIV: Общая подвздошная вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "IIV", "IIV: Внутренняя подвздошная вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "EIV", "EIV: Наружная подвздошная вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "PELV", "PELV: Тазовые вены", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "CFV", "CFV: Общая бедренная вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "DFV", "DFV: Глубокая вена бедра", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "FV", "FV: Бедренная вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "POPV", "POPV: Подколенная вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "TIBV", "TIBV: Берцовые вены", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "PRV", "PRV: Малоберцовая вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "ATV", "ATV: Передняя большеберцовая вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "PTV", "PTV: Задняя большеберцовая вена", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "MUSV", "MUSV: Мышечные (суральные) вены", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "GAV", "GAV: Вены икроножной мышцы", veinsBox, data.getDeepVeins());
            addVein(deepBoxes, "SOV", "SOV: Вены камбаловидной мышцы", veinsBox, data.getDeepVeins());

            box.getChildren().add(veinsBox);
            updateVeinsEnabled();

            return box;
        }

        private VBox buildLevelSection() {
            VBox box = new VBox(6, sectionTitle("L — Уровень диагностики"));

            addLevel(box, "LI", "LI: Клинически ± доплерография");
            addLevel(box, "LII", "LII: Ретикулярные вены");
            addLevel(box, "LIII", "LIII: БПВ выше колена");

            addListener(levelGroup.selectedToggleProperty(), (o, ov, nv) -> {
                if (nv == null) {
                    data.setDiagnosticLevel(null);
                } else {
                    data.setDiagnosticLevel((String) levelGroup.getSelectedToggle().getUserData());
                }
                onChange.run();
            });

            return box;
        }

        private void addClinical(String code, String label, VBox parent) {
            CheckBox cb = new CheckBox(label);
            cb.setWrapText(true);
            cb.setMaxWidth(Double.MAX_VALUE);
            clinicalBoxes.put(code, cb);
            parent.getChildren().add(cb);

            addListener(cb.selectedProperty(), (o, ov, nv) -> {
                if (nv) {
                    if ("0".equals(code)) {
                        clearClinicalExcept("0");
                    } else {
                        clinicalBoxes.get("0").setSelected(false);
                        data.getClinical().remove("0");
                    }
                    data.getClinical().add(code);
                } else {
                    data.getClinical().remove(code);
                }
                onChange.run();
            });
        }

        private CheckBox createClinicalSubBox(String code, String label) {
            CheckBox cb = new CheckBox(label);
            cb.setWrapText(true);
            cb.setMaxWidth(Double.MAX_VALUE);
            clinicalBoxes.put(code, cb);

            addListener(cb.selectedProperty(), (o, ov, nv) -> {
                if (nv) {
                    clinicalBoxes.get("0").setSelected(false);
                    data.getClinical().remove("0");
                    data.getClinical().add(code);
                } else {
                    data.getClinical().remove(code);
                }
                onChange.run();
            });
            return cb;
        }

        private void clearClinicalExcept(String keep) {
            for (Map.Entry<String, CheckBox> entry : clinicalBoxes.entrySet()) {
                if (!keep.equals(entry.getKey())) {
                    entry.getValue().setSelected(false);
                    data.getClinical().remove(entry.getKey());
                }
            }
        }

        private void addEtiology(VBox parent, String code, String label) {
            RadioButton rb = createRadio(etiologyGroup, code, label);
            rb.setWrapText(true);
            rb.setMaxWidth(Double.MAX_VALUE);
            parent.getChildren().add(rb);
        }

        private void addLevel(VBox parent, String code, String label) {
            RadioButton rb = createRadio(levelGroup, code, label);
            rb.setWrapText(true);
            rb.setMaxWidth(Double.MAX_VALUE);
            parent.getChildren().add(rb);
        }

        private void addVein(Map<String, CheckBox> map, String code, String label, VBox parent, Set<String> target) {
            CheckBox cb = new CheckBox(label);
            cb.setWrapText(true);
            cb.setMaxWidth(Double.MAX_VALUE);
            map.put(code, cb);
            parent.getChildren().add(cb);

            addListener(cb.selectedProperty(), (o, ov, nv) -> {
                if (nv) {
                    target.add(code);
                } else {
                    target.remove(code);
                }
                onChange.run();
            });
        }

        private void updateVeinsEnabled() {
            String type = data.getPathophysiology();
            boolean enabled = "Pr".equals(type) || "Po".equals(type) || "Pr,o".equals(type);

            setVeinsEnabled(superficialBoxes, enabled);
            setVeinsEnabled(perforatingBoxes, enabled);
            setVeinsEnabled(deepBoxes, enabled);

            if (!enabled) {
                clearVeins(superficialBoxes, data.getSuperficialVeins());
                clearVeins(perforatingBoxes, data.getPerforatingVeins());
                clearVeins(deepBoxes, data.getDeepVeins());
            }
        }

        private void setVeinsEnabled(Map<String, CheckBox> map, boolean enabled) {
            for (CheckBox cb : map.values()) {
                cb.setDisable(!enabled);
            }
        }

        private void clearVeins(Map<String, CheckBox> map, Set<String> target) {
            for (CheckBox cb : map.values()) {
                cb.setSelected(false);
            }
            target.clear();
        }

        private RadioButton createRadio(ToggleGroup group, String code, String label) {
            RadioButton rb = new RadioButton(label);
            rb.setToggleGroup(group);
            rb.setUserData(code);
            return rb;
        }

        private Label sectionTitle(String text) {
            Label label = new Label(text);
            label.setStyle("-fx-font-weight: bold;");
            return label;
        }

        private <T> void addListener(javafx.beans.value.ObservableValue<T> observable,
                                     ChangeListener<T> listener) {
            observable.addListener(listener);
            listeners.add(listener);
        }
    }

}
