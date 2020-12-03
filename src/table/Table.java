package table;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.Controller;

public class Table {

    private int id;
    private TableState state;

    public Table(int id, TableState state) {
        this.id = id;
        setState(state);
    }

    public int getID() {
        return id;
    }

    public TableState getState() {
        return state;
    }

    public void setState(TableState state) {

        this.state = state;

        Controller controller = Controller.instance;

        for (int i = 0; i < controller.diningRoom.getChildren().size(); i++) {

            if (controller.diningRoom.getChildren().get(i) instanceof Button) {

                if (i == id) {

                    Button button = (Button) controller.diningRoom.getChildren().get(i);
                    Image image = new Image("image/table_" + state.getTextureName() + ".png", 75, 75, true, false);
                    button.setGraphic(new ImageView(image));
                }
            }
        }
    }

    public enum TableState {

        CLOSED ("disabled"),
        OPEN ("open"),
        OCCUPIED ("occupied"),
        DIRTY ("dirty");

        private String textureName;

        TableState (String textureName) {
            this.textureName = textureName;
        }

        private String getTextureName() {
            return textureName;
        }
    }
}
