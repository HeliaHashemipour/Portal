import java.io.Serial;
import java.io.Serializable;

public class Unit implements Serializable {

    @Serial
    private static final long serialVersionUID = -8744629711481128915L;

    private int grade;
    private Classroom classroom;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
