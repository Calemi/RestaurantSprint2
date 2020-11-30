package main;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import profile.AuthenticationManager;

public class Controller {

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private void onBtnLoginConfirm(ActionEvent event) {
        AuthenticationManager.logIn(username.getText(), password.getText());
    }
}
