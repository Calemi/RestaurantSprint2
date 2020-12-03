package order;

import java.util.ArrayList;
import java.util.List;

public class OrderQueue {

    private static OrderQueue instance = new OrderQueue();

    public static OrderQueue getInstance() {
        return instance;
    }

    private List<Food> orders;

    public OrderQueue() {
        orders = new ArrayList<>();
    }

    public void addToQueue(Food food) {

        orders.add(food);

        System.out.println("Added to Queue: " + food.getName());
        printCurrentQueue();
    }

    public void printCurrentQueue() {

        StringBuilder builder = new StringBuilder("-----CURRENT ORDERS-----\n");

        for (int i = 0; i < orders.size(); i++) {
            builder.append(" #" + (i + 1) + " " + orders.get(i).getName() + " \n");
        }

        builder.append("-----END OF ORDERS-----");

        System.out.println(builder.toString());
    }
}
