package order;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the order queue for kitchen staff.
 */
public class OrderQueue {

    private static final OrderQueue instance = new OrderQueue();

    /**
     * Used to get the single instance of the order queue.
     */
    public static OrderQueue getInstance() {
        return instance;
    }

    /**
     * The current orders in the queue.
     */
    private final List<Food> orders;

    public OrderQueue() {
        orders = new ArrayList<>();
    }

    /**
     * Adds a food item to the queue.
     */
    public void addToQueue(Food food) {

        orders.add(food);

        System.out.println("Added to Queue: " + food.getName());
        printCurrentQueue();
    }

    /**
     * Prints the queue's information.
     */
    public void printCurrentQueue() {

        StringBuilder builder = new StringBuilder("-----CURRENT ORDERS-----\n");

        for (int i = 0; i < orders.size(); i++) {
            builder.append(" #" + (i + 1) + " " + orders.get(i).getName() + " \n");
        }

        builder.append("-----END OF ORDERS-----");

        System.out.println(builder.toString());
    }
}
