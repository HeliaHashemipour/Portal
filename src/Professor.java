import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the professor role of this application.
 * this means every instance of this class is a professor.
 * @Hashemipour
 * @since 2020
 */
public class Professor extends Person {

    private final List<Classroom> classrooms;

    /**
     * The constructor of this class tha initializes the classrooms list.
     */
    public Professor() {
        classrooms = new ArrayList<>();
    }

    /**
     * This method creates a classroom.
     * @param classroom
     * @return
     */
    public boolean createClassroom(Classroom classroom) {
        if (classrooms.contains(classroom))
            return false;
        return classrooms.add(classroom);
    }

    /**
     * This method removes a classroom.
     * @param classroom
     */
    public void removeClassroom(Classroom classroom) {
        classrooms.remove(classroom);
    }

    /**
     * This method gets all of this professors students.
     * @return
     */
    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < classrooms.size(); i++){
            list.addAll(classrooms.get(i).getStudents());
        }
        return list;
    }

    /**
     * This method sets grade of a specific student from a specific classroom.
     * @param student
     * @param classroom
     * @param grade
     */
    public void setGrade(Student student, Classroom classroom, double grade) {
        for (Unit unit : student.getUnits()) {
            if (unit.getClassroom().equals(classroom))
                unit.setGrade(grade);
        }
    }

    /**
     * The getter method of classrooms
     * @return
     */
    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
