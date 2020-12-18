import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The meal paln of this week
 * @Hashemipour
 * @since 2020
 */
public class MealPlan {

    private static Meal[] meals;

    static {
        meals = new Meal[7];
        for (int i = 0; i < 7; i++) {
            meals[i] = new Meal();
        }
    }

    /**
     * The setter method of meals.
     * @param meals
     */
    public static void setMeals(Meal[] meals) {
        MealPlan.meals = meals;
    }

    /**
     * The getter method of meals
     * @return
     */
    public static Meal[] getMeals() {
        return meals;
    }

    /**
     * Gets a meal and returns the index of that meal.
     * @param meal
     * @return
     */
    public static int indexOf(Meal meal) {
        for (int i = 0; i < 7; i++) {
            if (meals[i].equals(meal))
                return i;
        }
        return -1;
    }
}
