public enum ClassTime {
    FIRST,
    SECOND,
    THIRD,
    FORTH;

    @Override
    public String toString() {
        return switch (this) {
            case FIRST -> "8 - 10";
            case SECOND -> "10 - 12";
            case THIRD -> "2 - 4";
            case FORTH -> "4 - 6";
        };
    }
}


