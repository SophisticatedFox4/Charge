public enum State {
    POSITIVE("positive"), NEUTRAL("neutral"), NEGATIVE("negative");

    private final String label;

    State(String label) {
        this.label = label;
    }

    public String getCharge() {
        return label;
    }

    public State swap() {
        return switch(this) {
            case POSITIVE -> NEGATIVE;
            case NEGATIVE -> POSITIVE;
            case NEUTRAL -> NEUTRAL;
        };
    }
}
