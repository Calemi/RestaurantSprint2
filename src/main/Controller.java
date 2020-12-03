package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import order.Food;
import order.OrderQueue;
import profile.ActiveProfile;
import profile.AuthenticationManager;
import profile.Profile;
import profile.WaitStaffProfile;
import table.ActiveTable;
import table.DiningRoom;
import table.Table;

import java.io.IOException;

public class Controller {

    public static Controller instance;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    public GridPane diningRoom;

    @FXML
    private TextArea tabText;
    @FXML
    private Label tabTotal;

    @FXML
    private Group panelParent;

    @FXML
    private GridPane foodBtnParent;

    public void setCurrentPanel(int id) {

        for (int i = 0; i < instance.panelParent.getChildren().size(); i++) {

            if (instance.panelParent.getChildren().get(i) instanceof Group) {

                Group group = (Group) instance.panelParent.getChildren().get(i);

                if (i == id) {
                    group.setOpacity(1);
                    group.setDisable(false);
                }

                else {
                    group.setOpacity(0);
                    group.setDisable(true);
                }
            }
        }
    }

    @FXML
    private void onBtnLoginConfirm(ActionEvent event) throws IOException {

        if (AuthenticationManager.logIn(username.getText(), password.getText())) {

            Profile profile = ActiveProfile.getActiveProfile();

            Scenes.loadScene(Scenes.getMainScene());

            if (profile instanceof WaitStaffProfile) {

                WaitStaffProfile waitStaff = (WaitStaffProfile) profile;
                DiningRoom.getInstance().assignTables(waitStaff);
            }

            setCurrentPanel(0);

            System.out.println("LOGGED IN");
        }

        else System.out.println("FAILED TO LOG IN");
    }

    @FXML
    private void onBtnLogoutConfirm(ActionEvent event) throws IOException {

        AuthenticationManager.logOut();

        Scenes.loadScene(Scenes.getLoginScene());

        System.out.println("LOGGED OUT");
    }

    @FXML
    private void onBtnBack(ActionEvent event) {
        setCurrentPanel(0);
    }

    @FXML
    private void onBtnTable(ActionEvent event) {

        Button button = (Button) event.getSource();
        int tableID = Integer.parseInt(button.getText()) - 1;
        Table table = DiningRoom.getInstance().getTables()[tableID];

        if (table.getState() == Table.TableState.OCCUPIED) {

            ActiveTable.setActiveTable(table);
            updateTab();

            setCurrentPanel(1);
        }
    }

    @FXML
    private void onBtnAddOrder(ActionEvent event) {
        setCurrentPanel(2);
    }

    @FXML
    private void onBtnSelectCategory(ActionEvent event) {
        setCurrentPanel(3);

        int buttonID = Integer.parseInt(((Button)event.getSource()).getId().replace("cat", ""));

        for (int i = 0; i < instance.foodBtnParent.getChildren().size(); i++) {

            if (instance.foodBtnParent.getChildren().get(i) instanceof Button) {

                Button button = (Button) instance.foodBtnParent.getChildren().get(i);

                button.setText(Food.fromCategory(buttonID).get(i).getName());
            }
        }
    }

    @FXML
    private void onBtnAddFood(ActionEvent event) {

        Button button = (Button)event.getSource();

        Food food = Food.fromName(button.getText());

        if (food != null) {

            ActiveTable.getActiveTable().getTab().addToTab(food);
            updateTab();

            OrderQueue.getInstance().addToQueue(food);

            setCurrentPanel(1);
        }
    }

    public void updateTab() {

        Table table = ActiveTable.getActiveTable();

        tabText.setText(table.getTab().getTabString());
        tabTotal.setText("Total: $" + String.format("%.2f", table.getTab().calculateTotal()));
    }
}
