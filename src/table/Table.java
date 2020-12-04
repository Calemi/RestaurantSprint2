package table;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Controller;
import order.Tab;

import java.util.Random;

/**
 * Holds the tab data and a table state.
 */
public class Table {

    private final int id;
    private TableState state;
    private final Tab tab;

    public Table(int id, TableState state, Tab tab) {
        this.id = id;
        setState(state);
        this.tab = tab;
    }

    /**
     * Gets the table's current state.
     */
    public TableState getState() {
        return state;
    }

    /**
     * Sets the table's current state. Also updates the icon on the GUI.
     */
    public void setState(TableState state) {

        this.state = state;

        Controller controller = Controller.instance;

        //Iterates through all of the table buttons.
        for (int i = 0; i < controller.diningRoom.getChildren().size(); i++) {

            //Checks if the current node is a button
            if (controller.diningRoom.getChildren().get(i) instanceof Button) {

                //Checks if their ids are the same
                if (i == id) {

                    //Handles changing the icon.
                    Button button = (Button) controller.diningRoom.getChildren().get(i);
                    Image image = new Image("image/table_" + state.getTextureName() + ".png", 75, 75, true, false);
                    button.setGraphic(new ImageView(image));
                }
            }
        }
    }

    /**
     * Gets the table's current tab.
     */
    public Tab getTab() {
        return tab;
    }

    /**
     * The different states a table can have.
     */
    public enum TableState {

        CLOSED ("disabled"),
        OPEN ("open"),
        OCCUPIED ("occupied"),
        DIRTY ("dirty");

        private final String textureName;

        TableState (String textureName) {
            this.textureName = textureName;
        }

        /**
         * Gets the texture to use when switching the table's icon.
         */
        private String getTextureName() {
            return textureName;
        }

        /**
         * Gets a random table state that isn't "closed".
         */
        public static TableState getRandomState() {

            Random random = new Random();

            int i = random.nextInt(3);

            switch (i) {
                case 0 : return OPEN;
                case 1 : return OCCUPIED;
                default: return DIRTY;
            }
        }
    }
}
