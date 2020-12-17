import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MealPlan {

    private static Meal[] meals;

    static {
        meals = new Meal[7];
        for (int i = 0; i < 7; i++) {
            meals[i] = new Meal();
        }
    }

    public static void setMeals(Meal[] meals) {
        MealPlan.meals = meals;
    }

    public static Meal[] getMeals() {
        return meals;
    }

    public static int indexOf(Meal meal) {
        for (int i = 0; i < 7; i++) {
            if (meals[i].equals(meal))
                return i;
        }
        return -1;
    }
}
