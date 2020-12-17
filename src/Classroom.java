import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Classroom implements Serializable {


    @Serial
    private static final long serialVersionUID = -8603258451909863723L;

    private String name;
    private int numberOfUnit;
    private int capacity;
    private ClassTime classTime;
    private ClassDay classDay;
    private final List<Student> students;
    private Professor professor;

    public Classroom() {
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfUnit() {
        return numberOfUnit;
    }

    public void setNumberOfUnit(int numberOfUnit) {
        this.numberOfUnit = numberOfUnit;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ClassTime getClassTime() {
        return classTime;
    }

    public void setClassTime(ClassTime classTime) {
        this.classTime = classTime;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) { this.professor = professor; }

    public List<Student> getStudents(){ return students; }

    @Override
    public String toString() {
        return name;
    }

    public ClassDay getClassDay() {
        return classDay;
    }

    public void setClassDay(ClassDay classDay) {
        this.classDay = classDay;
    }

    public boolean addStudent(Student st) {
        if (students.contains(st))
            return false;
        return students.add(st);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Classroom)) return false;
        Classroom classroom = (Classroom) o;
        return Objects.equals(getName(), classroom.getName()) && getClassTime() == classroom.getClassTime() && getClassDay() == classroom.getClassDay() && Objects.equals(getProfessor(), classroom.getProfessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getClassTime(), getClassDay(), getProfessor());
    }
}
