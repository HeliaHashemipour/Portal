import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor extends Person {

    private final List<Classroom> classrooms;

    public Professor() {
        classrooms = new ArrayList<>();
    }

    public boolean createClassroom(Classroom classroom) {
        if (classrooms.contains(classroom))
            return false;
        return classrooms.add(classroom);
    }

    public void removeClassroom(Classroom classroom) {
        classrooms.remove(classroom);
    }

    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < classrooms.size(); i++){
            list.addAll(classrooms.get(i).getStudents());
        }
        return list;
    }

    public void setGrade(Student student, Classroom classroom, int grade) {
        for (Unit unit : student.getUnits()) {
            if (unit.getClassroom().equals(classroom))
                unit.setGrade(grade);
        }
    }

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getFirstName(), getLastName());
    }
}
