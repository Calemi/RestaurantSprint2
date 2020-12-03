package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Scenes {

    public static void loadScene(FXMLLoader loader) throws IOException {

        Parent root = loader.load();

        Scene scene = new Scene(root, 1080, 720);

        Main.getInstance().getStage().setScene(scene);
        Main.getInstance().getStage().show();
        Controller.instance = loader.getController();
    }

    public static FXMLLoader getLoginScene() {
        return new FXMLLoader(Scenes.class.getResource("../fxml/login.fxml"));
    }

    public static FXMLLoader getMainScene() {
        return new FXMLLoader(Scenes.class.getResource("../fxml/main.fxml"));
    }
}
