package order;

import java.util.ArrayList;
import java.util.List;

public class Tab {

    /**
     * All current food items in the tab.
     */
    private final List<Food> foods;

    public Tab() {
        foods = new ArrayList<>();
    }

    /**
     * Adds a food the tab.
     */
    public void addToTab(Food food) {
        foods.add(food);
    }

    /**
     * Calculates the total costs of everything in the tab.
     */
    public float calculateTotal() {

        float total = 0;

        for (Food food : foods) {
            total += food.getPrice();
        }

        return total;
    }

    /**
     * Used to display the tab's information on the screen.
     */
    public String getTabString() {

        StringBuilder builder = new StringBuilder();

        for (Food food : foods) {

            builder.append("$" + food.getPrice() + " - " + food.getName() + "\n");
        }

        return builder.toString();
    }
}
