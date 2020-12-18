import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class represents Classroom of the university.
 * this means every instance of this class is a class room that students can join and learn
 * and professors can create classrooms and delete 'em from database.
 */
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

    /**
     * The default constructor of this class that just initializes the students list.
     */
    public Classroom() {
        students = new ArrayList<>();
    }

    /**
     * The getter method of name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * The setter method of name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The getter method of number of units.
     * @return
     */
    public int getNumberOfUnit() {
        return numberOfUnit;
    }

    /**
     * The setter method of number of units.
     * @param numberOfUnit
     */
    public void setNumberOfUnit(int numberOfUnit) {
        this.numberOfUnit = numberOfUnit;
    }

    /**
     * The getter method of capacity.
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * The setter method of capacity.
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * The getter method of class time.
     * @return
     */
    public ClassTime getClassTime() {
        return classTime;
    }

    /**
     * The setter method of class time.
     * @param classTime
     */
    public void setClassTime(ClassTime classTime) {
        this.classTime = classTime;
    }

    /**
     * The getter method of professor
     * @return
     */
    public Professor getProfessor() {
        return professor;
    }

    /**
     * Teh setter method of professor.
     * @param professor
     */
    public void setProfessor(Professor professor) { this.professor = professor; }

    /**
     * The getter method of students.
     * @return
     */
    public List<Student> getStudents(){ return students; }

    /**
     * The toString method that returns name of the class room.
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * The getter method of class day.
     * @return
     */
    public ClassDay getClassDay() {
        return classDay;
    }

    /**
     * The setter method of class day.
     * @param classDay
     */
    public void setClassDay(ClassDay classDay) {
        this.classDay = classDay;
    }

    /**
     * The method that adds a student to this class room.
     * @param st
     * @return
     */
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
