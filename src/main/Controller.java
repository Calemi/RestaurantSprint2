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
    public Group panelParent;

    @FXML
    private void onBtnLoginConfirm(ActionEvent event) throws IOException {

        if (AuthenticationManager.logIn(username.getText(), password.getText())) {

            Profile profile = ActiveProfile.getActiveProfile();

            Scenes.loadScene(Scenes.mainScene);

            if (profile instanceof WaitStaffProfile) {

                WaitStaffProfile waitStaff = (WaitStaffProfile) profile;
                DiningRoom.getInstance().assignTables(waitStaff);
            }

            System.out.println("LOGGED IN");
        }

        else System.out.println("FAILED TO LOG IN");
    }

    @FXML
    private void onBtnLogoutConfirm(ActionEvent event) throws IOException {

        AuthenticationManager.logOut();

        Scenes.loadScene(Scenes.loginScene);

        System.out.println("LOGGED OUT");
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
            }

            else System.out.println("YOU CAN'T ACCESS THAT TABLE");
        }
    }
}
