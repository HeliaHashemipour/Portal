import java.util.ArrayList;
import java.util.List;

public class Student extends Person {

    private int balance;
    private List<Unit> units;
    private Meal[] meals;

    public Student() {
        meals = new Meal[7];
        units = new ArrayList<>();
    }

    public void deposit(int amount) {

    }

    public Meal[] getmeals() {
        return MealPlan.getMeals();
    }

    public boolean reserveMeal(Meal meal) {
        if (meal.addStudent(this) && MealPlan.indexOf(meal) != -1) {
            this.meals[MealPlan.indexOf(meal)] = meal;
            return true;
        }
        else
            return false;
    }

    public boolean addUnit(Classroom classroom) {
        int numberOfUnits = 0;
        int averageGrade = 0, totalGrade = 0;
        for (int i = 0; i < this.getUnits().size(); i++){
            int currentUnitNumber = this.getUnits().get(i).getClassroom().getNumberOfUnit();
            numberOfUnits += currentUnitNumber;
            totalGrade += this.getUnits().get(i).getGrade()*currentUnitNumber;
        }
        averageGrade = totalGrade/numberOfUnits;
        if (averageGrade < 17){
            if (numberOfUnits + classroom.getNumberOfUnit() <= 20)
                return this.units.add(classroom.getUnit());
        }
        if (averageGrade >= 17){
            if (numberOfUnits + classroom.getNumberOfUnit() <= 24)
                return this.units.add(classroom.getUnit());
        }
        return false;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public double averageGrade() {
        double sum = 0;
        double unitSum = 0;
        for (Unit unit : units) {
            sum += unit.getGrade();
            unitSum += unit.getClassroom().getNumberOfUnit();
        }
        return sum / unitSum;
    }
}