package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class Scenes {

    public static FXMLLoader loginScene = new FXMLLoader(Scenes.class.getResource("../fxml/login.fxml"));
    public static FXMLLoader mainScene = new FXMLLoader(Scenes.class.getResource("../fxml/main.fxml"));

    public static void loadScene(FXMLLoader loader) throws IOException {

        Parent root = loader.load();

        Scene scene = new Scene(root, 1080, 720);

        Main.getInstance().getStage().setScene(scene);
        Main.getInstance().getStage().show();
        Controller.instance = loader.getController();
    }
}
