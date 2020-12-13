public class MealPlan {

    private static Meal[] meals;

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
