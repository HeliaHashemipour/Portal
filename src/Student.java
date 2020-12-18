import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the students of this application.
 * this means every instance of this class is a student.
 */
public class Student extends Person {

    private int balance;
    private List<Unit> units;
    private Meal[] meals;

    /**
     * This is the constructor that initializes the meals and the units.
     */
    public Student() {
        meals = new Meal[7];
        units = new ArrayList<>();
    }

    /**
     * This method gets an amount and add it to the balance of this students account.
     * @param amount
     */
    public void deposit(int amount) {
        balance += amount;
    }

    /**
     * This method returns the meal plan of this week.
     * @return
     */
    public Meal[] getmeals() {
        return MealPlan.getMeals();
    }

    /**
     * Student can reserve meal with this method.
     * it we'll return true if student could reserve the meal
     * @param meal
     * @return
     */
    public boolean reserveMeal(Meal meal) {
        if (meal.getCost() < balance && meal.addStudent(this) && MealPlan.indexOf(meal) != -1) {
            this.meals[MealPlan.indexOf(meal)] = meal;
            balance -= meal.getCost();
            return true;
        }
        else
            return false;
    }

    /**
     * Student can add unit with this method.
     * this means student can join to a class room this term.
     * @param classroom
     * @return
     */
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

    /**
     * The setter method of the balance.
     * @param balance
     */
    public void setBalance(int balance) {
        this.balance = balance;
    }

    /**
     * The getter method of the balance.
     * @return
     */
    public int getBalance() {
        return balance;
    }

    /**
     * The getter method of the units.
     * @return
     */
    public List<Unit> getUnits() {
        return units;
    }

    /**
     * This method calculates the average grade of this student.
     * @return
     */
    public double averageGrade() {
        double sum = 0;
        double unitSum = 0;
        for (Unit unit : units) {
            sum += unit.getGrade() * unit.getClassroom().getNumberOfUnit();
            unitSum += unit.getClassroom().getNumberOfUnit();
        }
        return unitSum == 0 ? 0 : sum / unitSum;
    }

    /**
     * This method calculates the number of units of this student.
     * @return
     */
    public int getNumberOfUnits() {
        int units = 0;
        for (Unit unit : this.units) {
            units += unit.getClassroom().getNumberOfUnit();
        }
        return units;
    }
}

