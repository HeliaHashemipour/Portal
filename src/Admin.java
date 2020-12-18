import java.io.IOException;
import java.util.List;

public class Admin extends Person {

    public void setMealPlan(Meal[] meals) {
        MealPlan.setMeals(meals);
    }

    public List<Student> getStudents() {
        return FileInterface.allStudents();
    }

    public List<Professor> getProfessors() {
        return FileInterface.allProfessor();
    }

    public boolean addProfessor(Professor professor) {
        if (FileInterface.exists(professor.getId(), new Professor()))
            return false;
        return FileInterface.addProfessor(professor);
    }

    public boolean addStudent(Student student) {
        if (FileInterface.exists(student.getId(), new Student()))
            return false;
        return FileInterface.addStudent(student);
    }
}
