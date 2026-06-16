package com.example.demo1.controls.CEAP;

public class CEAPResult {

    private final LegResult right;
    private final LegResult left;

    public CEAPResult(LegResult right, LegResult left) {
        this.right = right;
        this.left = left;
    }

    public LegResult getRight() {
        return right;
    }

    public LegResult getLeft() {
        return left;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("РЕЗУЛЬТАТ CEAP\n");
        sb.append("══════════════════════════════════════\n\n");
        appendLeg(sb, "Правая нога (Dxt)", right);
        sb.append('\n');
        appendLeg(sb, "Левая нога (Sin)", left);
        sb.append("──────────────────────────────────────\n");
        sb.append("Для копирования:\n");
        sb.append("Dxt: ").append(right.getFullCode()).append('\n');
        sb.append("Sin: ").append(left.getFullCode());
        return sb.toString();
    }

    private void appendLeg(StringBuilder sb, String title, LegResult leg) {
        sb.append(title).append('\n');
        sb.append("  C (клиника):        ").append(leg.getClinical()).append('\n');
        sb.append("  E (этиология):      ").append(leg.getEtiology()).append('\n');
        sb.append("  A (анатомия):       ").append(leg.getAnatomy()).append('\n');
        sb.append("  P (патофизиология): ").append(leg.getPathophysiology()).append('\n');
        sb.append("  L (диагностика):    ").append(leg.getLevel()).append('\n');
        sb.append("  Полный код:         ").append(leg.getFullCode()).append('\n');
    }

    public static class LegResult {

        private static final String EMPTY = "(Не заполнено!)";

        private final String clinical;
        private final String etiology;
        private final String anatomy;
        private final String pathophysiology;
        private final String level;
        private final String fullCode;

        public LegResult(String clinical, String etiology, String anatomy,
                         String pathophysiology, String level, String fullCode) {
            this.clinical = clinical;
            this.etiology = etiology;
            this.anatomy = anatomy;
            this.pathophysiology = pathophysiology;
            this.level = level;
            this.fullCode = fullCode;
        }

        public String getClinical() {
            return clinical;
        }

        public String getEtiology() {
            return etiology;
        }

        public String getAnatomy() {
            return anatomy;
        }

        public String getPathophysiology() {
            return pathophysiology;
        }

        public String getLevel() {
            return level;
        }

        public String getFullCode() {
            return fullCode;
        }

        public boolean isEmpty() {
            return EMPTY.equals(fullCode);
        }

        static LegResult empty() {
            return new LegResult(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY);
        }

        static String orEmpty(String value) {
            return value == null || value.isEmpty() ? EMPTY : value;
        }
    }
}
