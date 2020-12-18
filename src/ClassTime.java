import java.io.Serializable;

/**
 * This enumeration represents the class time of the classrooms.
 * @Hashemipour
 * @since 2020
 */
public enum ClassTime implements Serializable {

    FIRST,
    SECOND,
    THIRD,
    FORTH;

    /**
     * To string method of this class
     * @return
     */
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
