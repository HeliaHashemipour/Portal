import java.io.Serial;
import java.io.Serializable;

/**
 * This is the unit of a student's
 * it contains the grade of the student and the classroom of this unit.
 */
public class Unit implements Serializable {

    @Serial
    private static final long serialVersionUID = -8744629711481128915L;

    private double grade;
    private Classroom classroom;

    /**
     * The getter method of grade.
     * @return
     */
    public double getGrade() {
        return grade;
    }

    /**
     * The setter method of the grade.
     * @param grade
     */
    public void setGrade(double grade) {
        System.out.println(grade);
        this.grade = grade;
    }

    /**
     * The getter method of the classroom.
     * @return
     */
    public Classroom getClassroom() {
        return classroom;
    }

    /**
     * The setter method of the classroom.
     * @param classroom
     */
    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
