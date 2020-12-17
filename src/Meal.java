import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meal implements Serializable {

    @Serial
    private static final long serialVersionUID = 2871764622758109040L;

    private String name;
    private int cost;
    private final List<Student> students;

    public Meal() {
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean addStudent(Student st) {
        if (students.contains(st))
            return false;
        return students.add(st);
    }
}
