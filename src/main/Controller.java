package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import profile.AuthenticationManager;

import java.io.IOException;

public class Controller {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void onBtnLoginConfirm(ActionEvent event) throws IOException {

        if (AuthenticationManager.logIn(username.getText(), password.getText())) {

            Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
            Scene mainScene = new Scene(root, 1080, 720);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainScene);
            window.show();

            System.out.println("LOGGED IN");
        }

        else System.out.println("FAILED TO LOG IN");
    }

    @FXML
    private void onBtnLogoutConfirm(ActionEvent event) throws IOException {

        AuthenticationManager.logOut();

        Parent root = FXMLLoader.load(getClass().getResource("../fxml/login.fxml"));
        Scene loginScene = new Scene(root, 1080, 720);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();

        System.out.println("LOGGED OUT");
    }
}
