package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import profile.ActiveProfile;
import profile.AuthenticationManager;
import profile.Profile;
import profile.WaitStaffProfile;
import table.DiningRoom;

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
    private Group panelParent;

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

        Profile profile = ActiveProfile.getActiveProfile();

        if (profile instanceof WaitStaffProfile) {

            WaitStaffProfile waitStaff = (WaitStaffProfile) profile;

            if (waitStaff.isAssignedTable(tableID)) {
                System.out.println("EDIT SCREEN");

                setCurrentPanel(1);
            }

            else System.out.println("YOU CAN'T ACCESS THAT TABLE");
        }
    }

    @FXML
    private void onBtnAddOrder(ActionEvent event) {
        setCurrentPanel(2);
    }

    @FXML
    private void onBtnSelectCategory(ActionEvent event) {
        setCurrentPanel(3);
    }
}
