import java.io.*;
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
        balance += amount;
    }

    public Meal[] getmeals() {
        return MealPlan.getMeals();
    }

    public boolean reserveMeal(Meal meal) {
        if (meal.getCost() < balance && meal.addStudent(this) && MealPlan.indexOf(meal) != -1) {
            this.meals[MealPlan.indexOf(meal)] = meal;
            balance -= meal.getCost();
            return true;
        }
        else
            return false;
    }

    public boolean addUnit(Classroom classroom) {
        int numberOfUnits = 0;
        for (Unit unit : units) {
            numberOfUnits += unit.getClassroom().getNumberOfUnit();
        }
        double averageGrade = averageGrade();
        if (averageGrade < 17 && numberOfUnits + classroom.getNumberOfUnit() <= 20) {
            Unit unit = new Unit();
            unit.setClassroom(classroom);
            return this.units.add(unit);
        }
        if (averageGrade >= 17 && numberOfUnits + classroom.getNumberOfUnit() <= 24) {
            Unit unit = new Unit();
            unit.setClassroom(classroom);
            return this.units.add(unit);
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
            sum += unit.getGrade() * unit.getClassroom().getNumberOfUnit();
            unitSum += unit.getClassroom().getNumberOfUnit();
        }
        return unitSum == 0 ? 0 : sum / unitSum;
    }

    public int getNumberOfUnits() {
        int units = 0;
        for (Unit unit : this.units) {
            units += unit.getClassroom().getNumberOfUnit();
        }
        return units;
    }
}

