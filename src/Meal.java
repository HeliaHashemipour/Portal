
import java.util.List;

public class Meal {

    private String name;
    private int cost;
    private List<Student> students;

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


