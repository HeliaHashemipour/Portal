import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a meal for students
 * It means that every instance of this class is a meal.
 * @Hashemipour
 * @since 2020
 */
public class Meal implements Serializable {

    @Serial
    private static final long serialVersionUID = 2871764622758109040L;

    private String name;
    private int cost;
    private final List<Student> students;

    /**
     * The constructor of this class.
     */
    public Meal() {
        students = new ArrayList<>();
    }

    /**
     * The getter method of name
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
     * The getter method of cost.
     * @return
     */
    public int getCost() {
        return cost;
    }

    /**
     * The setter method of cost.
     * @param cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * \The getter method of studetns
     * @return
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * This method adds student to this meal.
     * @param st
     * @return
     */
    public boolean addStudent(Student st) {
        if (students.contains(st))
            return false;
        return students.add(st);
    }
}
