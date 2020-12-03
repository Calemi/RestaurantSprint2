package table;

import order.Food;

import java.util.ArrayList;
import java.util.List;

public class Tab {

    private List<Food> foods;

    public Tab() {
        foods = new ArrayList<>();
    }

    public void addToTab(Food food) {
        foods.add(food);
    }

    public float calculateTotal() {

        float total = 0;

        for (Food food : foods) {
            total += food.getPrice();
        }

        return total;
    }

    public String getTabString() {

        StringBuilder builder = new StringBuilder();

        for (Food food : foods) {

            builder.append("$" + food.getPrice() + " - " + food.getName() + "\n");
        }

        return builder.toString();
    }
}
