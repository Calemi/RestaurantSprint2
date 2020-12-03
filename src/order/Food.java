package order;

import java.util.ArrayList;
import java.util.List;

public enum Food {

    BURGER_CLASSIC      (0, "Classic Burger", 8.99F),
    BURGER_MUSH_STEAK   (0, "Mushroom Burger", 9.99F),
    BURGER_HICKORY      (0, "Hickory Burger", 9.99F),
    BURGER_JALAPENO     (0, "Jalapeno Burger", 8.50F),

    SALAD_CLASSIC       (1, "Classic Salad", 10.99F),
    SALAD_BUFFALO       (1, "Buffalo Salad", 11.99F),
    SALAD_COBB          (1, "Cobb Salad", 13.99F),
    SALAD_HICKORY       (1, "Hickory Salad", 11.99F),

    SOUP_CLAM_CHOWDER   (2, "Clam Chowder", 6.99F),
    SOUP_ONION          (2, "French Onion Soup", 6.99F),
    SOUP_CHICKEN_TORT   (2, "Chicken Tort Soup", 6.99F),
    SOUP_LOBSTER_BISQUE (2, "Bisque", 6.99F),

    PIZZA_CHEESE        (3, "Cheese Pizza", 11.99F),
    PIZZA_PEPPERONI     (3, "Pepperoni Pizza", 12.99F),
    PIZZA_SUPREME       (3, "Supreme Pizza", 13.99F),
    PIZZA_VEGETARIAN    (3, "Vegetarian Pizza", 11.99F),

    SIDE_FRIES          (4, "French Fries", 2.99F),
    SIDE_BROCCOLI       (4, "Broccoli", 2.99F),
    SIDE_OKRA           (4, "Fried Okra", 2.99F),
    SIDE_BEANS          (4, "Baked Beans", 3.99F);

    private int categoryID;
    private String name;
    private float price;

    Food (int categoryID, String name, float price) {
        this.categoryID = categoryID;
        this.name = name;
        this.price = price;
    }

    public static List<Food> fromCategory(int categoryID) {

        List<Food> foods = new ArrayList<>();

        for (Food food : values()) {

            if (food.categoryID == categoryID) {
                foods.add(food);
            }
        }

        return foods;
    }

    public static Food fromName(String name) {

        for (Food food : values()) {

            if (food.getName().equalsIgnoreCase(name)) {
                return food;
            }
        }

        return null;
    }


    public int getCategoryID() {
        return categoryID;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}
