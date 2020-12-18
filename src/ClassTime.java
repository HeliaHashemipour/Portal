import java.io.Serializable;

public enum ClassTime implements Serializable {

    FIRST,
    SECOND,
    THIRD,
    FORTH;

    @Override
    public String toString() {
        return switch (this) {
            case FIRST -> "8 - 10";
            case SECOND -> "10 - 12";
            case THIRD -> "14 - 16";
            case FORTH -> "16 - 18";
        };
    }
}
