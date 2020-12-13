import java.util.List;
import java.util.Objects;

public class Classroom  {


    private String name;
    private int numberOfUnit;
    private int capacity;
    private ClassTime classTime;
    private ClassDay classDay;
    private List<Student> students;
    private Professor professor;
    private Unit unit;

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

    public Unit getUnit() { return unit; }

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
