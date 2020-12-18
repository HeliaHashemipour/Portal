import java.io.IOException;
import java.util.List;


/**
 * This class represents admin of this system
 * every instance of this class is has role of admin and can access admin panel.
 */
public class Admin extends Person {

    /**
     * Sets mealplan of this week
     * @param meals
     */
    public void setMealPlan(Meal[] meals) {
        MealPlan.setMeals(meals);
    }

    /**
     * Get's all students from file
     * @return
     */
    public List<Student> getStudents() {
        return FileInterface.allStudents();
    }

    /**
     * Get's all professors from file.
     * @return
     */
    public List<Professor> getProfessors() {
        return FileInterface.allProfessor();
    }

    /**
     * adds a professor to the file
     * @param professor
     * @return
     */
    public boolean addProfessor(Professor professor) {
        if (FileInterface.exists(professor.getId(), new Professor()))
            return false;
        return FileInterface.addProfessor(professor);
    }

    /**
     * Adds a student to the file.
     * @param student
     * @return true if it's added,
     */
    public boolean addStudent(Student student) {
        if (FileInterface.exists(student.getId(), new Student()))
            return false;
        return FileInterface.addStudent(student);
    }
}
