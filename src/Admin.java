import java.util.List;

public class Admin extends Person {
    public void setMealPlan(Meal[] meals) {
        MealPlan.setMeals(meals);
    }

    public List<Student> getStudents() {
        return FileInterface.allStudents();
    }

    public List<Professor> getProfessors() {

    }

    public boolean addProfessor(Professor professor) {

    }

    public boolean addStudent(Student student) {

    }

}
